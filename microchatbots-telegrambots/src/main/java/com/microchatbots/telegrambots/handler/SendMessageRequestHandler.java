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
package com.microchatbots.telegrambots.handler;

import com.microchatbots.core.parser.SpaceParser;
import com.microchatbots.core.parser.TextParser;
import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.core.send.SendMessage;

import java.io.Serializable;
import java.util.Optional;

public abstract class SendMessageRequestHandler implements TelegramRequestHandler<SendMessage> {
    protected final SpaceParser<Update> spaceParser;
    protected final TextParser<Update> textParser;

    public SendMessageRequestHandler(SpaceParser<Update> spaceParser,
                                     TextParser<Update> textParser) {
        this.spaceParser = spaceParser;
        this.textParser = textParser;
    }

    @Override
    public SendMessage handle(TelegramBotConfiguration telegramBotConfiguration, Update update) {
        SendMessage sendMessage = new SendMessage();
        Optional<Serializable> chatId = spaceParser.parseSpaceUniqueIdentifier(telegramBotConfiguration, update);
        chatId.ifPresent(serializable -> sendMessage.setChatId(serializable.toString()));
        Optional<String> textOptional = textParser.parseText(telegramBotConfiguration, update, true);
        textOptional.flatMap(this::getResponse).ifPresent(sendMessage::setText);
        return sendMessage;
    }

    public abstract Optional<String> getResponse(String text);
}
