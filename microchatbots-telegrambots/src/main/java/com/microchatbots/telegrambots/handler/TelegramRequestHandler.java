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

import com.microchatbots.core.request.handler.GenericRequestHandler;
import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import com.microchatbots.telegrambots.core.Update;

/**
 * Request handlers are responsible for handling telegram {@link Update} requests.
 * @param <Output> output type.
 */
public interface TelegramRequestHandler<Output> extends GenericRequestHandler<TelegramBotConfiguration, Update, Output> {


}
