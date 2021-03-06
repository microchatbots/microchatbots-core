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
import com.microchatbots.core.request.handler.CommandHandler;
import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.core.send.SendMessage;

import java.io.Serializable;
import java.util.Optional;

public abstract class SendMessageCommandHandler extends CommandHandler<TelegramBotConfiguration, Update, SendMessage> implements TelegramRequestHandler<SendMessage> {

    public SendMessageCommandHandler(SpaceParser<Update> spaceParser,
                                     TextParser<Update> textParser) {
        super(spaceParser, textParser);
    }

    protected abstract String getText(TelegramBotConfiguration telegramBotConfiguration, Update update);

    @Override
    public SendMessage handle(TelegramBotConfiguration telegramBotConfiguration, Update update) {
        SendMessage sendMessage = new SendMessage();
        Optional<Serializable> chatId = spaceParser.parseSpaceUniqueIdentifier(telegramBotConfiguration, update);
        chatId.ifPresent(serializable -> sendMessage.setChatId(serializable.toString()));
        sendMessage.setText(getText(telegramBotConfiguration, update));
        return sendMessage;
    }
}
