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
package com.microchatbots.telegrambots.api

import com.microchatbots.telegrambots.core.Location
import com.microchatbots.telegrambots.core.Venue
import io.micronaut.core.beans.BeanIntrospection

class VenueSpec extends ApplicationContextSpecification {

    void "Venue is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(Venue)

        then:
        noExceptionThrown()
    }

    void "Venue::toString() does not throw NPE"() {
        when:
        new Venue().toString()

        then:
        noExceptionThrown()
    }

    void "valid Venue does not trigger any constraint exception"() {
        when:
        Venue el = validVenue()

        then:
        validator.validate(el).isEmpty()
    }

    void "location is required"() {
        given:
        Venue el = validVenue()

        when:
        el.location = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "title is required"() {
        given:
        Venue el = validVenue()

        when:
        el.title = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "address is required"() {
        given:
        Venue el = validVenue()

        when:
        el.address = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "foursquareId is optional"() {
        given:
        Venue el = validVenue()

        when:
        el.foursquareId = null

        then:
        validator.validate(el).isEmpty()
    }

    void "foursquareType is optional"() {
        given:
        Venue el = validVenue()

        when:
        el.foursquareType = null

        then:
        validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        Location loc = new Location()
        loc.longitude = -122.03118
        loc.latitude = 37.33182
        Venue el = new Venue()
        el.location = loc
        el.address = '1 Infinite Loop; Cupertino, CA 95014'
        el.title = 'Apple Campus'
        el.foursquareType = 'arts_entertainment/default'
        el.foursquareId = 'xxx'

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"location"')
        json.contains('"address":"1 Infinite Loop; Cupertino, CA 95014"')
        json.contains('"title":"Apple Campus"')
        json.contains('"foursquare_type":"arts_entertainment/default"')
        json.contains('"foursquare_id":"xxx"')
    }

    void "only non null fields are included"() {
        given:
        Location loc = new Location()
        loc.longitude = -122.03118
        loc.latitude = 37.33182
        Venue el = new Venue()
        el.location = loc
        el.address = '1 Infinite Loop; Cupertino, CA 95014'
        el.title = 'Apple Campus'
        el.foursquareType = 'arts_entertainment/default'
        el.foursquareId = 'xxx'

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('location')
        json.contains('address')
        json.contains('title')
        json.contains("foursquare_type")
        json.contains("foursquare_id")

        when:
        el.location = null
        el.address = null
        el.title = null
        el.foursquareType = null
        el.foursquareId = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('location')
        !json.contains('address')
        !json.contains('title')
        !json.contains("foursquare_type")
        !json.contains("foursquare_id")
    }

    static Venue validVenue() {
        Location loc = new Location()
        loc.longitude = -122.03118
        loc.latitude = 37.33182
        Venue el = new Venue()
        el.location = loc
        el.address = '1 Infinite Loop; Cupertino, CA 95014'
        el.title = 'Apple Campus'
        el
    }
}
