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
package com.microchatbots.telegrambots.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @see <a href="https://core.telegram.org/bots/api#getupdates">getUpdates</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUpdates {

    /**
     * Identifier of the first update to be returned.
     */
    @Nullable
    private Integer offset;

    /**
     * Limits the number of updates to be returned.
     */
    @Min(1)
    @Max(100)
    @Nullable
    private Integer limit;

    /**
     * Timeout in seconds for long polling.
     */
    @Nullable
    private Integer timeout;

    /**
     * A JSON-Serialized list of the updates types you want your bot to receive. For example, specify [“message”, “edited_channel_post”, “callback_query”] to only receive updates of these types.
     */
    @JsonProperty("allowed_updates")
    @Nullable
    private List<String> allowedUpdates;

    public GetUpdates() {
    }

    /**
     *
     * @return Identifier of the first update to be returned.
     */
    @Nullable
    public Integer getOffset() {
        return offset;
    }

    /**
     *
     * @param offset Identifier of the first update to be returned.
     */
    public void setOffset(@Nullable Integer offset) {
        this.offset = offset;
    }

    /**
     *
     * @return Limits the number of updates to be returned.
     */
    @Nullable
    public Integer getLimit() {
        return limit;
    }

    /**
     *
     * @param limit Limits the number of updates to be returned.
     */
    public void setLimit(@Nullable Integer limit) {
        this.limit = limit;
    }

    /**
     *
     * @return Timeout in seconds for long polling.
     */
    @Nullable
    public Integer getTimeout() {
        return timeout;
    }

    /**
     *
     * @param timeout Timeout in seconds for long polling.
     */
    public void setTimeout(@Nullable Integer timeout) {
        this.timeout = timeout;
    }

    /**
     *
     * @return A JSON-Serialized list of the updates types you want your bot to receive.
     */
    @Nullable
    public List<String> getAllowedUpdates() {
        return allowedUpdates;
    }

    /**
     *
     * @param allowedUpdates A JSON-Serialized list of the updates types you want your bot to receive.
     */
    public void setAllowedUpdates(@Nullable List<String> allowedUpdates) {
        this.allowedUpdates = allowedUpdates;
    }
}
