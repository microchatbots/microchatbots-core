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
 * Represents a video to be sent.
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputMediaVideo extends InputMediaDocument {
    private static final String TYPE_VIDEO = "video";

    /**
     * Video width.
     */
    @Nullable
    private Integer width;

    /**
     * Video height.
     */
    @Nullable
    private Integer height;

    /**
     * Video duration.
     */
    @Nullable
    private Integer duration;

    /**
     *  Pass True, if the uploaded video is suitable for streaming.
     */
    @Nullable
    @JsonProperty("supports_streaming")
    private Boolean supportsStreaming;

    public InputMediaVideo() {
        super(TYPE_VIDEO);
    }

    @Nullable
    public Integer getWidth() {
        return width;
    }

    public void setWidth(@Nullable Integer width) {
        this.width = width;
    }

    @Nullable
    public Integer getHeight() {
        return height;
    }

    public void setHeight(@Nullable Integer height) {
        this.height = height;
    }

    @Nullable
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(@Nullable Integer duration) {
        this.duration = duration;
    }

    @Nullable
    public Boolean getSupportsStreaming() {
        return supportsStreaming;
    }

    public void setSupportsStreaming(@Nullable Boolean supportsStreaming) {
        this.supportsStreaming = supportsStreaming;
    }

    @Override
    public String toString() {
        return "InputMediaVideo{" +
                "width=" + width +
                ", height=" + height +
                ", duration=" + duration +
                ", supportsStreaming=" + supportsStreaming +
                '}';
    }
}
