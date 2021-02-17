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
package com.microchatbots.core.request.handler;

import com.microchatbots.core.conf.BotConfiguration;
import io.micronaut.core.order.Ordered;

/**
 * Request handlers are responsible for handling one or more types of incoming requests.
 * @param <Bot> The Bot configuration
 * @param <Input> input type.
 * @param <Output> output type.
 */
public interface GenericRequestHandler<Bot extends BotConfiguration, Input, Output> extends Ordered {

        /**
         * Returns true if the handler can dispatch the current request.
         * @param bot bot being ask to handle this command
         * @param input input to the request handler
         * @return true if the handler is capable of handling the current request
         */
        boolean  canHandle(Bot bot, Input input);

        /**
         * Handles the request.
         * @param bot bot being ask to handle this command
         * @param input input to the request handler
         * @return output from the handler.
         */
        Output handle(Bot bot, Input input);
}
