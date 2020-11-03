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
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendPoll extends Send {

    /**
     * Poll question, 1-255 characters.
     */
    @NonNull
    @NotBlank
    private String question;

    /**
     * List of answer options, 2-10 strings 1-100 characters each
     */
    @NonNull
    @NotNull
    private List<String> options;

    /**
     * True, if the poll needs to be anonymous, defaults to True.
     */
    @JsonProperty("is_anonymous")
    @Nullable
    private Boolean isAnonymous;

    /**
     * Poll type, “quiz” or “regular”, defaults to “regular”.
     */
    @Nullable
    private String type;

    /**
     * True, if the poll allows multiple answers, ignored for polls in quiz mode, defaults to False.
     */
    @Nullable
    @JsonProperty("allows_multiple_answers")
    private Boolean allowsMultipleAnswers;

    /**
     * 0-based identifier of the correct answer option, required for polls in quiz mode.
     */
    @Nullable
    @JsonProperty("correct_option_id")
    private Integer correctOptionId;

    /**
     * Pass True, if the poll needs to be immediately closed.
     */
    @Nullable
    @JsonProperty("is_closed")
    private Boolean isClosed;

    public SendPoll() {
        super("sendPoll");
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }

    @NonNull
    public List<String> getOptions() {
        return options;
    }

    public void setOptions(@NonNull List<String> options) {
        this.options = options;
    }

    @Nullable
    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(@Nullable Boolean anonymous) {
        isAnonymous = anonymous;
    }

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(@Nullable String type) {
        this.type = type;
    }

    @Nullable
    public Boolean getAllowsMultipleAnswers() {
        return allowsMultipleAnswers;
    }

    public void setAllowsMultipleAnswers(@Nullable Boolean allowsMultipleAnswers) {
        this.allowsMultipleAnswers = allowsMultipleAnswers;
    }

    @Nullable
    public Integer getCorrectOptionId() {
        return correctOptionId;
    }

    public void setCorrectOptionId(@Nullable Integer correctOptionId) {
        this.correctOptionId = correctOptionId;
    }

    @Nullable
    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(@Nullable Boolean closed) {
        isClosed = closed;
    }
}
