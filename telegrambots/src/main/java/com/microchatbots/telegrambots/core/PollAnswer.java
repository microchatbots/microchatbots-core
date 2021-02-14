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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This object represents an answer of a user in a non-anonymous poll.
 * @see <a href="https://core.telegram.org/bots/api#pollanswer">Poll Answer</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PollAnswer {
    /**
     * Unique poll identifier.
     */
    @NotBlank
    @NonNull
    @JsonProperty("poll_id")
    private String pollId;

    /**
     * The user, who changed the answer to the poll.
     */
    @NonNull
    @NotNull
    @Valid
    private User user;

    /**
     * 0-based identifiers of answer options, chosen by the user. May be empty if the user retracted their vote.
     */
    @NonNull
    @NotNull
    @JsonProperty("option_ids")
    private List<Integer> optionIds;

    public PollAnswer() {
    }

    /**
     *
     * @return Unique poll identifier.
     */
    @NonNull
    public String getPollId() {
        return pollId;
    }

    /**
     *
     * @param pollId Unique poll identifier.
     */
    public void setPollId(@NonNull String pollId) {
        this.pollId = pollId;
    }

    /**
     *
     * @return The user, who changed the answer to the poll.
     */
    @NonNull
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user The user, who changed the answer to the poll.
     */
    public void setUser(@NonNull User user) {
        this.user = user;
    }

    /**
     *
     * @return 0-based identifiers of answer options, chosen by the user. May be empty if the user retracted their vote.
     */
    @NonNull
    public List<Integer> getOptionIds() {
        return optionIds;
    }

    /**
     *
     * @param optionIds 0-based identifiers of answer options, chosen by the user. May be empty if the user retracted their vote.
     */
    public void setOptionIds(@NonNull List<Integer> optionIds) {
        this.optionIds = optionIds;
    }

    @Override
    public String toString() {
        return "PollAnswer{" +
                "pollId='" + pollId + '\'' +
                ", user=" + (user != null ? user.toString() : "") +
                ", optionIds=" + optionIds +
                '}';
    }
}
