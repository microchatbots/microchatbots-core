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
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.core.send.SendAnimation;
import com.microchatbots.telegrambots.core.send.SendAudio;
import com.microchatbots.telegrambots.core.send.SendLocation;
import com.microchatbots.telegrambots.core.send.SendMessage;
import com.microchatbots.telegrambots.core.send.SendPhoto;
import com.microchatbots.telegrambots.core.send.SendVideo;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface BlockingTelegramBot {

    List<Update> getUpdates(@NonNull @NotNull @Valid @Body GetUpdates getUpdates);

    List<BotCommand> getMyCommands(@PathVariable @NonNull @NotBlank String token);

    TelegramApiResponse setMyCommands(@PathVariable @NonNull @NotBlank String token,
                                      @NonNull @NotNull List<@Valid BotCommand> botCommands);

    TelegramApiResponse setWebhook(@NonNull @NotNull @Valid @Body Webhook webhook);

    Message sendMessage(@NonNull @NotNull @Valid SendMessage sendMessage);

    Message sendMessage(@NonNull @NotNull String text,
                                   @NonNull @NotNull Integer chatId);

    Message sendPhoto(@NonNull @NotNull @Valid SendPhoto sendPhoto);

    TelegramApiResponse deleteWebhook();

    WebhookInfo getWebhookInfo();

    Message sendAudio(@NonNull @NotNull @Valid SendAudio sendAudio);

    Message sendVideo(@NonNull @NotNull @Valid SendVideo sendVideo);

    Message sendAnimation(@NonNull @NotNull @Valid SendAnimation sendAnimation);

    Message sendLocation(@NonNull @NotNull @Valid SendLocation sendLocation);
}
