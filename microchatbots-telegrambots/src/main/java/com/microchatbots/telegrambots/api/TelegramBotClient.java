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
package com.microchatbots.telegrambots.api;

import com.microchatbots.telegrambots.core.BotCommand;
import com.microchatbots.telegrambots.core.Message;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.core.send.SendAnimation;
import com.microchatbots.telegrambots.core.send.SendAudio;
import com.microchatbots.telegrambots.core.send.SendLocation;
import com.microchatbots.telegrambots.core.send.SendMessage;
import com.microchatbots.telegrambots.core.send.SendPhoto;
import com.microchatbots.telegrambots.core.send.SendVideo;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.Flowable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Client(
        value = "${" + TelegramBotClientConfiguration.PREFIX + ".url:`" + TelegramBotClientConfiguration.TELEGRAM_API + "`}",
        configuration = TelegramBotClientConfiguration.class
)
@Retryable(
        attempts = "${" + TelegramBotClientConfiguration.PREFIX + ".retry.attempts:0}",
        delay = "${" + TelegramBotClientConfiguration.PREFIX + ".retry.delay:5s}")
public interface TelegramBotClient extends TelegramBotApi {

    @Override
    @Post("/bot{token}/setMyCommands")
    Flowable<TelegramApiResponse> setMyCommands(@PathVariable @NonNull @NotBlank String token,
                                                @NonNull @NotNull List<@Valid BotCommand> botCommands);

    @Override
    @Post("/bot{token}/sendMessage")
    Flowable<Message> sendMessage(@PathVariable @NonNull @NotBlank String token,
                                  @NonNull @NotNull @Valid @Body SendMessage sendMessage);

    @Post("/bot{token}/getUpdates")
    @Override
    Flowable<List<Update>> getUpdates(@PathVariable @NonNull @NotBlank String token,
                                      @NonNull @NotNull @Valid @Body GetUpdates getUpdates);

    @Post("/bot{token}/setWebhook")
    @Override
    Flowable<TelegramApiResponse> setWebhook(@PathVariable @NonNull @NotBlank String token,
                                      @NonNull @NotNull @Valid @Body Webhook webhook);

    @Post("/bot{token}/setWebhook")
    @Override
    Flowable<TelegramApiResponse> deleteWebhook(@PathVariable @NonNull @NotBlank String token);

    @Post("/bot{token}/getWebhookInfo")
    @Override
    Flowable<WebhookInfo> getWebhookInfo(@PathVariable @NonNull @NotBlank String token);

    @Get("/bot{token}/getMyCommands")
    Flowable<List<BotCommand>> getMyCommands(@PathVariable @NonNull @NotBlank String token);

    @Post("/bot{token}/sendPhoto")
    Flowable<Message> sendPhoto(@PathVariable @NonNull @NotBlank String token,
                                @NonNull @NotNull @Valid @Body SendPhoto sendPhoto);

    @Post("/bot{token}/sendAudio")
    Flowable<Message> sendAudio(@PathVariable @NonNull @NotBlank String token,
                                @NonNull @NotNull @Valid @Body SendAudio sendAudio);

    @Post("/bot{token}/sendVideo")
    Flowable<Message> sendVideo(@PathVariable @NonNull @NotBlank String token,
                                @NonNull @NotNull @Valid @Body SendVideo sendVideo);

    @Post("/bot{token}/sendAnimation")
    Flowable<Message> sendAnimation(@PathVariable @NonNull @NotBlank String token,
                                    @NonNull @NotNull @Valid @Body SendAnimation sendAnimation);

    @Post("/bot{token}/sendLocation")
    Flowable<Message> sendLocation(@PathVariable @NonNull @NotBlank String token,
                                   @NonNull @NotNull @Valid @Body SendLocation sendLocation);
}
