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
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Represents an animation file (GIF or H.264/MPEG-4 AVC video without sound) to be sent.
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputMediaAnimation extends InputMediaDocument {
    private static final String TYPE_ANIMATION = "animation";

    /**
     * Animation width.
     */
    @Nullable
    private Integer width;

    /**
     * Animation height.
     */
    @Nullable
    private Integer height;

    /**
     * Animation duration.
     */
    @Nullable
    private Integer duration;

    public InputMediaAnimation() {
        super(TYPE_ANIMATION);
    }

    /**
     *
     * @return Animation width.
     */
    @Nullable
    public Integer getWidth() {
        return width;
    }

    /**
     *
     * @param width Animation width.
     */
    public void setWidth(@Nullable Integer width) {
        this.width = width;
    }

    /**
     *
     * @return Animation height.
     */
    @Nullable
    public Integer getHeight() {
        return height;
    }

    /**
     *
     * @param height Animation height.
     */
    public void setHeight(@Nullable Integer height) {
        this.height = height;
    }

    /**
     *
     * @return Animation duration.
     */
    @Nullable
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @param duration Animation duration.
     */
    public void setDuration(@Nullable Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "InputMediaAnimation{" +
                "width=" + width +
                ", height=" + height +
                ", duration=" + duration +
                super.toString() +
                '}';
    }
}
