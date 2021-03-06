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

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This object represents an inline keyboard that appears right next to the message it belongs to.
 * @see <a href="https://core.telegram.org/bots/api#inlinekeyboardmarkup">InlineKeyboardMarkup</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InlineKeyboardMarkup {
    /**
     * Array of button rows, each represented by an Array of InlineKeyboardButton objects.
     */
    @NonNull
    @NotNull
    @JsonProperty("inline_keyboard")
    private List<List<InlineKeyboardButton>> inlineKeyboard;

    public InlineKeyboardMarkup() {
    }

    /**
     *
     * @return Array of button rows, each represented by an Array of InlineKeyboardButton objects.
     */
    @NonNull
    public List<List<InlineKeyboardButton>>  getInlineKeyboard() {
        return inlineKeyboard;
    }

    /**
     *
     * @param inlineKeyboard Array of button rows, each represented by an Array of InlineKeyboardButton objects.
     */
    public void setInlineKeyboard(@NonNull List<List<InlineKeyboardButton>>  inlineKeyboard) {
        this.inlineKeyboard = inlineKeyboard;
    }

    @Override
    public String toString() {
        return "InlineKeyboardMarkup{" +
                "inlineKeyboard=" + (inlineKeyboard != null ? inlineKeyboard.stream().map(keyboard -> keyboard.stream().map(InlineKeyboardButton::toString).collect(Collectors.joining(","))).collect(Collectors.joining(",")) : "") +
                '}';
    }
}
