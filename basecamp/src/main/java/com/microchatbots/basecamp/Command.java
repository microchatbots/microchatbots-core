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
package com.microchatbots.basecamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

/**
 * @see <a href="https://github.com/basecamp/bc3-api/blob/master/sections/chatbots.md">Basecamp Chatbots</a>
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Introspected
public class Command {
    /**
     * User's query.
     */
    private String command;

    /**
     * Person who created the command.
     */
    private Creator creator;

    @JsonProperty("callback_url")
    private String callbackUrl;

    /**
     * Constructor.
     */
    public Command() {
    }

    /**
     *
     * @return User's query
     */
    public String getCommand() {
        return command;
    }

    /**
     *
     * @param command User's query
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     *
     * @return Basecamp's callback URL
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     *
     * @param callbackUrl Basecamp's callback URL
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    /**
     *
     * @return Person who created the command.
     */
    public Creator getCreator() {
        return creator;
    }

    /**
     *
     * @param creator Person who created the command.
     */
    public void setCreator(Creator creator) {
        this.creator = creator;
    }
}
