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
package com.microchatbots.telegrambots.dispatcher;

import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.handler.TelegramRequestHandler;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.order.OrderUtil;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class TelegramDispatcher {

    private final List<TelegramRequestHandler<?>> handlers;

    public TelegramDispatcher(Collection<TelegramRequestHandler<?>> handlerCollection) {
        this.handlers = handlerCollection.stream().sorted(OrderUtil.COMPARATOR).collect(Collectors.toList());
    }

    /**
     *
     * @param botConfiguration Telegram's bot configuration
     * @param update Update
     * @return An optional response
     */
    public Optional<?> dispatch(@NonNull @NotNull @Valid TelegramBotConfiguration botConfiguration,
                                @NonNull @NotNull @Valid Update update) {
        for (TelegramRequestHandler<?> handler : handlers) {
            if (handler.canHandle(botConfiguration, update)) {
                return Optional.of(handler.handle(botConfiguration, update));
            }
        }
        return Optional.empty();
    }
}
