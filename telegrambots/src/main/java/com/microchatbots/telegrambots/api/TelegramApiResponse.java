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
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TelegramApiResponse {
    @Nullable
    private Boolean ok;

    @Nullable
    private Boolean result;

    @Nullable
    private String description;

    public TelegramApiResponse() {
    }

    /**
     *
     * @return Whether the method was successful or not
     */
    @Nullable
    public Boolean getOk() {
        return ok;
    }

    /**
     *
     * @param ok Whether the method was successful or not
     */
    public void setOk(@Nullable Boolean ok) {
        this.ok = ok;
    }

    /**
     *
     * @return result of the method
     */
    @Nullable
    public Boolean getResult() {
        return result;
    }

    /**
     *
     * @param result result of the method
     */
    public void setResult(@Nullable Boolean result) {
        this.result = result;
    }

    /**
     *
     * @return description of the result
     */
    @Nullable
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description Description of the result
     */
    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TelegramApiResponse{" +
                "ok=" + ok +
                ", result=" + result +
                ", description='" + description + '\'' +
                '}';
    }
}
