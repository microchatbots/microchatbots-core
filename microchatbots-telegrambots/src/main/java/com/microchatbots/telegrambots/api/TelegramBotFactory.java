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
package com.microchatbots.telegrambots.api;

import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

@Factory
public class TelegramBotFactory {
    private static final Logger LOG = LoggerFactory.getLogger(TelegramBotFactory.class);
    private final TelegramBotClient telegramBotClient;

    public TelegramBotFactory(TelegramBotClient telegramBotClient) {
        this.telegramBotClient = telegramBotClient;
    }

    /**
     *
     * @param telegramBotConfiguration Telegram's Bot configuration
     * @return A bean of type {@link TelegramBot} for each bean of type {@link TelegramBotConfiguration}
     */
    @EachBean(TelegramBotConfiguration.class)
    public TelegramBot createBot(TelegramBotConfiguration telegramBotConfiguration) {
        return new DefaultTelegramBot(telegramBotConfiguration.getToken(), telegramBotClient);
    }

    /**
     *
     * @param telegramBotConfiguration Telegram's Bot configuration
     * @return A bean of type {@link BlockingTelegramBot} for each bean of type {@link TelegramBotConfiguration}
     */
    @EachBean(TelegramBotConfiguration.class)
    public BlockingTelegramBot createBlockingBot(TelegramBotConfiguration telegramBotConfiguration) {
        try {
            return new DefaultBlockingTelegramBot(telegramBotConfiguration.getToken());
        } catch (MalformedURLException e) {
            LOG.warn("Malformed URL Exception thrown trying to instantiate a blocking telegram bot");
        }
        return null;
    }
}
