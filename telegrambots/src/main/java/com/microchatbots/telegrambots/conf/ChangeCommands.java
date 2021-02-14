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

import com.microchatbots.telegrambots.api.TelegramBotClient;
import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.StringUtils;
import io.micronaut.scheduling.TaskExecutors;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Requires(property = TelegramConfigurationProperties.PREFIX + ".change-commands", value = StringUtils.TRUE)
@Context
public class ChangeCommands {
    private static final Logger LOG = LoggerFactory.getLogger(ChangeCommands.class);

    public ChangeCommands(@Named(TaskExecutors.IO) ExecutorService executorService,
                          TelegramBotClient telegramBotClient,
                          List<TelegramBotConfiguration> telegramBotConfigurations) {
        Scheduler scheduler = Schedulers.from(executorService);

        for (TelegramBotConfiguration bot : telegramBotConfigurations) {
            telegramBotClient.setMyCommands(bot.getToken(), bot.getBotCommands())
                    .subscribeOn(scheduler)
                    .subscribe(telegramApiResponse -> {
                if (LOG.isInfoEnabled()) {
                    LOG.info("set commands for bot {} with response {}", bot.getName(), telegramApiResponse.toString());
                }
            });
        }
    }
}
