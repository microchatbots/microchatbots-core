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
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This object represents one special entity in a text message. For example, hashtags, usernames, URLs, etc.
 * @see <a href="https://core.telegram.org/bots/api#messageentity">MessageEntity</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageEntity {

    /**
     * Type of the entity. Can be mention (@username), hashtag, cashtag, bot_command, url, email, phone_number, bold (bold text), italic (italic text), code (monowidth string), pre (monowidth block), text_link (for clickable text URLs), text_mention (for users without usernames).
     */
    @NonNull
    @NotBlank
    private String type;

    /**
     * Offset in UTF-16 code units to the start of the entity.
     */
    @NonNull
    @NotNull
    private Integer offset;

    /**
     * Length of the entity in UTF-16 code units.
     */
    @NonNull
    @NotNull
    private Integer length;

    /**
     * For “text_link” only, url that will be opened after user taps on the text.
     */
    @Nullable
    private String url;

    /**
     * For “text_mention” only, the mentioned user.
     */
    @Nullable
    @Valid
    private User user;

    public MessageEntity() {
    }

    /**
     *
     * @return Type of the entity.
     */
    @NonNull
    public String getType() {
        return type;
    }

    /**
     *
     * @param type Type of the entity.
     */
    public void setType(@NonNull String type) {
        this.type = type;
    }

    /**
     *
     * @return Offset in UTF-16 code units to the start of the entity.
     */
    @NonNull
    public Integer getOffset() {
        return offset;
    }

    /**
     *
     * @param offset Offset in UTF-16 code units to the start of the entity.
     */
    public void setOffset(@NonNull Integer offset) {
        this.offset = offset;
    }

    /**
     *
     * @return Length of the entity in UTF-16 code units.
     */
    @NonNull
    public Integer getLength() {
        return length;
    }

    /**
     *
     * @param length Length of the entity in UTF-16 code units.
     */
    public void setLength(@NonNull Integer length) {
        this.length = length;
    }

    /**
     *
     * @return For “text_link” only, url that will be opened after user taps on the text.
     */
    @Nullable
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url For “text_link” only, url that will be opened after user taps on the text.
     */
    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    /**
     *
     * @return For “text_mention” only, the mentioned user.
     */
    @Nullable
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user For “text_mention” only, the mentioned user.
     */
    public void setUser(@Nullable User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "type='" + type + '\'' +
                ", offset=" + offset +
                ", length=" + length +
                ", url='" + url + '\'' +
                ", user=" + (user != null ? user.toString() : "") +
                '}';
    }
}
