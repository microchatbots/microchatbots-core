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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;
import javax.validation.constraints.NotBlank;

/**
 * This object represents a parameter of the inline keyboard button used to automatically authorize a user. Serves as a great replacement for the Telegram Login Widget when the user is coming from Telegram. All the user needs to do is tap/click a button and confirm that they want to log in:
 * @see <a href="https://core.telegram.org/bots/api#loginurl">LoginUrl</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginUrl {

    /**
     * An HTTP URL to be opened with user authorization data added to the query string when the button is pressed. If the user refuses to provide authorization data, the original URL without information about the user will be opened. The data added is the same as described in Receiving authorization data.
     *
     * NOTE: You must always check the hash of the received data to verify the authentication and the integrity of the data as described in Checking authorization.
     */
    @NonNull
    @NotBlank
    private String url;

    /**
     * New text of the button in forwarded messages.
     */
    @Nullable
    @JsonProperty("forward_text")
    private String forwardText;

    /**
     * Username of a bot, which will be used for user authorization. See Setting up a bot for more details. If not specified, the current bot's username will be assumed. The url's domain must be the same as the domain linked with the bot. See Linking your domain to the bot for more details.
     */
    @JsonProperty("bot_username")
    @Nullable
    private String botUsername;

    /**
     * Pass True to request the permission for your bot to send messages to the user.
     */
    @Nullable
    @JsonProperty("request_write_access")
    private Boolean requestWriteAccess;

    public LoginUrl() {
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @Nullable
    public String getForwardText() {
        return forwardText;
    }

    public void setForwardText(@Nullable String forwardText) {
        this.forwardText = forwardText;
    }

    @Nullable
    public String getBotUsername() {
        return botUsername;
    }

    public void setBotUsername(@Nullable String botUsername) {
        this.botUsername = botUsername;
    }

    @Nullable
    public Boolean getRequestWriteAccess() {
        return requestWriteAccess;
    }

    public void setRequestWriteAccess(@Nullable Boolean requestWriteAccess) {
        this.requestWriteAccess = requestWriteAccess;
    }

    @Override
    public String toString() {
        return "LoginUrl{" +
                "url='" + url + '\'' +
                ", forwardText='" + forwardText + '\'' +
                ", botUsername='" + botUsername + '\'' +
                ", requestWriteAccess=" + requestWriteAccess +
                '}';
    }
}
