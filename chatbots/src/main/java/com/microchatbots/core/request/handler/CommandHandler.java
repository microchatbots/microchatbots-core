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
package com.microchatbots.core.request.handler;

import com.microchatbots.core.conf.BotConfiguration;
import com.microchatbots.core.parser.SpaceParser;
import com.microchatbots.core.parser.TextParser;

import java.util.Optional;

public abstract class CommandHandler<Bot extends BotConfiguration, Input, Output> implements GenericRequestHandler<Bot, Input, Output> {

    protected final TextParser<Input> textParser;
    protected final SpaceParser<Input> spaceParser;

    public CommandHandler(SpaceParser<Input> spaceParser,
                                     TextParser<Input> textParser) {
        this.spaceParser = spaceParser;
        this.textParser = textParser;
    }

    @Override
    public boolean canHandle(Bot botConfiguration, Input input) {
        if (!spaceParser.parseSpaceUniqueIdentifier(botConfiguration, input).isPresent()) {
            return false;
        }
        Optional<String> commandOptional = textParser.parserCommand(botConfiguration, input);
        if (!commandOptional.isPresent()) {
            return false;
        }
        String command = commandOptional.get();
        return getCommandName().equals(command);
    }

    protected abstract String getCommandName();
}
