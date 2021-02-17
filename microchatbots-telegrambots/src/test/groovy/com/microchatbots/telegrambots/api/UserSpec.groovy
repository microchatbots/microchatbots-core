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

import com.microchatbots.telegrambots.core.User
import io.micronaut.core.beans.BeanIntrospection

class UserSpec extends ApplicationContextSpecification {

    void "user is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(User)

        then:
        noExceptionThrown()
    }

    void "User::toString() does not throw NPE"() {
        when:
        new User().toString()

        then:
        noExceptionThrown()
    }

    void "valid User does not trigger any constraint exception"() {
        when:
        User el = validUser()

        then:
        validator.validate(el).isEmpty()
    }

    void "id is required"() {
        given:
        User el = validUser()

        when:
        el.id = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "bot is required"() {
        given:
        User el = validUser()

        when:
        el.bot = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "firstName is required"() {
        given:
        User el = validUser()

        when:
        el.firstName = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "lastName is optional"() {
        given:
        User el = validUser()

        when:
        el.lastName = null

        then:
        validator.validate(el).isEmpty()
    }

    void "username is optional"() {
        given:
        User el = validUser()

        when:
        el.username = null

        then:
        validator.validate(el).isEmpty()
    }

    void "languageCode is optional"() {
        given:
        User el = validUser()

        when:
        el.languageCode = null

        then:
        validator.validate(el).isEmpty()
    }

    void "canJoinGroups is optional"() {
        given:
        User el = validUser()

        when:
        el.canJoinGroups = null

        then:
        validator.validate(el).isEmpty()
    }

    void "canReadAllGroupMessages is optional"() {
        given:
        User el = validUser()

        when:
        el.canReadAllGroupMessages = null

        then:
        validator.validate(el).isEmpty()
    }

    void "supportsInlineQueries is optional"() {
        given:
        User el = validUser()

        when:
        el.supportsInlineQueries = null

        then:
        validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        User el = new User()
        el.id = 12
        el.bot = false
        el.firstName = 'Sergio'
        el.lastName = 'del Amo'
        el.username = 'sdelamo'
        el.languageCode = 'es_ES'
        el.canJoinGroups = true
        el.canReadAllGroupMessages = false
        el.supportsInlineQueries = false

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"supports_inline_queries":false')
        json.contains('"can_read_all_group_messages":false')
        json.contains('"can_join_groups":true')
        json.contains('"language_code":"es_ES"')
        json.contains('"username":"sdelamo"')
        json.contains('"last_name":"del Amo"')
        json.contains('"first_name":"Sergio"')
        json.contains('"is_bot":false')
        json.contains('"id":12')
    }

    void "snake case is used for Json serialization"() {
        given:
        User el = new User()
        el.id = 12
        el.bot = false
        el.firstName = 'Sergio'
        el.lastName = 'del Amo'
        el.username = 'sdelamo'
        el.languageCode = 'es_ES'
        el.canJoinGroups = true
        el.canReadAllGroupMessages = false
        el.supportsInlineQueries = false

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('supports_inline_queries')
        json.contains('can_read_all_group_messages')
        json.contains('can_join_groups')
        json.contains('language_code')
        json.contains('username')
        json.contains('last_name')
        json.contains('first_name')
        json.contains('is_bot')
        json.contains('id')
        !json.contains('supportsInlineQueries')
        !json.contains('canReadAllGroupMessages')
        !json.contains('canJoinGroups')
        !json.contains('languageCode')
        !json.contains('lastName')
        !json.contains('firstName')
        !json.contains('isBot')
    }

    void "only non null fields are included"() {
        given:
        User el = new User()
        el.id = 12
        el.bot = false
        el.firstName = 'Sergio'
        el.lastName = 'del Amo'
        el.username = 'sdelamo'
        el.languageCode = 'es_ES'
        el.canJoinGroups = true
        el.canReadAllGroupMessages = false
        el.supportsInlineQueries = false

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('supports_inline_queries')
        json.contains('can_read_all_group_messages')
        json.contains('can_join_groups')
        json.contains('language_code')
        json.contains('username')
        json.contains('last_name')
        json.contains('first_name')
        json.contains('is_bot')
        json.contains('id')

        when:
        el.lastName = null
        el.username = null
        el.languageCode = null
        el.canJoinGroups = null
        el.canReadAllGroupMessages = null
        el.supportsInlineQueries = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('supports_inline_queries')
        !json.contains('can_read_all_group_messages')
        !json.contains('can_join_groups')
        !json.contains('language_code')
        !json.contains('username')
        !json.contains('last_name')
        json.contains('first_name')
        json.contains('is_bot')
        json.contains('id')
    }

    static User validUser() {
        User el = new User()
        el.id = 12
        el.bot = false
        el.firstName = 'Sergio'
        el
    }
}
