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

import com.microchatbots.telegrambots.core.PollAnswer
import com.microchatbots.telegrambots.core.User
import io.micronaut.core.beans.BeanIntrospection

class PollAnswerSpec extends ApplicationContextSpecification {
    void "PollAnswer is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(PollAnswer)

        then:
        noExceptionThrown()
    }

    void "PollAnswer::toString() does not throw NPE"() {
        when:
        new PollAnswer().toString()

        then:
        noExceptionThrown()
    }

    void "valid PollAnswer does not trigger any constraint exception"() {
        when:
        PollAnswer el = validPollAnswer()

        then:
        validator.validate(el).isEmpty()
    }

    void "pollId is required"() {
        given:
        PollAnswer el = validPollAnswer()

        when:
        el.pollId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "user is required"() {
        given:
        PollAnswer el = validPollAnswer()

        when:
        el.user = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "optionIds are required"() {
        given:
        PollAnswer el = validPollAnswer()

        when:
        el.optionIds = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        PollAnswer el = new PollAnswer()
        el.pollId = 'xxx'
        el.user = validUser()
        el.optionIds = [1, 2]

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"poll_id":"xxx"')
        json.contains('"user"')
        json.contains('"option_ids":[1,2]')
    }

    void "snake case is used for Json serialization"() {
        given:
        PollAnswer el = new PollAnswer()
        el.pollId = 'xxx'
        el.user = validUser()
        el.optionIds = [1, 2]

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('poll_id')
        !json.contains('pollId')
        json.contains('option_ids')
        !json.contains('optionIds')
    }

    void "only non null fields are included"() {
        given:
        PollAnswer el = new PollAnswer()
        el.pollId = 'xxx'
        el.user = validUser()
        el.optionIds = [1, 2]

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('poll_id')
        json.contains('user')
        json.contains('option_ids')

        when:
        el.pollId = null
        el.user =  null
        el.optionIds =  null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('poll_id')
        !json.contains('user')
        !json.contains('option_ids')
    }

    static PollAnswer validPollAnswer() {
        PollAnswer el = new PollAnswer()
        el.pollId = 'xxx'
        el.user = validUser()
        el.optionIds = [1, 2]
        el
    }

    static User validUser() {
        User el = new User()
        el.id = 12
        el.bot = false
        el.firstName = 'Sergio'
        el
    }
}
