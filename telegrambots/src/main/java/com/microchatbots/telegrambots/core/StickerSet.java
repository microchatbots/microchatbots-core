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
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see <a href="https://core.telegram.org/bots/api#stickerset">StickerSet</a>
 * This object represents a sticker.
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StickerSet {
    /**
     * Sticker set name
     */
    @NonNull
    @NotBlank
    private String name;

    /**
     * Sticker set title
     */
    @NonNull
    @NotBlank
    private String title;

    /**
     * True, if the sticker set contains animated stickers
     */
    @JsonProperty("is_animated")
    @NonNull
    @NotNull
    private Boolean animated;

    /**
     * True, if the sticker set contains masks
     */
    @JsonProperty("contains_masks")
    @NonNull
    @NotNull
    private Boolean containsMasks;

    /**
     * List of all set stickers
     */
    @NonNull
    @NotNull
    private List<@Valid Sticker> stickers;

    /**
     * Sticker set thumbnail in the .WEBP or .TGS format
     */
    @Nullable
    private PhotoSize thumb;

    public StickerSet() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public Boolean getAnimated() {
        return animated;
    }

    public void setAnimated(@NonNull Boolean animated) {
        this.animated = animated;
    }

    @NonNull
    public Boolean getContainsMasks() {
        return containsMasks;
    }

    public void setContainsMasks(@NonNull Boolean containsMasks) {
        this.containsMasks = containsMasks;
    }

    @NonNull
    public List<Sticker> getStickers() {
        return stickers;
    }

    public void setStickers(@NonNull List<Sticker> stickers) {
        this.stickers = stickers;
    }

    @Nullable
    public PhotoSize getThumb() {
        return thumb;
    }

    public void setThumb(@Nullable PhotoSize thumb) {
        this.thumb = thumb;
    }

    @Override
    public String toString() {
        return "StickerSet{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", animated=" + animated +
                ", containsMasks=" + containsMasks +
                ", stickers=" + (stickers != null ? stickers.stream().map(Sticker::toString).collect(Collectors.joining(",")) : "") +
                ", thumb=" + (thumb != null ? thumb.toString() : "") +
                '}';
    }
}
