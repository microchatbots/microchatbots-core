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
package com.microchatbots.telegrambots.parser;

import com.microchatbots.core.conf.BotConfiguration;
import com.microchatbots.core.parser.SpaceParser;
import com.microchatbots.core.parser.TextParser;
import com.microchatbots.core.parser.UserParser;
import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import com.microchatbots.telegrambots.core.Chat;
import com.microchatbots.telegrambots.core.Message;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.core.User;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;

@Singleton
public class TelegramParser implements SpaceParser<Update>, UserParser<Update>, TextParser<Update> {
    private static final Logger LOG = LoggerFactory.getLogger(TelegramParser.class);
    public static final String COMMAND_PREFIX = "/";

    @Override
    public Optional<Serializable> parseSpaceUniqueIdentifier(@NonNull @NotNull @Valid BotConfiguration botConfiguration,
                                                             @NonNull @NotNull @Valid Update update) {
        Optional<Chat> chatOptional = parseChat(update);
        if (chatOptional.isPresent()) {
            return chatOptional.map(Chat::getId);
        }
        if (update.getCallbackQuery() != null) {
            return Optional.of(update.getCallbackQuery().getChatInstance());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Serializable> parseUserUniqueIdentifier(@NonNull @NotNull @Valid BotConfiguration botConfiguration,
                                                            @NonNull @NotNull @Valid Update update) {
        return parseUser(update).map(User::getId);
    }

    @NonNull
    public Optional<User> parseUser(@NonNull Message message) {
        return Optional.ofNullable(message.getFrom());
    }

    @NonNull
    public Optional<String> parseText(@NonNull Message message) {
        return Optional.ofNullable(message.getText());
    }

    public Optional<Chat> parseChat(@NonNull Update update) {
        if (update.getEditedMessage() != null) {
            return Optional.of(update.getEditedMessage().getChat());
        }
        if (update.getEditedChannelPost() != null) {
            return Optional.of(update.getEditedChannelPost().getChat());
        }
        if (update.getChannelPost() != null) {
            return Optional.of(update.getChannelPost().getChat());
        }
        if (update.getMessage() != null) {
            return Optional.of(update.getMessage().getChat());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> parseText(@NonNull @NotNull @Valid BotConfiguration botConfiguration,
                                      @NonNull @NotNull @Valid Update update,
                                      boolean excludeBotName) {

        Optional<String> textOptional = parseText(update);
        if (!excludeBotName) {
            return textOptional;
        }
        if (!textOptional.isPresent()) {
            return Optional.empty();
        }
        String text = textOptional.get();
        if (botConfiguration instanceof TelegramBotConfiguration) {
            String username =((TelegramBotConfiguration) botConfiguration).getAtUsername();
            return Optional.of(text.replaceAll(username, "").trim());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> parserCommand(@NonNull @NotNull @Valid BotConfiguration botConfiguration,
                                          @NonNull @NotNull @Valid Update update) {
        Optional<String> text = parseText(botConfiguration, update, true);
        if (!text.isPresent()) {
            return Optional.empty();
        }
        return parseCommand(text.get());
    }

    protected Optional<String> parseCommand(@NonNull String text) {
        if (LOG.isInfoEnabled()) {
            LOG.info("text parsed: {}", text);
        }
        if (text.indexOf(StringUtils.SPACE) != -1) {
            String[] arr = text.split(""+ StringUtils.SPACE);
            if (arr.length >= 1) {
                text = arr[0];
            }
        }
        if (text.startsWith(COMMAND_PREFIX)) {
            return Optional.of(text.substring(1));
        }
        return Optional.empty();
    }

    public Optional<String> parseText(@NonNull Update update) {
        if (update.getEditedMessage() != null) {
            return parseText(update.getEditedMessage());
        }
        if (update.getMessage() != null) {
            return parseText(update.getMessage());
        }
        if (update.getEditedChannelPost() != null) {
            return parseText(update.getEditedChannelPost());
        }
        if (update.getChannelPost() != null) {
            return parseText(update.getChannelPost());
        }
        if (update.getInlineQuery() != null) {
            return Optional.of(update.getInlineQuery().getQuery());
        }
        if (update.getChosenInlineResult() != null) {
            return Optional.of(update.getChosenInlineResult().getQuery());
        }
        if (update.getCallbackQuery() != null) {
            return Optional.ofNullable(update.getCallbackQuery().getData());
        }
        return Optional.empty();
    }

    @NonNull
    public Optional<User> parseUser(@NonNull Update update) {
        if (update.getEditedMessage() != null) {
            return parseUser(update.getEditedMessage());
        }
        if (update.getMessage() != null) {
            return parseUser(update.getMessage());
        }
        if (update.getEditedChannelPost() != null) {
            return parseUser(update.getEditedChannelPost());
        }
        if (update.getChannelPost() != null) {
            return parseUser(update.getChannelPost());
        }
        if (update.getInlineQuery() != null) {
            return Optional.of(update.getInlineQuery().getFrom());
        }
        if (update.getChosenInlineResult() != null) {
            return Optional.of(update.getChosenInlineResult().getFrom());
        }
        if (update.getCallbackQuery() != null) {
            return Optional.of(update.getCallbackQuery().getFrom());
        }
        if (update.getShippingQuery() != null) {
            return Optional.of(update.getShippingQuery().getFrom());
        }
        if (update.getPreCheckoutQuery() != null) {
            return Optional.of(update.getPreCheckoutQuery().getFrom());
        }
        if (update.getPollAnswer() != null) {
            return Optional.of(update.getPollAnswer().getUser());
        }
        return Optional.empty();
    }
}
