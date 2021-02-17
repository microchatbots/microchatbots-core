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
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.uri.UriBuilder;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DefaultBlockingTelegramBot implements BlockingTelegramBot, AutoCloseable {
    public static final String BOT_PATH = "/bot";
    private final BlockingHttpClient httpClient;
    private final TelegramBotClient telegramBotClient;
    private final String token;

    public DefaultBlockingTelegramBot(String token,
                                      TelegramBotClient telegramBotClient) {
        this.token = token;
        this.telegramBotClient = telegramBotClient;
        this.httpClient = null;
    }

    public DefaultBlockingTelegramBot(String token,
                                      String url) throws MalformedURLException {
        this.token = token;
        this.telegramBotClient = null;
        this.httpClient = HttpClient.create(new URL(url)).toBlocking();
    }

    public DefaultBlockingTelegramBot(String token) throws MalformedURLException  {
        this.token = token;
        this.telegramBotClient = null;
        this.httpClient = HttpClient.create(new URL(TelegramBotClientConfiguration.TELEGRAM_API)).toBlocking();
    }

    public DefaultBlockingTelegramBot(TelegramBotClient telegramBotClient, HttpClient httpClient, String token) {
        this.telegramBotClient = telegramBotClient;
        this.httpClient = httpClient.toBlocking();
        this.token = token;
    }

    @Override
    public List<Update> getUpdates(@NonNull @NotNull @Valid @Body GetUpdates getUpdates) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_GET_UPDATES).build(), getUpdates), Argument.listOf(Update.class));
        } else {
            return telegramBotClient.getUpdates(token, getUpdates).blockingFirst();
        }
    }

    @Override
    public List<BotCommand> getMyCommands(@PathVariable @NonNull @NotBlank String token) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.GET(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_GET_MY_COMMANDS).build()), Argument.listOf(BotCommand.class));
        } else {
            return telegramBotClient.getMyCommands(token).blockingFirst();
        }
    }

    @Override
    public TelegramApiResponse setMyCommands(@PathVariable @NonNull @NotBlank String token,
                                                        @NonNull @NotNull List<@Valid BotCommand> botCommands) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_SET_MY_COMMANDS).build(), botCommands), TelegramApiResponse.class);
        } else {
            return telegramBotClient.setMyCommands(token, botCommands).blockingFirst();
        }
    }

    @Override
    public TelegramApiResponse setWebhook(@NonNull @NotNull @Valid @Body Webhook webhook) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_SET_WEBHOOK).build(), webhook), TelegramApiResponse.class);
        } else {
            return telegramBotClient.setWebhook(token, webhook).blockingFirst();
        }
    }

    @Override
    public Message sendMessage(@NonNull @NotNull @Valid SendMessage sendMessage) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendMessage.METHOD_SENDMESSAGE).build(), sendMessage), Message.class);

        } else {
            return telegramBotClient.sendMessage(token, sendMessage).blockingFirst();
        }
    }

    @Override
    public Message sendMessage(@NonNull @NotNull String text,
                                          @NonNull @NotNull Integer chatId) {
        if (telegramBotClient ==  null) {
            SendMessage message = new SendMessage();
            message.setText(text);
            message.setChatId(chatId);
            return this.sendMessage(message);

        } else {
            return telegramBotClient.sendMessage(token, text, chatId).blockingFirst();
        }
    }

    @Override
    public Message sendPhoto(@NonNull @NotNull @Valid SendPhoto sendPhoto) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendPhoto.SEND_PHOTO).build(), sendPhoto), Message.class);

        } else {
            return telegramBotClient.sendPhoto(token, sendPhoto).blockingFirst();
        }
    }

    @Override
    public TelegramApiResponse deleteWebhook() {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.GET(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_DELETE_WEBHOOK).build()), TelegramApiResponse.class);
        } else {
            return telegramBotClient.deleteWebhook(token).blockingFirst();
        }
    }

    @Override
    public WebhookInfo getWebhookInfo() {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.GET(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_GET_WEBHOOKINFO).build()), WebhookInfo.class);
        } else {
            return telegramBotClient.getWebhookInfo(token).blockingFirst();
        }
    }

    @Override
    public Message sendAudio(@NonNull @NotNull @Valid SendAudio sendAudio) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendAudio.SEND_AUDIO).build(), sendAudio), Message.class);

        } else {
            return telegramBotClient.sendAudio(token, sendAudio).blockingFirst();
        }
    }

    @Override
    public Message sendVideo(@NonNull @NotNull @Valid SendVideo sendVideo) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendVideo.SEND_VIDEO).build(), sendVideo), Message.class);

        } else {
            return telegramBotClient.sendVideo(token, sendVideo).blockingFirst();
        }
    }

    @Override
    public Message sendAnimation(@NonNull @NotNull @Valid SendAnimation sendAnimation) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendAnimation.SEND_ANIMATION).build(), sendAnimation), Message.class);

        } else {
            return telegramBotClient.sendAnimation(token, sendAnimation).blockingFirst();
        }
    }

    @Override
    public Message sendLocation(@NonNull @NotNull @Valid SendLocation sendLocation) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendLocation.SEND_LOCATION).build(), sendLocation), Message.class);

        } else {
            return telegramBotClient.sendLocation(token, sendLocation).blockingFirst();
        }
    }

    @Override
    public void close() throws IOException {
        if (httpClient != null) {
            httpClient.close();
        }
    }
}
