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
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This object represents a Telegram user or bot.
 * <a href="https://core.telegram.org/bots/api#user">User</a>
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    /**
     * Unique identifier for this user or bot
     */
    @NonNull
    @NotNull
    private Integer id;

    /**
     * True, if this user is a bot
     */
    @JsonProperty("is_bot")
    @NonNull
    @NotNull
    private Boolean bot;

    /**
     * User‘s or bot’s first name
     */
    @JsonProperty("first_name")
    @NonNull
    @NotBlank
    private String firstName;

    /**
     * User‘s or bot’s last name
     */
    @JsonProperty("last_name")
    @Nullable
    private String lastName;

    /**
     * User‘s or bot’s username
     */
    @Nullable
    private String username;

    /**
     * IETF language tag of the user's language
     * <a href="https://en.wikipedia.org/wiki/IETF_language_tag">IETF language tag</a>
     */
    @Nullable
    @JsonProperty("language_code")
    private String languageCode;

    /**
     * True, if the bot can be invited to groups. Returned only in getMe.
     */
    @Nullable
    @JsonProperty("can_join_groups")
    private Boolean canJoinGroups;

    /**
     * True, if privacy mode is disabled for the bot. Returned only in getMe.
     */
    @Nullable
    @JsonProperty("can_read_all_group_messages")
    private Boolean canReadAllGroupMessages;

    /*
     * True, if the bot supports inline queries. Returned only in getMe.
     */
    @Nullable
    @JsonProperty("supports_inline_queries")
    private Boolean supportsInlineQueries;

    public User() {

    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Boolean getBot() {
        return bot;
    }

    public void setBot(@NonNull Boolean bot) {
        this.bot = bot;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    @Nullable
    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(@Nullable String languageCode) {
        this.languageCode = languageCode;
    }

    @Nullable
    public Boolean getCanJoinGroups() {
        return canJoinGroups;
    }

    public void setCanJoinGroups(@Nullable Boolean canJoinGroups) {
        this.canJoinGroups = canJoinGroups;
    }

    @Nullable
    public Boolean getCanReadAllGroupMessages() {
        return canReadAllGroupMessages;
    }

    public void setCanReadAllGroupMessages(@Nullable Boolean canReadAllGroupMessages) {
        this.canReadAllGroupMessages = canReadAllGroupMessages;
    }

    @Nullable
    public Boolean getSupportsInlineQueries() {
        return supportsInlineQueries;
    }

    public void setSupportsInlineQueries(@Nullable Boolean supportsInlineQueries) {
        this.supportsInlineQueries = supportsInlineQueries;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", bot=" + bot +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", canJoinGroups=" + canJoinGroups +
                ", canReadAllGroupMessages=" + canReadAllGroupMessages +
                ", supportsInlineQueries=" + supportsInlineQueries +
                '}';
    }
}
