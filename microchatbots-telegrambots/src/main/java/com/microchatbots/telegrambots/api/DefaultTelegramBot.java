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
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.uri.UriBuilder;
import org.reactivestreams.Publisher;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DefaultTelegramBot implements TelegramBot, AutoCloseable {
    public static final String BOT_PATH = "/bot";
    private final HttpClient httpClient;
    private final TelegramBotClient telegramBotClient;
    private final String token;

    /**
     *
     * @param token Bot's token
     * @param telegramBotClient Telegram Bot Client
     */
    public DefaultTelegramBot(String token,
                              TelegramBotClient telegramBotClient) {
        this.token = token;
        this.telegramBotClient = telegramBotClient;
        this.httpClient = null;
    }

    /**
     *
     * @param token Bot's token
     * @param url URL
     * @throws MalformedURLException if the url is not a valid URL
     */
    public DefaultTelegramBot(String token,
                              String url) throws MalformedURLException {
        this.token = token;
        this.telegramBotClient = null;
        this.httpClient = HttpClient.create(new URL(url));
    }

    /**
     *
     * @param token Bot's Token
     * @throws MalformedURLException when a Micronaut HTTP Client cannot be pointed to Telegrams' API
     */
    public DefaultTelegramBot(String token) throws MalformedURLException  {
        this.token = token;
        this.telegramBotClient = null;
        this.httpClient = HttpClient.create(new URL(TelegramBotClientConfiguration.TELEGRAM_API));
    }

    @Override
    public Publisher<List<Update>> getUpdates(@NonNull @NotNull @Valid @Body GetUpdates getUpdates) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_GET_UPDATES).build(), getUpdates), Argument.listOf(Update.class));
        } else {
            return telegramBotClient.getUpdates(token, getUpdates);
        }
    }

    @Override
    public Publisher<List<BotCommand>> getMyCommands(@PathVariable @NonNull @NotBlank String token) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.GET(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_GET_MY_COMMANDS).build()), Argument.listOf(BotCommand.class));
        } else {
            return telegramBotClient.getMyCommands(token);
        }
    }

    @Override
    public Publisher<TelegramApiResponse> setMyCommands(@PathVariable @NonNull @NotBlank String token,
                                                        @NonNull @NotNull List<@Valid BotCommand> botCommands) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_SET_MY_COMMANDS).build(), botCommands), TelegramApiResponse.class);
        } else {
            return telegramBotClient.setMyCommands(token, botCommands);
        }
    }

    @Override
    public Publisher<TelegramApiResponse> setWebhook(@NonNull @NotNull @Valid @Body Webhook webhook) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_SET_WEBHOOK).build(), webhook), TelegramApiResponse.class);
        } else {
            return telegramBotClient.setWebhook(token, webhook);
        }
    }

    @Override
    public Publisher<Message> sendMessage(@NonNull @NotNull @Valid SendMessage sendMessage) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendMessage.METHOD_SENDMESSAGE).build(), sendMessage), Message.class);

        } else {
            return telegramBotClient.sendMessage(token, sendMessage);
        }
    }

    @Override
    public Publisher<Message> sendMessage(@NonNull @NotNull String text,
                                          @NonNull @NotNull Integer chatId) {
        if (telegramBotClient ==  null) {
            SendMessage message = new SendMessage();
            message.setText(text);
            message.setChatId(chatId);
            return this.sendMessage(message);

        } else {
            return telegramBotClient.sendMessage(token, text, chatId);
        }
    }

    @Override
    public Publisher<Message> sendPhoto(@NonNull @NotNull @Valid SendPhoto sendPhoto) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendPhoto.SEND_PHOTO).build(), sendPhoto), Message.class);

        } else {
            return telegramBotClient.sendPhoto(token, sendPhoto);
        }
    }

    @Override
    public Publisher<TelegramApiResponse> deleteWebhook() {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.GET(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_DELETE_WEBHOOK).build()), TelegramApiResponse.class);
        } else {
            return telegramBotClient.deleteWebhook(token);
        }
    }

    @Override
    public Publisher<WebhookInfo> getWebhookInfo() {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.GET(UriBuilder.of(BOT_PATH + token).path(TelegramBotApi.METHOD_GET_WEBHOOKINFO).build()), WebhookInfo.class);
        } else {
            return telegramBotClient.getWebhookInfo(token);
        }
    }

    @Override
    public Publisher<Message> sendAudio(@NonNull @NotNull @Valid SendAudio sendAudio) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendAudio.SEND_AUDIO).build(), sendAudio), Message.class);

        } else {
            return telegramBotClient.sendAudio(token, sendAudio);
        }
    }

    @Override
    public Publisher<Message> sendVideo(@NonNull @NotNull @Valid SendVideo sendVideo) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendVideo.SEND_VIDEO).build(), sendVideo), Message.class);

        } else {
            return telegramBotClient.sendVideo(token, sendVideo);
        }
    }

    @Override
    public Publisher<Message> sendAnimation(@NonNull @NotNull @Valid SendAnimation sendAnimation) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendAnimation.SEND_ANIMATION).build(), sendAnimation), Message.class);

        } else {
            return telegramBotClient.sendAnimation(token, sendAnimation);
        }
    }

    @Override
    public Publisher<Message> sendLocation(@NonNull @NotNull @Valid SendLocation sendLocation) {
        if (telegramBotClient ==  null) {
            return httpClient.retrieve(HttpRequest.POST(UriBuilder.of(BOT_PATH + token).path(SendLocation.SEND_LOCATION).build(), sendLocation), Message.class);

        } else {
            return telegramBotClient.sendLocation(token, sendLocation);
        }
    }

    @Override
    public void close() throws IOException {
        if (httpClient != null) {
            httpClient.close();
        }
    }

    /**
     *
     * @return a Blocking Telegram Bot
     */
    public BlockingTelegramBot toBlocking() {
        return new DefaultBlockingTelegramBot(getTelegramBotClient(), getHttpClient(), getToken());
    }

    /**
     *
     * @return HttpClient
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     *
     * @return Telegram Bot client
     */
    public TelegramBotClient getTelegramBotClient() {
        return telegramBotClient;
    }

    /**
     *
     * @return Telegram's token
     */
    public String getToken() {
        return token;
    }
}
