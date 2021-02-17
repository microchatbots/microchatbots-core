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
package com.microchatbots.core.parser;

import com.microchatbots.core.conf.BotConfiguration;
import edu.umd.cs.findbugs.annotations.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface TextParser<Input> {
    Optional<String> parseText(@NonNull @NotNull @Valid BotConfiguration botConfiguration,
                               @NonNull @NotNull @Valid Input update,
                                      boolean excludeBotName);

    default Optional<String> parseText(@NonNull @NotNull @Valid BotConfiguration botConfiguration,
                                      @NonNull Input update) {
        return parseText(botConfiguration, update, false);
    }

    Optional<String> parserCommand(@NonNull @NotNull @Valid BotConfiguration botConfiguration,
                                   @NonNull @NotNull @Valid Input update);
}
