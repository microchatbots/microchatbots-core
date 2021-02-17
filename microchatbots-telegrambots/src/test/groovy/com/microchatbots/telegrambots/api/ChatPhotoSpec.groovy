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

import com.microchatbots.telegrambots.core.ChatPhoto
import io.micronaut.core.beans.BeanIntrospection

class ChatPhotoSpec extends ApplicationContextSpecification {
    void "ChatPhoto is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(ChatPhoto)

        then:
        noExceptionThrown()
    }

    void "ChatPhoto::toString() does not throw NPE"() {
        when:
        new ChatPhoto().toString()

        then:
        noExceptionThrown()
    }

    void "valid ChatPhoto does not trigger any constraint exception"() {
        when:
        ChatPhoto el = validChatPhoto()

        then:
        validator.validate(el).isEmpty()
    }

    void "bigFileId is required"() {
        given:
        ChatPhoto el = validChatPhoto()

        when:
        el.bigFileId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "bigFileUniqueId is required"() {
        given:
        ChatPhoto el = validChatPhoto()

        when:
        el.bigFileUniqueId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "smallFileId is required"() {
        given:
        ChatPhoto el = validChatPhoto()

        when:
        el.smallFileId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "smallFileUniqueId is required"() {
        given:
        ChatPhoto el = validChatPhoto()

        when:
        el.smallFileUniqueId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        ChatPhoto el = new ChatPhoto()
        el.smallFileUniqueId = "xxx.yyy"
        el.smallFileId = "xxx"
        el.bigFileUniqueId = "zzz.yyy"
        el.bigFileId = "zzz"

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"small_file_id":"xxx"')
        json.contains('"small_file_unique_id":"xxx.yyy"')
        json.contains('"big_file_id":"zzz"')
        json.contains('"big_file_unique_id":"zzz.yyy"')
    }

    void "snake case is used for Json serialization"() {
        given:
        ChatPhoto el = new ChatPhoto()
        el.smallFileUniqueId = "xxx.yyy"
        el.smallFileId = "xxx"
        el.bigFileUniqueId = "zzz.yyy"
        el.bigFileId = "zzz"

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('small_file_id')
        json.contains('small_file_unique_id')
        json.contains('big_file_id')
        json.contains('big_file_unique_id')
        !json.contains('smallFileUniqueId')
        !json.contains('smallFileId')
        !json.contains('bigFileUniqueId')
        !json.contains('bigFileId')
    }

    void "only non null fields are included"() {
        given:
        ChatPhoto el = new ChatPhoto()
        el.smallFileUniqueId = "xxx.yyy"
        el.smallFileId = "xxx"
        el.bigFileUniqueId = "zzz.yyy"
        el.bigFileId = "zzz"

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('small_file_id')
        json.contains('small_file_unique_id')
        json.contains('big_file_id')
        json.contains('big_file_unique_id')

        when:
        el.smallFileUniqueId = null
        el.smallFileId = null
        el.bigFileUniqueId = null
        el.bigFileId = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('small_file_id')
        !json.contains('small_file_unique_id')
        !json.contains('big_file_id')
        !json.contains('big_file_unique_id')
    }

    static ChatPhoto validChatPhoto() {
        ChatPhoto el = new ChatPhoto()
        el.smallFileUniqueId = "xxx.yyy"
        el.smallFileId = "xxx"
        el.bigFileUniqueId = "zzz.yyy"
        el.bigFileId = "zzz"
        el
    }
}
