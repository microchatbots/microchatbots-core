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

import com.microchatbots.telegrambots.core.MaskPosition
import com.microchatbots.telegrambots.core.MaskPositionPoint
import io.micronaut.core.beans.BeanIntrospection

class MaskPositionSpec extends ApplicationContextSpecification {
    void "MaskPosition is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(MaskPosition)

        then:
        noExceptionThrown()
    }

    void "MaskPosition::toString() does not throw NPE"() {
        when:
        new MaskPosition().toString()

        then:
        noExceptionThrown()
    }

    void "valid MaskPosition does not trigger any constraint exception"() {
        when:
        MaskPosition el = validMaskPosition()

        then:
        validator.validate(el).isEmpty()
    }

    void "point is required"() {
        given:
        MaskPosition el = validMaskPosition()

        when:
        el.point = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "xShift is required"() {
        given:
        MaskPosition el = validMaskPosition()

        when:
        el.xshift = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "yShift is required"() {
        given:
        MaskPosition el = validMaskPosition()

        when:
        el.yshift = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "scale is required"() {
        given:
        MaskPosition el = validMaskPosition()

        when:
        el.scale = null

        then:
        !validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        MaskPosition el = new MaskPosition()
        el.point = MaskPositionPoint.CHIN
        el.xshift = -1
        el.yshift = 1.0
        el.scale = 2.0

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"point":"chin"')
        json.contains('"x_shift":-1')
        json.contains('"y_shift":1.0')
        json.contains('"scale":2.0')
    }

    void "snake case is used for Json serialization"() {
        given:
        MaskPosition el = new MaskPosition()
        el.point = MaskPositionPoint.CHIN
        el.xshift = -1
        el.yshift = 1.0
        el.scale = 2.0

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('point')
        json.contains('x_shift')
        json.contains('y_shift')
        json.contains('scale')
        !json.contains('xShift')
        !json.contains('yShift')
    }

    void "only non null fields are included"() {
        given:
        MaskPosition el = new MaskPosition()
        el.point = MaskPositionPoint.CHIN
        el.xshift = -1
        el.yshift = 1.0
        el.scale = 2.0

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('point')
        json.contains('x_shift')
        json.contains('y_shift')
        json.contains('scale')

        when:
        el.point = null
        el.xshift = null
        el.yshift = null
        el.scale = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('point')
        !json.contains('x_shift')
        !json.contains('y_shift')
        !json.contains('scale')
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
