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

import com.microchatbots.telegrambots.core.PhotoSize
import io.micronaut.core.beans.BeanIntrospection

class PhotoSizeSpec extends ApplicationContextSpecification {
    void "PhotoSize is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(PhotoSize)

        then:
        noExceptionThrown()
    }

    void "PhotoSize::toString() does not throw NPE"() {
        when:
        new PhotoSize().toString()

        then:
        noExceptionThrown()
    }

    void "valid PhotoSize does not trigger any constraint exception"() {
        when:
        PhotoSize el = validPhotoSize()

        then:
        validator.validate(el).isEmpty()
    }

    void "fileId is required"() {
        given:
        PhotoSize el = validPhotoSize()

        when:
        el.fileId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "fileUniqueId is required"() {
        given:
        PhotoSize el = validPhotoSize()

        when:
        el.fileUniqueId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "width is required"() {
        given:
        PhotoSize el = validPhotoSize()

        when:
        el.width = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "height is required"() {
        given:
        PhotoSize el = validPhotoSize()

        when:
        el.height = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "fileSize is optional"() {
        given:
        PhotoSize el = validPhotoSize()

        when:
        el.fileSize = null

        then:
        validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        PhotoSize el = new PhotoSize()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.fileSize = 120

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"file_id":"xxx"')
        json.contains('"file_unique_id":"xxx.yyy"')
        json.contains('"width":200')
        json.contains('"height":300')
        json.contains('"file_size":120')
    }

    void "snake case is used for Json serialization"() {
        given:
        PhotoSize el = new PhotoSize()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.fileSize = 120

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('file_id')
        json.contains('file_unique_id')
        json.contains('width')
        json.contains('height')
        json.contains('file_size')
        !json.contains('fileId')
        !json.contains('fileUniqueId')
        !json.contains('fileSize')
    }

    void "only non null fields are included"() {
        given:
        PhotoSize el = new PhotoSize()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.fileSize = 120

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('file_id')
        json.contains('file_unique_id')
        json.contains('width')
        json.contains('height')
        json.contains('file_size')

        when:
        el.fileSize = null
        json = objectMapper.writeValueAsString(el)

        then:
        json.contains('file_id')
        json.contains('file_unique_id')
        json.contains('width')
        json.contains('height')
        !json.contains('file_size')
    }

    static PhotoSize validPhotoSize() {
        PhotoSize el = new PhotoSize()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el
    }
}
