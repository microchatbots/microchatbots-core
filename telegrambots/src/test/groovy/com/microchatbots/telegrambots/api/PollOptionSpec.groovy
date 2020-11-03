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

import com.microchatbots.telegrambots.core.PollOption
import io.micronaut.core.beans.BeanIntrospection

class PollOptionSpec extends ApplicationContextSpecification {
    void "PollOption is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(PollOption)

        then:
        noExceptionThrown()
    }

    void "PollOption::toString() does not throw NPE"() {
        when:
        new PollOption().toString()

        then:
        noExceptionThrown()
    }

    void "valid PollOption does not trigger any constraint exception"() {
        when:
        PollOption el = validPollOption()

        then:
        validator.validate(el).isEmpty()
    }

    void "text is required"() {
        given:
        PollOption el = validPollOption()

        when:
        el.text = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "voterCount is required"() {
        given:
        PollOption el = validPollOption()

        when:
        el.voterCount = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        PollOption el = new PollOption()
        el.text = 'Micronaut'
        el.voterCount = 1000

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"voter_count":1000')
        json.contains('"text":"Micronaut"')
    }

    void "snake case is used for Json serialization"() {
        given:
        PollOption el = new PollOption()
        el.text = 'Micronaut'
        el.voterCount = 1000

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('voter_count')
        json.contains('text')
        !json.contains('voterCount')
    }

    void "only non null fields are included"() {
        given:
        PollOption el = new PollOption()
        el.text = 'Micronaut'
        el.voterCount = 1000

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('voter_count')
        json.contains('text')

        when:
        el.voterCount = null
        el.text = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('voter_count')
        !json.contains('text')
    }

    static PollOption validPollOption() {
        PollOption el = new PollOption()
        el.text = 'Micronaut'
        el.voterCount = 1000
        el
    }
}
