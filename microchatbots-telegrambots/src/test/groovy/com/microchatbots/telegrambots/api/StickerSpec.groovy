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

import com.microchatbots.telegrambots.core.MaskPosition
import com.microchatbots.telegrambots.core.MaskPositionPoint
import com.microchatbots.telegrambots.core.PhotoSize
import com.microchatbots.telegrambots.core.Sticker
import io.micronaut.core.beans.BeanIntrospection

class StickerSpec extends ApplicationContextSpecification {
    void "Sticker is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(Sticker)

        then:
        noExceptionThrown()
    }

    void "Sticker::toString() does not throw NPE"() {
        when:
        new Sticker().toString()

        then:
        noExceptionThrown()
    }

    void "valid Sticker does not trigger any constraint exception"() {
        when:
        Sticker el = validSticker()

        then:
        validator.validate(el).isEmpty()
    }

    void "fileId is required"() {
        given:
        Sticker el = validSticker()

        when:
        el.fileId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "fileUniqueId is required"() {
        given:
        Sticker el = validSticker()

        when:
        el.fileUniqueId = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "width is required"() {
        given:
        Sticker el = validSticker()

        when:
        el.width = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "height is required"() {
        given:
        Sticker el = validSticker()

        when:
        el.height = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "animated is required"() {
        given:
        Sticker el = validSticker()

        when:
        el.animated = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "thumb is optional"() {
        given:
        Sticker el = validSticker()

        when:
        el.thumb = null

        then:
        validator.validate(el).isEmpty()
    }

    void "emoji is optional"() {
        given:
        Sticker el = validSticker()

        when:
        el.emoji = null

        then:
        validator.validate(el).isEmpty()
    }

    void "setName is optional"() {
        given:
        Sticker el = validSticker()

        when:
        el.setName = null

        then:
        validator.validate(el).isEmpty()
    }

    void "maskPosition is optional"() {
        given:
        Sticker el = validSticker()

        when:
        el.maskPosition = null

        then:
        validator.validate(el).isEmpty()
    }

    void "fileSize is optional"() {
        given:
        Sticker el = validSticker()

        when:
        el.fileSize = null

        then:
        validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        Sticker el = new Sticker()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.animated = true

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"file_id":"xxx"')
        json.contains('"file_unique_id":"xxx.yyy"')
        json.contains('"width":200')
        json.contains('"height":300')
        json.contains('"is_animated":true')
    }

    void "snake case is used for Json serialization"() {
        given:
        Sticker el = new Sticker()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.animated = true
        el.thumb = validPhotoSize()
        el.emoji = '🤖'
        el.setName = 'stickers by sergio'
        el.maskPosition = validMaskPosition()
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
        json.contains('emoji')
        json.contains('thumb')
        json.contains('animated')
        json.contains('set_name')
        !json.contains('setName')
        json.contains('mask_position')
        !json.contains('maskPosition')
    }

    void "only non null fields are included"() {
        given:
        Sticker el = new Sticker()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.animated = true
        el.thumb = validPhotoSize()
        el.emoji = '🤖'
        el.setName = 'stickers by sergio'
        el.maskPosition = validMaskPosition()
        el.fileSize = 120

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('file_id')
        json.contains('file_unique_id')
        json.contains('width')
        json.contains('height')
        json.contains('file_size')
        json.contains('emoji')
        json.contains('thumb')
        json.contains('animated')
        json.contains('set_name')
        json.contains('mask_position')

        when:
        el.fileId = null
        el.fileUniqueId = null
        el.width = null
        el.height = null
        el.animated = null
        el.thumb = null
        el.emoji = null
        el.setName = null
        el.maskPosition = null
        el.fileSize = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('file_id')
        !json.contains('file_unique_id')
        !json.contains('width')
        !json.contains('height')
        !json.contains('file_size')
        !json.contains('emoji')
        !json.contains('thumb')
        !json.contains('animated')
        !json.contains('set_name')
        !json.contains('mask_position')
    }

    static Sticker validSticker() {
        Sticker el = new Sticker()
        el.fileId = "xxx"
        el.fileUniqueId = "xxx.yyy"
        el.width = 200
        el.height = 300
        el.animated = true
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

    static MaskPosition validMaskPosition() {
        MaskPosition el = new MaskPosition()
        el.point = MaskPositionPoint.CHIN
        el.xshift = -1
        el.yshift = 1.0
        el.scale = 2.0
        el
    }
}
