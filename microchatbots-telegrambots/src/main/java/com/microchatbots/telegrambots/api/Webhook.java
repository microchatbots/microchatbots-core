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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @see <a href="https://core.telegram.org/bots/api#webhookinfo">setWebhook</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Webhook {
    /**
     * HTTPS url to send updates to. Use an empty string to remove webhook integration
     */
    @NonNull
    @NotBlank
    private String url;

    /**
     * Upload your public key certificate so that the root certificate in use can be checked. See our self-signed guide for details.
     */
    //TODOprivate InputFile certificate;

    /**
     * Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery, 1-100. Defaults to 40. Use lower values to limit the load on your bot's server, and higher values to increase your bot's throughput.
     */
    @JsonProperty("max_connections")
    @Nullable
    @Min(1)
    @Max(100)
    private Integer maxConnections;

    /**
     * A JSON-serialized list of the update types you want your bot to receive. For example, specify [“message”, “edited_channel_post”, “callback_query”] to only receive updates of these types. See Update for a complete list of available update types. Specify an empty list to receive all updates regardless of type (default). If not specified, the previous setting will be used.
     * <p>
     * Please note that this parameter doesn't affect updates created before the call to the setWebhook, so unwanted updates may be received for a short period of time.
     */
    @JsonProperty("allowed_updates")
    @Nullable
    private List<String> allowedUpdated;

    public Webhook() {
    }

    /**
     *
     * @return HTTPS url to send updates to. Use an empty string to remove webhook integration
     */
    @NonNull
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url HTTPS url to send updates to. Use an empty string to remove webhook integration
     */
    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    /**
     *
     * @return Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery, 1-100.
     */
    @Nullable
    public Integer getMaxConnections() {
        return maxConnections;
    }

    /**
     *
     * @param maxConnections Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery, 1-100.
     */
    public void setMaxConnections(@Nullable Integer maxConnections) {
        this.maxConnections = maxConnections;
    }

    /**
     *
     * @return A JSON-serialized list of the update types you want your bot to receive
     */
    @Nullable
    public List<String> getAllowedUpdated() {
        return allowedUpdated;
    }

    /**
     *
     * @param allowedUpdated A JSON-serialized list of the update types you want your bot to receive
     */
    public void setAllowedUpdated(@Nullable List<String> allowedUpdated) {
        this.allowedUpdated = allowedUpdated;
    }
}


