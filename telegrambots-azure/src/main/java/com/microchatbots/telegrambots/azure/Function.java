/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microchatbots.telegrambots.azure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import com.microchatbots.telegrambots.conf.TokenValidator;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.core.send.Send;
import com.microchatbots.telegrambots.dispatcher.TelegramDispatcher;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import io.micronaut.azure.function.AzureFunction;
import io.micronaut.core.annotation.Introspected;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Introspected
public class Function extends AzureFunction {
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String TEXT_PLAIN = "text/plain";

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    public TokenValidator tokenValidator;

    @Inject
    public TelegramDispatcher dispatcher;

    @FunctionName("Webhook")
    public HttpResponseMessage webhook(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "{token}")
                    HttpRequestMessage<Optional<String>> request,
                    @BindingName("token") String token,
            ExecutionContext context) {
        if (context != null) {
            context.getLogger().info("Executing Function: " + getClass().getName());
            context.getLogger().info("request: {}" + request);
        }
        if (!request.getBody().isPresent()) {
            HttpResponseMessage.Builder builder = request.createResponseBuilder(HttpStatus.BAD_REQUEST);
            if (context != null) {
                context.getLogger().info("body is empty");
            }
            return builder.build();
        }
        String path = request.getUri().getPath();
        String body = request.getBody().get();

        if (context != null) {
            context.getLogger().info(body);
        }

        if (context != null) {
            context.getLogger().info("resolved path: " + token);
        }
        if (token == null) {
            if (context != null) {
                context.getLogger().info("could not resolve token");
            }
            HttpResponseMessage.Builder builder = request.createResponseBuilder(HttpStatus.UNAUTHORIZED);
            return builder.build();
        }

        Optional<TelegramBotConfiguration> telegramBotConfigurationOptional = tokenValidator.validate(token);
        if (!telegramBotConfigurationOptional.isPresent()) {
            if (context != null) {
                context.getLogger().info("not bot with token that matches token " + token);
            }
            HttpResponseMessage.Builder builder = request.createResponseBuilder(HttpStatus.UNAUTHORIZED);
            return builder.build();
        }

        Map<String, String> headers = new HashMap<>();
        HttpResponseMessage.Builder builder = request.createResponseBuilder(HttpStatus.OK);
        if (body != null && !body.trim().isEmpty()) {
            try {
                Update update = objectMapper.readValue(body, Update.class);
                TelegramBotConfiguration telegramBotConfiguration = telegramBotConfigurationOptional.get();
                Optional<?> answerOptional = dispatcher.dispatch(telegramBotConfiguration, update);
                if (answerOptional.isPresent()) {
                    Object object = answerOptional.get();
                    if (context != null) {
                        context.getLogger().info("dispatcher received an answer of class " + object.getClass().getSimpleName());
                    }
                    if (object instanceof Send) {
                        Send send = (Send) object;
                        String json  = objectMapper.writeValueAsString(send);
                        if (context != null) {
                            context.getLogger().info("response json is:" + json);
                        }
                        headers.put(CONTENT_TYPE, APPLICATION_JSON);
                        builder = builder.body(json);
                    }
                } else {
                    if (context != null) {
                        context.getLogger().info("dispatcher received no answer");
                    }
                }

            } catch (JsonProcessingException e) {
                if (context != null) {
                    context.getLogger().info("json proccession error marshalling the send message " + e.getMessage());
                }
                headers.put(CONTENT_TYPE, TEXT_PLAIN);
                builder = builder.body("error converting message to json string");
                builder = builder.status(HttpStatus.BAD_REQUEST);
            }
        } else {
            if (context != null) {
                context.getLogger().warning("body is null");
            }
            headers.put(CONTENT_TYPE, TEXT_PLAIN);
            builder = builder.body("body is null");
            builder = builder.status(HttpStatus.BAD_REQUEST);
        }
        for (String k : headers.keySet()) {
            builder = builder.header(k, headers.get(k));
        }
        return builder.build();
    }
}