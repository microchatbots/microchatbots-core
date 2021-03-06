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
package com.microchatbots.telegrambots.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForwardMessage {
    /**
     * Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     */
    @JsonProperty("chat_id")
    @NonNull
    @NotBlank
    private String chatId;

    /**
     * Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername).
     */
    @JsonProperty("from_chat_id")
    @NonNull
    @NotBlank
    private String fromChatId;

    /**
     * Sends the message silently. Users will receive a notification with no sound.
     */
    @JsonProperty("disable_notification")
    @Nullable
    private Boolean disableNotification;

    /**
     * Message identifier in the chat specified in from_chat_id.
     */
    @JsonProperty("message_id")
    @NonNull
    @NotNull
    private Integer messageId;

    public ForwardMessage() {
    }

    /**
     *
     * @return Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     */
    @NonNull
    public String getChatId() {
        return chatId;
    }

    /**
     *
     * @param chatId Unique identifier for the target chat or username of the target channel (in the format @channelusername).
     */
    public void setChatId(@NonNull String chatId) {
        this.chatId = chatId;
    }

    /**
     *
     * @return Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername).
     */
    @NonNull
    public String getFromChatId() {
        return fromChatId;
    }

    /**
     *
     * @param fromChatId Unique identifier for the chat where the original message was sent (or channel username in the format @channelusername).
     */
    public void setFromChatId(@NonNull String fromChatId) {
        this.fromChatId = fromChatId;
    }

    /**
     *
     * @return Sends the message silently. Users will receive a notification with no sound.
     */
    @Nullable
    public Boolean getDisableNotification() {
        return disableNotification;
    }

    /**
     *
     * @param disableNotification Sends the message silently. Users will receive a notification with no sound.
     */
    public void setDisableNotification(@Nullable Boolean disableNotification) {
        this.disableNotification = disableNotification;
    }

    /**
     *
     * @return Unique message identifier inside this chat.
     */
    @NonNull
    public Integer getMessageId() {
        return messageId;
    }

    /**
     *
     * @param messageId Unique message identifier inside this chat.
     */
    public void setMessageId(@NonNull Integer messageId) {
        this.messageId = messageId;
    }
}
