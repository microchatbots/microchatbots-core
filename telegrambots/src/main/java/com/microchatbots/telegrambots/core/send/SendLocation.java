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
package com.microchatbots.telegrambots.core.send;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @see <a href="https://core.telegram.org/bots/api#sendlocation">SendAudio</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendLocation extends Send {

    public static final String SEND_LOCATION = "sendLocation";

    /**
     * Longitude of the location
     */
    @NonNull
    @NotNull
    private Float longitude;

    /**
     * Latitude of the location
     *
     */
    @NonNull
    @NotNull
    private Float latitude;

    /**
     * Period in seconds for which the location will be updated
     */
    @Nullable
    @JsonProperty("live_period")
    private Integer livePeriod;

    public SendLocation() {
        super(SEND_LOCATION);
    }

    @NonNull
    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(@NonNull Float longitude) {
        this.longitude = longitude;
    }

    @NonNull
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(@NonNull Float latitude) {
        this.latitude = latitude;
    }

    @Nullable
    public Integer getLivePeriod() {
        return livePeriod;
    }

    public void setLivePeriod(@Nullable Integer livePeriod) {
        this.livePeriod = livePeriod;
    }
}
