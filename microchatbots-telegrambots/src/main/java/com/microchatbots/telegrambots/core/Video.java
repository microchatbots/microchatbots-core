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
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import edu.umd.cs.findbugs.annotations.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This object represents a video file.
 * @see <a href="https://core.telegram.org/bots/api#video">Video</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Video {

    /**
     * Identifier for this file.
     */
    @JsonProperty("file_id")
    @NonNull
    @NotBlank
    private String fileId;

    /**
     * Video width as defined by sender.
     */
    @NonNull
    @NotNull
    private Integer width;

    /**
     * Video height as defined by sender.
     */
    @NonNull
    @NotNull
    private Integer height;

    /**
     * Duration of the video in seconds as defined by sender.
     */
    @NonNull
    @NotNull
    private Integer duration;

    /**
     * Video thumbnail.
     */
    @Nullable
    @Valid
    private PhotoSize thumb;

    /**
     * Mime type of a file as defined by sender.
     */
    @Nullable
    @JsonProperty("mime_type")
    private String mimeType;

    /**
     * File size.
     */
    @Nullable
    @JsonProperty("file_size")
    private Integer fileSize;

    public Video() {
    }

    /**
     *
     * @return Identifier for this file.
     */
    @NonNull
    public String getFileId() {
        return fileId;
    }

    /**
     *
     * @param fileId Identifier for this file.
     */
    public void setFileId(@NonNull String fileId) {
        this.fileId = fileId;
    }

    /**
     *
     * @return Video width as defined by sender.
     */
    @NonNull
    public Integer getWidth() {
        return width;
    }

    /**
     *
     * @param width Video width as defined by sender.
     */
    public void setWidth(@NonNull Integer width) {
        this.width = width;
    }

    /**
     *
     * @return Video height as defined by sender.
     */
    @NonNull
    public Integer getHeight() {
        return height;
    }

    /**
     *
     * @param height Video height as defined by sender.
     */
    public void setHeight(@NonNull Integer height) {
        this.height = height;
    }

    /**
     *
     * @return Duration of the video in seconds as defined by sender.
     */
    @NonNull
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @param duration Duration of the video in seconds as defined by sender.
     */
    public void setDuration(@NonNull Integer duration) {
        this.duration = duration;
    }

    /**
     *
     * @return Video thumbnail.
     */
    @Nullable
    public PhotoSize getThumb() {
        return thumb;
    }

    /**
     *
     * @param thumb Video thumbnail.
     */
    public void setThumb(@Nullable PhotoSize thumb) {
        this.thumb = thumb;
    }

    /**
     *
     * @return Mime type of a file as defined by sender.
     */
    @Nullable
    public String getMimeType() {
        return mimeType;
    }

    /**
     *
     * @param mimeType Mime type of a file as defined by sender.
     */
    public void setMimeType(@Nullable String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     *
     * @return File size.
     */
    @Nullable
    public Integer getFileSize() {
        return fileSize;
    }

    /**
     *
     * @param fileSize File size.
     */
    public void setFileSize(@Nullable Integer fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "Video{" +
                "fileId='" + fileId + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", duration=" + duration +
                ", thumb=" + (thumb != null ? thumb.toString() : "") +
                ", mimeType='" + mimeType + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
