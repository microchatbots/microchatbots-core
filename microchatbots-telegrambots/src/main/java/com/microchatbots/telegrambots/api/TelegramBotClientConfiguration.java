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

import com.microchatbots.telegrambots.conf.TelegramConfigurationProperties;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.http.client.HttpClientConfiguration;
import io.micronaut.runtime.ApplicationConfiguration;
import edu.umd.cs.findbugs.annotations.NonNull;
import javax.validation.constraints.NotBlank;
import java.time.Duration;

/**
 * {@link ConfigurationProperties} for {@link TelegramBotClient}.
 */
@ConfigurationProperties(TelegramBotClientConfiguration.PREFIX)
public class TelegramBotClientConfiguration extends HttpClientConfiguration {
    public static final String PREFIX = TelegramConfigurationProperties.PREFIX + ".client";
    public static final String TELEGRAM_API = "https://api.telegram.org/";

    private final TelegramBotClientConfigurationConnectionPoolConfiguration connectionPoolConfiguration;

    @NonNull
    @NotBlank
    private String url = TELEGRAM_API;

    public TelegramBotClientConfiguration(final ApplicationConfiguration applicationConfiguration,
                                  final TelegramBotClientConfigurationConnectionPoolConfiguration connectionPoolConfiguration) {
        super(applicationConfiguration);
        this.connectionPoolConfiguration = connectionPoolConfiguration;
    }

    /**
     *
     * @return the telegram API's url
     */
    @NotBlank
    @NonNull
    public String getUrl() {
        return this.url;
    }

    /**
     *
     * @param url telegram API's url
     */
    public void setUrl(@NonNull @NotBlank String url) {
        this.url = url;
    }

    @Override
    @NonNull
    public ConnectionPoolConfiguration getConnectionPoolConfiguration() {
        return this.connectionPoolConfiguration;
    }

    /**
     * {@link ConnectionPoolConfiguration} for {@link TelegramBotClient}.
     */
    @ConfigurationProperties(ConnectionPoolConfiguration.PREFIX)
    public static class TelegramBotClientConfigurationConnectionPoolConfiguration extends ConnectionPoolConfiguration {
    }

    /**
     * Extra {@link ConfigurationProperties} to set the values for the {@link io.micronaut.retry.annotation.Retryable} annotation on {@link TelegramBotClient}.
     */
    @ConfigurationProperties(TelegramBotClientConfigurationConnectionPoolRetryConfiguration.PREFIX)
    public static class TelegramBotClientConfigurationConnectionPoolRetryConfiguration {

        public static final String PREFIX = "retry";

        private static final Duration DEFAULT_DELAY = Duration.ofSeconds(5);
        private static final int DEFAULT_ATTEMPTS = 0;

        private Duration delay = DEFAULT_DELAY;

        private int attempts = DEFAULT_ATTEMPTS;

        /**
         * @return The delay between retry attempts
         */

        public Duration getDelay() {
            return delay;
        }

        /**
         *
         * @param delay The delay between retry attempts
         */
        public void setDelay(Duration delay) {
            this.delay = delay;
        }

        /**
         * @return The maximum number of retry attempts
         */
        public int getAttempts() {
            return attempts;
        }

        /**
         *
         * @param attempts The maximum number of retry attempts
         */
        public void setAttempts(int attempts) {
            this.attempts = attempts;
        }
    }
}

