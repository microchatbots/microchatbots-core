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
 * Use this method to send answers to callback queries sent from inline keyboards. The answer will be displayed to the user as a notification at the top of the chat screen or as an alert. On success, True is returned.
 * @see <a href="https://core.telegram.org/bots/api#answercallbackquery">answerCallbackQuery</a>.
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerCallbackQuery {

    /**
     * Unique identifier for the query to be answered.
     */
    @JsonProperty("callback_query_id")
    @NonNull
    @NotBlank
    private String callbackQueryId;

    /**
     * Text of the notification. If not specified, nothing will be shown to the user, 0-200 characters,
     */
    @Nullable
    private String text;

    /**
     * If true, an alert will be shown by the client instead of a notification at the top of the chat screen. Defaults to false.
     */
    @Nullable
    @JsonProperty("show_alert")
    private Boolean showAlert;

    /**
     * URL that will be opened by the user's client. If you have created a Game and accepted the conditions via @Botfather, specify the URL that opens your game – note that this will only work if the query comes from a callback_game button.
     *
     * Otherwise, you may use links like t.me/your_bot?start=XXXX that open your bot with a parameter.
     */
    @Nullable
    private String url;

    /**
     * The maximum amount of time in seconds that the result of the callback query may be cached client-side. Telegram apps will support caching starting in version 3.14. Defaults to 0.
     */
    @JsonProperty("cache_time")
    @Nullable
    private Integer cacheTime;

    public AnswerCallbackQuery() {
    }

    public String getCallbackQueryId() {
        return callbackQueryId;
    }

    public void setCallbackQueryId(String callbackQueryId) {
        this.callbackQueryId = callbackQueryId;
    }

    @Nullable
    public String getText() {
        return text;
    }

    public void setText(@Nullable String text) {
        this.text = text;
    }

    @Nullable
    public Boolean getShowAlert() {
        return showAlert;
    }

    public void setShowAlert(@Nullable Boolean showAlert) {
        this.showAlert = showAlert;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public Integer getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(@Nullable Integer cacheTime) {
        this.cacheTime = cacheTime;
    }
}
