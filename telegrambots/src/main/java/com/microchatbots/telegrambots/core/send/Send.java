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
package com.microchatbots.telegrambots.core.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;

import edu.umd.cs.findbugs.annotations.Nullable;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Send {

    @NonNull
    protected final String method;

    /**
     * Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     * Integer or String
     */
    @JsonProperty("chat_id")
    @NotNull
    @NonNull
    private Object chatId;

    /**
     * Sends the message silently. Users will receive a notification with no sound.
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("disable_notification")
    private Boolean disableNotification;

    /**
     * If the message is a reply, ID of the original message.
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("reply_to_message_id")
    private String replyToMessageId;

    /**
     * additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a reply from the user.
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("reply_markup")
    private String replyMarkup;

    public Send(String method) {
        this.method = method;
    }

    @NonNull
    public String getMethod() {
        return method;
    }

    @NonNull
    public Object getChatId() {
        return chatId;
    }

    public void setChatId(@NonNull Object chatId) {
        this.chatId = chatId;
    }

    /**
     *
     * @return Whether the message should be send silently. Users will receive a notification with no sound.
     */
    @Nullable
    public Boolean getDisableNotification() {
        return disableNotification;
    }

    /**
     *
     * @param disableNotification true to sends the message silently. Users will receive a notification with no sound.
     */
    public void setDisableNotification(@Nullable Boolean disableNotification) {
        this.disableNotification = disableNotification;
    }

    /**
     *
     * @return If the message is a reply, ID of the original message.
     */
    @Nullable
    public String getReplyToMessageId() {
        return replyToMessageId;
    }

    /**
     *
     * @param replyToMessageId If the message is a reply, ID of the original message.
     */
    public void setReplyToMessageId(@Nullable String replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
    }

    /**
     *
     * @return A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a reply from the user.
     */
    @Nullable
    public String getReplyMarkup() {
        return replyMarkup;
    }

    /**
     *
     * @param replyMarkup A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a reply from the user.
     */
    public void setReplyMarkup(@Nullable String replyMarkup) {
        this.replyMarkup = replyMarkup;
    }
}
