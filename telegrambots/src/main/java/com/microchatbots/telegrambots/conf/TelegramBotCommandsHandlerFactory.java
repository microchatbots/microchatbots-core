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
package com.microchatbots.telegrambots.conf;

import com.microchatbots.core.parser.SpaceParser;
import com.microchatbots.core.parser.TextParser;
import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.handler.TelegramBotCommandsHandler;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.ResourceLoader;
import javax.inject.Singleton;

@Factory
public class TelegramBotCommandsHandlerFactory {

    private final ResourceLoader resourceLoader;
    private final SpaceParser<Update> spaceParser;
    private final TextParser<Update> textParser;

    public TelegramBotCommandsHandlerFactory(ResourceLoader resourceLoader,
                                             SpaceParser<Update> spaceParser,
                                             TextParser<Update> textParser) {
        this.resourceLoader = resourceLoader;
        this.spaceParser = spaceParser;
        this.textParser = textParser;
    }

    @EachBean(TelegramBotConfiguration.class)
    @Singleton
    public TelegramBotCommandsHandler createCommandHandler(TelegramBotConfiguration telegramBotConfiguration) {
        return new TelegramBotCommandsHandler(telegramBotConfiguration, resourceLoader, spaceParser, textParser);
    }
}
