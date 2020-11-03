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
import javax.validation.constraints.NotNull;

/**
 * @see <a href="https://core.telegram.org/bots/api#sticker">Sticker</a>
 * This object represents a sticker.
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sticker {
    /**
     * Identifier for this file, which can be used to download or reuse the file
     */
    @NonNull
    @NotBlank
    @JsonProperty("file_id")
    private String fileId;

    /**
     * Unique identifier for this file, which is supposed to be the same over time and for different bots. Can't be used to download or reuse the file.
     */
    @NonNull
    @NotBlank
    @JsonProperty("file_unique_id")
    private String fileUniqueId;

    /**
     * Sticker width
     */
    @NonNull
    @NotNull
    private Integer width;

    /**
     * Sticker height
     */
    @NonNull
    @NotNull
    private Integer height;

    /**
     * True, if the sticker is animated
     */
    @JsonProperty("is_animated")
    @NonNull
    @NotNull
    private Boolean animated;

    /**
     * Sticker thumbnail in the .webp or .jpg format.
     */
    @Nullable
    private PhotoSize thumb;

    /**
     * Emoji associated with the sticker
     */
    @Nullable
    private String emoji;

    /**
     * Name of the sticker set to which the sticker belongs
     */
    @Nullable
    @JsonProperty("set_name")
    private String setName;

    /**
     * For mask stickers, the position where the mask should be placed
     */
    @Nullable
    @JsonProperty("mask_position")
    private MaskPosition maskPosition;

    /**
     * File size
     */
    @JsonProperty("file_size")
    @Nullable
    private Integer fileSize;

    public Sticker() {
    }

    @NonNull
    public String getFileId() {
        return fileId;
    }

    public void setFileId(@NonNull String fileId) {
        this.fileId = fileId;
    }

    @NonNull
    public String getFileUniqueId() {
        return fileUniqueId;
    }

    public void setFileUniqueId(@NonNull String fileUniqueId) {
        this.fileUniqueId = fileUniqueId;
    }

    @NonNull
    public Integer getWidth() {
        return width;
    }

    public void setWidth(@NonNull Integer width) {
        this.width = width;
    }

    @NonNull
    public Integer getHeight() {
        return height;
    }

    public void setHeight(@NonNull Integer height) {
        this.height = height;
    }

    @NonNull
    public Boolean isAnimated() {
        return animated;
    }

    public void setAnimated(@NonNull Boolean animated) {
        this.animated = animated;
    }

    @Nullable
    public PhotoSize getThumb() {
        return thumb;
    }

    public void setThumb(@Nullable PhotoSize thumb) {
        this.thumb = thumb;
    }

    @Nullable
    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(@Nullable String emoji) {
        this.emoji = emoji;
    }

    @Nullable
    public String getSetName() {
        return setName;
    }

    public void setSetName(@Nullable String setName) {
        this.setName = setName;
    }

    @Nullable
    public MaskPosition getMaskPosition() {
        return maskPosition;
    }

    public void setMaskPosition(@Nullable MaskPosition maskPosition) {
        this.maskPosition = maskPosition;
    }

    @Nullable
    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(@Nullable Integer fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "Sticker{" +
                "fileId='" + fileId + '\'' +
                ", fileUniqueId='" + fileUniqueId + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", isAnimated=" + animated +
                ", thumb=" + (thumb != null ? thumb.toString() : "") +
                ", emoji='" + emoji  + '\'' +
                ", setName='" + setName + '\'' +
                ", maskPosition=" + (maskPosition != null ? maskPosition.toString() : "") +
                ", fileSize=" + fileSize +
                '}';
    }
}
