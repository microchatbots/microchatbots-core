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
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Contains information about why a request was unsuccessful.
 * @see <a href="https://core.telegram.org/bots/api#responseparameters">ResponseParameters</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseParameters {
    /**
     * The group has been migrated to a supergroup with the specified identifier. This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
     */
    @Nullable
    @JsonProperty("migrate_to_chat_id")
    private Integer migrateToChatId;

    /**
     * In case of exceeding flood control, the number of seconds left to wait before the request can be repeated.
     */
    @Nullable
    @JsonProperty("retry_after")
    private Integer retryAfter;

    public ResponseParameters() {
    }

    /**
     *
     * @return The group has been migrated to a supergroup with the specified identifier. This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
     */
    @Nullable
    public Integer getMigrateToChatId() {
        return migrateToChatId;
    }

    /**
     *
     * @param migrateToChatId The group has been migrated to a supergroup with the specified identifier. This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
     */
    public void setMigrateToChatId(@Nullable Integer migrateToChatId) {
        this.migrateToChatId = migrateToChatId;
    }

    /**
     *
     * @return In case of exceeding flood control, the number of seconds left to wait before the request can be repeated.
     */
    @Nullable
    public Integer getRetryAfter() {
        return retryAfter;
    }

    /**
     *
     * @param retryAfter In case of exceeding flood control, the number of seconds left to wait before the request can be repeated.
     */
    public void setRetryAfter(@Nullable Integer retryAfter) {
        this.retryAfter = retryAfter;
    }

    @Override
    public String toString() {
        return "ResponseParameters{" +
                "migrateToChatId=" + migrateToChatId +
                ", retryAfter=" + retryAfter +
                '}';
    }
}


