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
package com.microchatbots.telegrambots.api;

import com.microchatbots.telegrambots.core.BotCommand;
import com.microchatbots.telegrambots.core.Message;
import com.microchatbots.telegrambots.core.send.SendAnimation;
import com.microchatbots.telegrambots.core.send.SendAudio;
import com.microchatbots.telegrambots.core.send.SendLocation;
import com.microchatbots.telegrambots.core.send.SendMessage;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.core.send.SendPhoto;
import com.microchatbots.telegrambots.core.send.SendVideo;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;
import io.reactivex.Flowable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface TelegramBotApi {
    String METHOD_GET_MY_COMMANDS = "getMyCommands";
    String METHOD_SET_MY_COMMANDS = "setMyCommands";
    String METHOD_GET_UPDATES = "getUpdates";
    String METHOD_SET_WEBHOOK = "setWebhook";
    String METHOD_GET_WEBHOOKINFO = "getWebhookInfo";
    String METHOD_DELETE_WEBHOOK = "deleteWebhook";

    Flowable<TelegramApiResponse> setMyCommands(@PathVariable @NonNull @NotBlank String token,
                                                @NonNull @NotNull List<@Valid BotCommand> botCommands);

    Flowable<List<BotCommand>> getMyCommands(@PathVariable @NonNull @NotBlank String token);

    Flowable<Message> sendMessage(@PathVariable @NonNull @NotBlank String token,
                                  @NonNull @NotNull @Valid @Body SendMessage sendMessage);

    default Flowable<Message> sendMessage(@PathVariable @NonNull @NotBlank String token,
                                          @NonNull @NotNull String text,
                                          @NonNull @NotNull Integer chatId) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chatId);
        return sendMessage(token, message);
    }

    Flowable<List<Update>> getUpdates(@PathVariable @NonNull @NotBlank String token,
                                      @NonNull @NotNull @Valid @Body GetUpdates getUpdates);

    //TODO add setwebhook https://core.telegram.org/bots/api#setwebhook
    Flowable<TelegramApiResponse> setWebhook(@PathVariable @NonNull @NotBlank String token,
                               @NonNull @NotNull @Valid @Body Webhook webhook);

    /**
     * Use this method to remove webhook integration if you decide to switch back to getUpdates.
     * @param token Token
     * @return Returns True on success.
     */
    Flowable<TelegramApiResponse> deleteWebhook(@PathVariable @NonNull @NotBlank String token);

    /**
     * Use this method to get current webhook status. Requires no parameters.
     * @param token Token
     * @see <a href="https://core.telegram.org/bots/api#getwebhookinfo">getWebhookInfo</a>
     * @return On success, returns a WebhookInfo object. If the bot is using getUpdates, will return an object with the url field empty.
     */
    Flowable<WebhookInfo> getWebhookInfo(@PathVariable @NonNull @NotBlank String token);

    /**
     *
     * @param token Token
     * @param sendPhoto Send Photo
     * @return Message
     */
    Flowable<Message> sendPhoto(@PathVariable @NonNull @NotBlank String token,
                                @NonNull @NotNull @Valid @Body SendPhoto sendPhoto);

    /**
     *
     * @param token Token
     * @param sendAudio Send Audio
     * @return Message
     */
    Flowable<Message> sendAudio(@PathVariable @NonNull @NotBlank String token,
                                @NonNull @NotNull @Valid @Body SendAudio sendAudio);

    /**
     *
     * @param token Token
     * @param sendVideo SendVideo
     * @return Message
     */
    Flowable<Message> sendVideo(@PathVariable @NonNull @NotBlank String token,
                                @NonNull @NotNull @Valid @Body SendVideo sendVideo);

    /**
     *
     * @param token Token
     * @param sendAnimation SendAnimatin
     * @return Message
     */
    Flowable<Message> sendAnimation(@PathVariable @NonNull @NotBlank String token,
                                    @NonNull @NotNull @Valid @Body SendAnimation sendAnimation);

    /**
     *
     * @param token Token
     * @param sendLocation SendLocation
     * @return Message
     */
    Flowable<Message> sendLocation(@PathVariable @NonNull @NotBlank String token,
                                   @NonNull @NotNull @Valid @Body SendLocation sendLocation);
}
