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

import com.microchatbots.telegrambots.core.Animation
import io.micronaut.core.beans.BeanIntrospection

class AnimationSpec extends ApplicationContextSpecification {
    void "Animation is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(Animation)

        then:
        noExceptionThrown()
    }

    void "Animation::toString() does not throw NPE"() {
        when:
        new Animation().toString()

        then:
        noExceptionThrown()
    }

    void "valid Animation does not trigger any constraint exception"() {
        when:
        Animation el = validAnimation()

        then:
        validator.validate(el).isEmpty()
    }

    void "fileId is required"() {
        given:
        Animation el = validAnimation()

        when:
        el.fileId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "fileUniqueId is required"() {
        given:
        Animation el = validAnimation()

        when:
        el.fileUniqueId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "width is required"() {
        given:
        Animation el = validAnimation()

        when:
        el.width = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "height is required"() {
        given:
        Animation el = validAnimation()

        when:
        el.height = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "duration is required"() {
        given:
        Animation el = validAnimation()

        when:

        el.duration = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "fileName is optional"() {
        given:
        Animation el = validAnimation()

        when:
        el.fileName = null

        then:
        validator.validate(el).isEmpty()
    }

    void "thumb is optional"() {
        given:
        Animation el = validAnimation()

        when:
        el.thumb = null

        then:
        validator.validate(el).isEmpty()
    }

    void "mimeType is optional"() {
        given:
        Animation el = validAnimation()

        when:
        el.mimeType = null

        then:
        validator.validate(el).isEmpty()
    }

    void "fileSize is optional"() {
        given:
        Animation el = validAnimation()

        when:
        el.fileSize = null

        then:
        validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        Animation el = new Animation()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.duration = 10
        el.fileName = 'foo'
        el.mimeType = "mime"
        el.fileSize = 120

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"file_id":"xxx"')
        json.contains('"file_unique_id":"xxx.yyy"')
        json.contains('"width":200')
        json.contains('"height":300')
        json.contains('"file_size":120')
        json.contains('"mime_type":"mime"')
        json.contains('"file_name":"foo"')
    }

    void "snake case is used for Json serialization"() {
        given:
        Animation el = new Animation()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.duration = 10
        el.fileName = 'foo'
        el.mimeType = "mime"
        el.fileSize = 120

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('file_id')
        json.contains('file_unique_id')
        json.contains('width')
        json.contains('height')
        json.contains('file_size')
        json.contains('mime_type')
        json.contains('file_name')
        !json.contains('fileId')
        !json.contains('fileUniqueId')
        !json.contains('fileSize')
        !json.contains('mimeType')
        !json.contains('fileName')
    }

    void "only non null fields are included"() {
        given:
        Animation el = new Animation()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.duration = 10
        el.fileSize = 120
        el.fileName = 'foo'
        el.mimeType = "mime"

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('file_id')
        json.contains('file_unique_id')
        json.contains('width')
        json.contains('height')
        json.contains('file_size')
        json.contains('file_name')
        json.contains('mime_type')

        when:
        el.fileSize = null
        el.mimeType = null
        el.fileName = null
        json = objectMapper.writeValueAsString(el)

        then:
        json.contains('file_id')
        json.contains('file_unique_id')
        json.contains('width')
        json.contains('height')
        !json.contains('file_size')
        !json.contains('file_name')
        !json.contains('mime_type')
    }

    static Animation validAnimation() {
        Animation el = new Animation()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.duration = 10
        el
    }
}
