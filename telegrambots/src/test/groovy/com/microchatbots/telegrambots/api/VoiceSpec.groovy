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

import com.microchatbots.telegrambots.core.Voice
import io.micronaut.core.beans.BeanIntrospection

class VoiceSpec extends ApplicationContextSpecification {
    void "voice is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(Voice)

        then:
        noExceptionThrown()
    }

    void "Voice::toString() does not throw NPE"() {
        when:
        new Voice().toString()

        then:
        noExceptionThrown()
    }

    void "valid Voice does not trigger any constraint exception"() {
        when:
        Voice el = validVoice()

        then:
        validator.validate(el).isEmpty()
    }

    void "fileId is required"() {
        given:
        Voice el = validVoice()

        when:
        el.fileId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "fileUniqueId is required"() {
        given:
        Voice el = validVoice()

        when:
        el.fileUniqueId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "duration is required"() {
        given:
        Voice el = validVoice()

        when:
        el.duration = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "mimeType is optional"() {
        given:
        Voice el = validVoice()

        when:
        el.mimeType = null

        then:
        validator.validate(el).isEmpty()
    }

    void "fileSize is optional"() {
        given:
        Voice el = validVoice()

        when:
        el.fileSize = null

        then:
        validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        Voice el = new Voice()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.duration = 20
        el.mimeType = 'mime'
        el.fileSize = 120

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"file_id":"xxx"')
        json.contains('"file_unique_id":"xxx.yyy"')
        json.contains('"duration":20')
        json.contains('"mime_type":"mime"')
        json.contains('"file_size":120')
    }

    void "snake case is used for Json serialization"() {
        given:
        Voice el = new Voice()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.duration = 20
        el.mimeType = 'mime'
        el.fileSize = 120

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('file_id')
        json.contains('file_unique_id')
        json.contains('duration')
        json.contains('mime_type')
        json.contains('file_size')
        !json.contains('fileId')
        !json.contains('fileUniqueId')
        !json.contains('mimeType')
        !json.contains('fileSize')
    }

    void "only non null fields are included"() {
        given:
        Voice el = new Voice()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.duration = 20
        el.mimeType = 'mime'
        el.fileSize = 120

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('file_id')
        json.contains('file_unique_id')
        json.contains('duration')
        json.contains('mime_type')
        json.contains('file_size')

        when:
        el.mimeType = null
        el.fileSize = null
        json = objectMapper.writeValueAsString(el)

        then:
        json.contains('file_id')
        json.contains('file_unique_id')
        json.contains('duration')
        !json.contains('mime_type')
        !json.contains('file_size')
    }

    static Voice validVoice() {
        Voice el = new Voice()
        el.fileId = "xxx"
        el.fileUniqueId = "yyyy"
        el.duration = 30
        el
    }
}
