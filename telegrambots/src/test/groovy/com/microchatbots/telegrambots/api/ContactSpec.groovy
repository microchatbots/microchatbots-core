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

import com.microchatbots.telegrambots.core.Contact
import io.micronaut.core.beans.BeanIntrospection

class ContactSpec extends ApplicationContextSpecification {
    void "Contact is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(Contact)

        then:
        noExceptionThrown()
    }

    void "Contact::toString() does not throw NPE"() {
        when:
        new Contact().toString()

        then:
        noExceptionThrown()
    }

    void "valid Contact does not trigger any constraint exception"() {
        when:
        Contact el = validContact()

        then:
        validator.validate(el).isEmpty()
    }

    void "phoneNumber is required"() {
        given:
        Contact el = validContact()

        when:
        el.phoneNumber = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "firstName is required"() {
        given:
        Contact el = validContact()

        when:
        el.firstName = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "lastName is optional"() {
        given:
        Contact el = validContact()

        when:
        el.lastName = null

        then:
        validator.validate(el).isEmpty()
    }

    void "userId is optional"() {
        given:
        Contact el = validContact()

        when:
        el.userId = null

        then:
        validator.validate(el).isEmpty()
    }

    void "vcard is optional"() {
        given:
        Contact el = validContact()

        when:

        el.vcard = null

        then:
        validator.validate(el).isEmpty()
    }


    void "values are present in json"() {
        given:
        Contact el = new Contact()
        el.phoneNumber = '+34630444444'
        el.firstName = 'Sergio'
        el.lastName = 'del Amo'
        el.userId = 12345
        el.vcard = 'BEGIN:VCARD'

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"phone_number":"+34630444444"')
        json.contains('"first_name":"Sergio"')
        json.contains('"last_name":"del Amo"')
        json.contains('"user_id":12345')
        json.contains('"vcard":"BEGIN:VCARD"')
    }

    void "snake case is used for Json serialization"() {
        given:
        Contact el = new Contact()
        el.phoneNumber = '+34630444444'
        el.firstName = 'Sergio'
        el.lastName = 'del Amo'
        el.userId = 12345
        el.vcard = 'BEGIN:VCARD'

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('phone_number')
        json.contains('first_name')
        json.contains('last_name')
        json.contains('user_id')
        !json.contains('phoneNumber')
        !json.contains('firstName')
        !json.contains('lastName')
        !json.contains('userId')
    }

    void "only non null fields are included"() {
        given:
        Contact el = new Contact()
        el.phoneNumber = '+34630444444'
        el.firstName = 'Sergio'
        el.lastName = 'del Amo'
        el.userId = 12345
        el.vcard = 'BEGIN:VCARD'

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('phone_number')
        json.contains('first_name')
        json.contains('last_name')
        json.contains('user_id')

        when:
        el.phoneNumber = null
        el.firstName = null
        el.lastName = null
        el.userId = null
        el.vcard = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('phone_number')
        !json.contains('first_name')
        !json.contains('last_name')
        !json.contains('user_id')
    }

    static Contact validContact() {
        Contact el = new Contact()
        el.phoneNumber = '+34630444444'
        el.firstName = 'Sergio'
        el
    }
}
