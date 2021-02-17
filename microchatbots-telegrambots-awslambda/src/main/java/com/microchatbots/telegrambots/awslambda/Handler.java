/*
 * Copyright 2017-2021 original authors
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
package com.microchatbots.telegrambots.awslambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import com.microchatbots.telegrambots.conf.TokenValidator;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.core.send.Send;
import com.microchatbots.telegrambots.dispatcher.TelegramDispatcher;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;
import io.micronaut.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Introspected
public class Handler extends MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    public static final String PATH_START = "/";
    public static final String TOKEN = "token";
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String TEXT_PLAIN = "text/plain";
    private static final Logger LOG = LoggerFactory.getLogger(Handler.class);

    @Inject
    protected ObjectMapper objectMapper;

    @Inject
    protected TokenValidator tokenValidator;

    @Inject
    protected TelegramDispatcher dispatcher;

    @Override
    public APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent input) {

        if (LOG.isTraceEnabled()) {
            LOG.trace("{}", input.toString());
        }
        final String token = input.getPath() != null ?
                (input.getPath().startsWith(PATH_START) ? input.getPath().substring(PATH_START.length()) : input.getPath()) :
                input.getPathParameters().get(TOKEN);
        if (LOG.isInfoEnabled()) {
            LOG.info("path: {}", token);
        }

        Optional<TelegramBotConfiguration> telegramBotConfigurationOptional = tokenValidator.validate(token);
        if (!telegramBotConfigurationOptional.isPresent()) {
            if (LOG.isTraceEnabled()) {
                LOG.trace("not bot with token that matches token {}", token);
            }
            APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
            apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.UNAUTHORIZED.getCode());
            return apiGatewayProxyResponseEvent;
        }

        Map<String, String> headers = new HashMap<>();
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();

        if (input.getBody() != null && !input.getBody().trim().isEmpty()) {
            try {
                Update update = objectMapper.readValue(input.getBody(), Update.class);
                TelegramBotConfiguration telegramBotConfiguration = telegramBotConfigurationOptional.get();
                Optional<?> answerOptional = dispatcher.dispatch(telegramBotConfiguration, update);
                if (answerOptional.isPresent()) {
                    Object object = answerOptional.get();
                    if (LOG.isInfoEnabled()) {
                        LOG.info("dispatcher received an answer of class {}", object.getClass().getSimpleName());
                    }
                    if (object instanceof Send) {
                        Send send = (Send) object;
                        String json  = objectMapper.writeValueAsString(send);
                        LOG.info("response json is:" + json);
                        headers.put(CONTENT_TYPE, APPLICATION_JSON);
                        apiGatewayProxyResponseEvent.setBody(json);
                    }
                } else {
                    if (LOG.isInfoEnabled()) {
                        LOG.info("dispatcher received no answer");
                    }
                }
                apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.OK.getCode());

            } catch (JsonProcessingException e) {
                LOG.info("json proccession error marshalling the send message " + e.getMessage());
                headers.put(CONTENT_TYPE, TEXT_PLAIN);
                apiGatewayProxyResponseEvent.setBody("error converting message to json string");
                apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.BAD_REQUEST.getCode());
            }
        } else {
            LOG.warn("body is null");
            headers.put(CONTENT_TYPE, TEXT_PLAIN);
            apiGatewayProxyResponseEvent.setBody("body is null");
            apiGatewayProxyResponseEvent.setStatusCode(HttpStatus.BAD_REQUEST.getCode());
        }
        apiGatewayProxyResponseEvent.setHeaders(headers);
        return apiGatewayProxyResponseEvent;
    }
}
