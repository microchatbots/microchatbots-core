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

import com.microchatbots.telegrambots.core.PhotoSize
import com.microchatbots.telegrambots.core.UserProfilePhotos
import io.micronaut.core.beans.BeanIntrospection

class UserProfilePhotosSpec extends ApplicationContextSpecification {
    void "UserProfilePhotos is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(UserProfilePhotos)

        then:
        noExceptionThrown()
    }

    void "UserProfilePhotos::toString() does not throw NPE"() {
        when:
        new UserProfilePhotos().toString()

        then:
        noExceptionThrown()
    }

    void "valid UserProfilePhotos does not trigger any constraint exception"() {
        when:
        UserProfilePhotos el = validUserProfilePhotos()

        then:
        validator.validate(el).isEmpty()
    }

    void "totalCount is required"() {
        given:
        UserProfilePhotos el = validUserProfilePhotos()

        when:
        el.totalCount = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "photos is required"() {
        given:
        UserProfilePhotos el = validUserProfilePhotos()

        when:
        el.photos = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        UserProfilePhotos el = new UserProfilePhotos()
        el.totalCount = 1
        el.photos = [[validPhotoSize()]]

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"total_count":1')
        json.contains('"photos"')
    }

    void "snake case is used for Json serialization"() {
        given:
        UserProfilePhotos el = new UserProfilePhotos()
        el.totalCount = 1
        el.photos = [[validPhotoSize()]]

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('total_count')
        !json.contains('totalCount')
    }

    void "only non null fields are included"() {
        given:
        UserProfilePhotos el = new UserProfilePhotos()
        el.totalCount = 1
        el.photos = [[validPhotoSize()]]

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('total_count')
        json.contains('photos')

        when:
        el.totalCount = null
        el.photos = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('total_count')
        !json.contains('photos')
    }

    static UserProfilePhotos validUserProfilePhotos() {
        UserProfilePhotos el = new UserProfilePhotos()
        el.totalCount = 1
        el.photos = [[validPhotoSize()]]
        el
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
