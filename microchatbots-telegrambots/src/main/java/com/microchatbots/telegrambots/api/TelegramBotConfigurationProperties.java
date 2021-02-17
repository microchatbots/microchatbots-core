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

import com.microchatbots.telegrambots.core.BotCommand;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EachProperty(TelegramBotConfigurationProperties.PREFIX)
public class TelegramBotConfigurationProperties implements TelegramBotConfiguration {
    public static final String PREFIX = "telegram.bots";

    @NonNull
    @NotBlank
    private String token;

    @NonNull
    private final String name;

    @NotBlank
    @NonNull
    private String atUsername;

    @NonNull
    private Map<String, String> commands = new HashMap<>();

    public TelegramBotConfigurationProperties(@Parameter String name) {
        this.name = name;
    }

    /**
     *
     * @return Commands
     */
    @NonNull
    public Map<String, String> getCommands() {
        return commands;
    }

    /**
     *
     * @param commands Commands
     */
    public void setCommands(@NonNull Map<String, String> commands) {
        this.commands = commands;
    }

    /**
     *
     * @return bot username prefixed with @
     */
    @NonNull
    public String getAtUsername() {
        return atUsername;
    }

    /**
     *
     * @return List of bot commands
     */
    @NonNull
    @Override
    public List<BotCommand> getBotCommands() {
        List<BotCommand> botCommands = new ArrayList<>();
        for (String command : commands.keySet()) {
            BotCommand botCommand = new BotCommand();
            botCommand.setCommand(command);
            botCommand.setDescription(commands.get(command));
            botCommands.add(botCommand);
        }
        return botCommands;
    }

    /**
     *
     * @param atUsername bot username prefixed with @
     */
    public void setAtUsername(@NonNull String atUsername) {
        this.atUsername = atUsername;
    }

    /**
     *
     * @return Telegram's token
     */
    @NonNull
    @Override
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token Telegram's token
     */
    public void setToken(@NonNull String token) {
        this.token = token;
    }

    @Override
    @NonNull
    public String getName() {
        return name;
    }
}
