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
import javax.validation.constraints.NotNull;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserProfilePhotos {

    /**
     * Unique identifier of the target user.
     */
    @JsonProperty("user_id")
    @NonNull
    @NotNull
    private Integer userId;

    /**
     * Sequential number of the first photo to be returned. By default, all photos are returned.
     */
    @Nullable
    private Integer offset;

    /**
     * Limits the number of photos to be retrieved. Values between 1—100 are accepted. Defaults to 100.
     */
    @Nullable
    private Integer limit;

    public GetUserProfilePhotos() {
    }

    /**
     *
     * @return Unique identifier of the target user.
     */
    @NonNull
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId Unique identifier of the target user.
     */
    public void setUserId(@NonNull Integer userId) {
        this.userId = userId;
    }

    /**
     *
     * @return Sequential number of the first photo to be returned. By default, all photos are returned.
     */
    @Nullable
    public Integer getOffset() {
        return offset;
    }

    /**
     *
     * @param offset Sequential number of the first photo to be returned. By default, all photos are returned.
     */
    public void setOffset(@Nullable Integer offset) {
        this.offset = offset;
    }

    /**
     *
     * @return Limits the number of photos to be retrieved. Values between 1—100 are accepted. Defaults to 100.
     */
    @Nullable
    public Integer getLimit() {
        return limit;
    }

    /**
     *
     * @param limit Limits the number of photos to be retrieved. Values between 1—100 are accepted. Defaults to 100.
     */
    public void setLimit(@Nullable Integer limit) {
        this.limit = limit;
    }
}
