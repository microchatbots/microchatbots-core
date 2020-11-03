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
package com.microchatbots.telegrambots.core;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @see <a href="https://core.telegram.org/bots/api#botcommand">BotCommand</a>
 */
@Introspected
public class BotCommand {

    /**
     * Text of the command
     */
    @NonNull
    @NotBlank
    @Size(min = 1, max = 32)
    @Pattern(regexp = "^[a-z0-9_]*$")
    private String command;

    @NonNull
    @NotBlank
    @Size(min = 3, max = 256)
    private String description;

    public BotCommand() {
    }

    @NonNull
    public String getCommand() {
        return command;
    }

    public void setCommand(@NonNull String command) {
        this.command = command;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BotCommand{" +
                "command='" + command + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
