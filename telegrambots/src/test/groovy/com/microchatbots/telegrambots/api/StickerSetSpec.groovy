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
import com.microchatbots.telegrambots.core.Sticker
import com.microchatbots.telegrambots.core.StickerSet
import io.micronaut.core.beans.BeanIntrospection

class StickerSetSpec extends ApplicationContextSpecification {
    void "StickerSet is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(StickerSet)

        then:
        noExceptionThrown()
    }

    void "StickerSet::toString() does not throw NPE"() {
        when:
        new StickerSet().toString()

        then:
        noExceptionThrown()
    }

    void "valid StickerSet does not trigger any constraint exception"() {
        when:
        StickerSet el = validStickerSet()

        then:
        validator.validate(el).isEmpty()
    }

    void "name is required"() {
        given:
        StickerSet el = validStickerSet()

        when:
        el.name = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "title is required"() {
        given:
        StickerSet el = validStickerSet()

        when:
        el.title = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "containsMasks is required"() {
        given:
        StickerSet el = validStickerSet()

        when:
        el.containsMasks = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "stickers is required"() {
        given:
        StickerSet el = validStickerSet()

        when:
        el.stickers = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "animated is required"() {
        given:
        StickerSet el = validStickerSet()

        when:
        el.animated = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "thumb is optional"() {
        given:
        StickerSet el = validStickerSet()

        when:
        el.thumb = null

        then:
        validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        StickerSet el = new StickerSet()
        el.name = "xxx"
        el.title = "yyy"
        el.animated = true
        el.containsMasks = false
        el.stickers = [validSticker()]
        el.thumb = validPhotoSize()

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"name":"xxx"')
        json.contains('"title":"yyy"')
        json.contains('"is_animated":true')
        json.contains('"contains_masks":false')
        json.contains('"stickers"')
        json.contains('"thumb"')
    }

    void "snake case is used for Json serialization"() {
        given:
        StickerSet el = new StickerSet()
        el.name = "xxx"
        el.title = "yyy"
        el.animated = true
        el.containsMasks = false
        el.stickers = [validSticker()]
        el.thumb = validPhotoSize()

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('name')
        json.contains('title')
        json.contains('is_animated')
        json.contains('thumb')
        json.contains('contains_masks')
        json.contains('stickers')
        !json.contains('isAnimated')
        !json.contains('containsMasks')
    }

    void "only non null fields are included"() {
        given:
        StickerSet el = new StickerSet()
        el.name = "xxx"
        el.title = "yyy"
        el.animated = true
        el.containsMasks = false
        el.stickers = [validSticker()]
        el.thumb = validPhotoSize()

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('name')
        json.contains('title')
        json.contains('is_animated')
        json.contains('thumb')
        json.contains('contains_masks')
        json.contains('stickers')

        when:
        el.name = null
        el.title = null
        el.animated = null
        el.containsMasks = null
        el.stickers = null
        el.thumb = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('name')
        !json.contains('title')
        !json.contains('is_animated')
        !json.contains('thumb')
        !json.contains('contains_masks')
        !json.contains('stickers')
    }

    static StickerSet validStickerSet() {
        StickerSet el = new StickerSet()
        el.name = "xxx"
        el.title = "yyy"
        el.animated = true
        el.containsMasks = false
        el.stickers = [validSticker()]
        el
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
}
