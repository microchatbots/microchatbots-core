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
package com.microchatbots.telegrambots.api

import com.microchatbots.telegrambots.core.Location
import io.micronaut.core.beans.BeanIntrospection

class LocationSpec extends ApplicationContextSpecification {

    void "Location is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(Location)

        then:
        noExceptionThrown()
    }

    void "Location::toString() does not throw NPE"() {
        when:
        new Location().toString()

        then:
        noExceptionThrown()
    }

    void "valid Location does not trigger any constraint exception"() {
        when:
        Location el = validLocation()

        then:
        validator.validate(el).isEmpty()
    }

    void "latitude is required"() {
        given:
        Location el = validLocation()

        when:
        el.latitude = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "longitude is required"() {
        given:
        Location el = validLocation()

        when:
        el.longitude = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        Location el = new Location()
        el.longitude = -122.03118
        el.latitude = 37.33182

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"longitude":-122.03118')
        json.contains('"latitude":37.33182')
    }

    void "only non null fields are included"() {
        given:
        Location el = new Location()
        el.longitude = -122.03118
        el.latitude = 37.33182

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('longitude')
        json.contains('latitude')

        when:
        el.latitude = null
        el.longitude = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('longitude')
        !json.contains('latitude')
    }

    static Location validLocation() {
        Location el = new Location()
        el.longitude = -122.03118
        el.latitude = 37.33182
        el
    }
}
