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

import com.microchatbots.telegrambots.core.ChosenInlineResult
import com.microchatbots.telegrambots.core.Location
import com.microchatbots.telegrambots.core.User
import io.micronaut.core.beans.BeanIntrospection

class ChosenInlineResultSpec extends ApplicationContextSpecification {

    void "user is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(ChosenInlineResult)

        then:
        noExceptionThrown()
    }

    void "ChosenInlineResult::toString() does not throw NPE"() {
        when:
        new ChosenInlineResult().toString()

        then:
        noExceptionThrown()
    }

    void "valid ChosenInlineResult does not trigger any constraint exception"() {
        when:
        ChosenInlineResult el = validChosenInlineResult()

        then:
        validator.validate(el).isEmpty()
    }

    void "resultId is required"() {
        given:
        ChosenInlineResult el = validChosenInlineResult()

        when:
        el.resultId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "from is required"() {
        given:
        ChosenInlineResult el = validChosenInlineResult()

        when:
        el.from = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "location is optional"() {
        given:
        ChosenInlineResult el = validChosenInlineResult()

        when:
        el.location = null

        then:
        validator.validate(el).isEmpty()
    }

    void "inlineMessageId is optional"() {
        given:
        ChosenInlineResult el = validChosenInlineResult()

        when:
        el.inlineMessageId = null

        then:
        validator.validate(el).isEmpty()
    }

    void "query is required"() {
        given:
        ChosenInlineResult el = validChosenInlineResult()

        when:
        el.query = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        ChosenInlineResult el = new ChosenInlineResult()
        el.resultId = "xxx"
        el.from = validUser()
        el.location = validLocation()
        el.inlineMessageId = 'yyy'
        el.query = 'foo'

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"result_id":"xxx"')
        json.contains('"inline_message_id":"yyy"')
        json.contains('"query":"foo"')
        json.contains('"from"')
        json.contains('"location"')
    }

    void "snake case is used for Json serialization"() {
        given:
        ChosenInlineResult el = new ChosenInlineResult()
        el.resultId = "xxx"
        el.from = validUser()
        el.location = validLocation()
        el.inlineMessageId = 'yyy'
        el.query = 'foo'

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('resultId')
        !json.contains('inlineMessageId')
        json.contains('result_id')
        json.contains('inline_message_id')
        json.contains('query')
        json.contains('from')
        json.contains('location')
    }

    void "only non null fields are included"() {
        given:
        ChosenInlineResult el = new ChosenInlineResult()
        el.resultId = "xxx"
        el.from = validUser()
        el.location = validLocation()
        el.inlineMessageId = 'yyy'
        el.query = 'foo'

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('result_id')
        json.contains('inline_message_id')
        json.contains('query')
        json.contains('from')
        json.contains('location')

        when:
        el.resultId = null
        el.from = null
        el.location = null
        el.inlineMessageId = null
        el.query = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('result_id')
        !json.contains('inline_message_id')
        !json.contains('query')
        !json.contains('from')
        !json.contains('location')
    }

    static ChosenInlineResult validChosenInlineResult() {
        ChosenInlineResult el = new ChosenInlineResult()
        el.resultId = "xxx"
        el.from = validUser()
        el.query = 'foo'
        el
    }

    static User validUser() {
        User el = new User()
        el.id = 12
        el.bot = false
        el.firstName = 'Sergio'
        el
    }

    static Location validLocation() {
        Location el = new Location()
        el.longitude = -122.03118
        el.latitude = 37.33182
        el
    }
}
