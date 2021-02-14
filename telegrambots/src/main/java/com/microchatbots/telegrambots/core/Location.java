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
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;

/**
 * This object represents a point on the map.
 * @see <a href="https://core.telegram.org/bots/api#location">Location</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {

    /**
     * Longitude as defined by sender.
     */
    @NonNull
    @NotNull
    private Float longitude;

    /**
     * Latitude as defined by sender.
     */
    @NonNull
    @NotNull
    private Float latitude;

    public Location() {
    }

    /**
     *
     * @return Longitude as defined by sender.
     */
    @NonNull
    public Float getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude Longitude as defined by sender.
     */
    public void setLongitude(@NonNull Float longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return Latitude as defined by sender.
     */
    @NonNull
    public Float getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude Latitude as defined by sender.
     */
    public void setLatitude(@NonNull Float latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
