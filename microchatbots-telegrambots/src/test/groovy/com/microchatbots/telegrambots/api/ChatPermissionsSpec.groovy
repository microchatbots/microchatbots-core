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

import com.microchatbots.telegrambots.core.ChatPermissions
import io.micronaut.core.beans.BeanIntrospection

class ChatPermissionsSpec extends ApplicationContextSpecification {

    void "ChatPermissions is annotated with Introspected"() {
        when:
        BeanIntrospection.getIntrospection(ChatPermissions)

        then:
        noExceptionThrown()
    }

    void "ChatPermissions::toString() does not throw NPE"() {
        when:
        new ChatPermissions().toString()

        then:
        noExceptionThrown()
    }

    void "valid ChatPermissions does not trigger any constraint exception"() {
        when:
        ChatPermissions el = validChatPermissions()

        then:
        validator.validate(el).isEmpty()
    }

    void "canSendMessages is optional"() {
        given:
        ChatPermissions el = validChatPermissions()

        when:
        el.canSendMessages = null

        then:
        validator.validate(el).isEmpty()
    }

    void "canSendMediaMessages is optional"() {
        given:
        ChatPermissions el = validChatPermissions()

        when:
        el.canSendMediaMessages = null

        then:
        validator.validate(el).isEmpty()
    }

    void "canSendPolls is optional"() {
        given:
        ChatPermissions el = validChatPermissions()

        when:
        el.canSendPolls = null

        then:
        validator.validate(el).isEmpty()
    }

    void "canSendOtherMessages is optional"() {
        given:
        ChatPermissions el = validChatPermissions()

        when:
        el.canSendOtherMessages = null

        then:
        validator.validate(el).isEmpty()
    }

    void "canAddWebPagePreviews is optional"() {
        given:
        ChatPermissions el = validChatPermissions()

        when:
        el.canAddWebPagePreviews = null

        then:
        validator.validate(el).isEmpty()
    }

    void "canChangeInfo is optional"() {
        given:
        ChatPermissions el = validChatPermissions()

        when:
        el.canChangeInfo = null

        then:
        validator.validate(el).isEmpty()
    }

    void "canInviteUsers is optional"() {
        given:
        ChatPermissions el = validChatPermissions()

        when:
        el.canInviteUsers = null

        then:
        validator.validate(el).isEmpty()
    }

    void "canPinMessages is optional"() {
        given:
        ChatPermissions el = validChatPermissions()

        when:
        el.canPinMessages = null

        then:
        validator.validate(el).isEmpty()
    }

    void "values are present in json"() {
        given:
        ChatPermissions el = new ChatPermissions()
        el.canSendMessages = true
        el.canSendMediaMessages = true
        el.canSendPolls = true
        el.canSendOtherMessages = true
        el.canAddWebPagePreviews = true
        el.canChangeInfo = true
        el.canInviteUsers = true
        el.canPinMessages = true

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('"can_send_messages":true')
        json.contains('"can_send_media_messages":true')
        json.contains('"can_send_polls":true')
        json.contains('"can_send_other_messages":true')
        json.contains('"can_add_web_page_previews":true')
        json.contains('"can_change_info":true')
        json.contains('"can_invite_users":true')
        json.contains('"can_pin_messages":true')
    }

    void "only non null fields are included"() {
        given:
        ChatPermissions el = new ChatPermissions()
        el.canSendMessages = true
        el.canSendMediaMessages = true
        el.canSendPolls = true
        el.canSendOtherMessages = true
        el.canAddWebPagePreviews = true
        el.canChangeInfo = true
        el.canInviteUsers = true
        el.canPinMessages = true

        when:
        String json = objectMapper.writeValueAsString(el)

        then:
        json.contains('can_send_messages')
        json.contains('can_send_media_messages')
        json.contains('can_send_polls')
        json.contains('can_send_other_messages')
        json.contains('can_add_web_page_previews')
        json.contains('can_change_info')
        json.contains('can_invite_users')
        json.contains('can_pin_messages')

        when:
        el.canSendMessages = null
        el.canSendMediaMessages = null
        el.canSendPolls = null
        el.canSendOtherMessages = null
        el.canAddWebPagePreviews = null
        el.canChangeInfo = null
        el.canInviteUsers = null
        el.canPinMessages = null
        json = objectMapper.writeValueAsString(el)

        then:
        !json.contains('can_send_messages')
        !json.contains('can_send_media_messages')
        !json.contains('can_send_polls')
        !json.contains('can_send_other_messages')
        !json.contains('can_add_web_page_previews')
        !json.contains('can_change_info')
        !json.contains('can_invite_users')
        !json.contains('can_pin_messages')
    }

    static ChatPermissions validChatPermissions() {
        ChatPermissions el = new ChatPermissions()
        el
    }
}
