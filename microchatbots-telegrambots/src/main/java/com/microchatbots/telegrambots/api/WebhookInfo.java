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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Contains information about the current status of a webhook.
 * @see <a href="https://core.telegram.org/bots/api#webhookinfo">WebhookInfo</a>
 */
@Introspected
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebhookInfo {

    /**
     * Webhook URL, may be empty if webhook is not set up.
     */
    @NonNull
    @NotBlank
    private String url;

    /**
     * True, if a custom certificate was provided for webhook certificate checks.
     */
    @JsonProperty("has_custom_certificate")
    @NonNull
    @NotNull
    private Boolean hasCustomCertificate;

    /**
     * Number of updates awaiting delivery.
     */
    @JsonProperty("pending_update_count")
    @NonNull
    @NotNull
    private Integer pendingUpdateCount;

    /**
     * Unix time for the most recent error that happened when trying to deliver an update via webhook.
     */
    @JsonProperty("last_error_date")
    @Nullable
    private Integer lastErrorDate;

    /**
     * Error message in human-readable format for the most recent error that happened when trying to deliver an update via webhook.
     */
    @JsonProperty("last_error_message")
    @Nullable
    private String lastErrorMessages;

    /**
     * Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery.
     */
    @JsonProperty("max_connections")
    @Nullable
    private Integer maxConnections;

    /**
     * Array of String. Optional. A list of update types the bot is subscribed to. Defaults to all update types.
     */
    @JsonProperty("allowed_updates")
    @Nullable
    private List<String> allowedUpdates;

    public WebhookInfo() {
    }

    /**
     *
     * @return Webhook URL, may be empty if webhook is not set up.
     */
    @NonNull
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url Webhook URL, may be empty if webhook is not set up.
     */
    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    /**
     *
     * @return True, if a custom certificate was provided for webhook certificate checks.
     */
    @NonNull
    public Boolean getHasCustomCertificate() {
        return hasCustomCertificate;
    }

    /**
     *
     * @param hasCustomCertificate True, if a custom certificate was provided for webhook certificate checks.
     */
    public void setHasCustomCertificate(@NonNull Boolean hasCustomCertificate) {
        this.hasCustomCertificate = hasCustomCertificate;
    }

    /**
     *
     * @return Number of updates awaiting delivery.
     */
    @NonNull
    public Integer getPendingUpdateCount() {
        return pendingUpdateCount;
    }

    /**
     *
     * @param pendingUpdateCount Number of updates awaiting delivery.
     */
    public void setPendingUpdateCount(@NonNull Integer pendingUpdateCount) {
        this.pendingUpdateCount = pendingUpdateCount;
    }

    /**
     *
     * @return Unix time for the most recent error that happened when trying to deliver an update via webhook.
     */
    @Nullable
    public Integer getLastErrorDate() {
        return lastErrorDate;
    }

    /**
     *
     * @param lastErrorDate Unix time for the most recent error that happened when trying to deliver an update via webhook.
     */
    public void setLastErrorDate(@Nullable Integer lastErrorDate) {
        this.lastErrorDate = lastErrorDate;
    }

    /**
     *
     * @return Error message in human-readable format for the most recent error that happened when trying to deliver an update via webhook.
     */
    @Nullable
    public String getLastErrorMessages() {
        return lastErrorMessages;
    }

    /**
     *
     * @param lastErrorMessages Error message in human-readable format for the most recent error that happened when trying to deliver an update via webhook.
     */
    public void setLastErrorMessages(@Nullable String lastErrorMessages) {
        this.lastErrorMessages = lastErrorMessages;
    }

    /**
     *
     * @return Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery.
     */
    @Nullable
    public Integer getMaxConnections() {
        return maxConnections;
    }

    /**
     *
     * @param maxConnections Maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery.
     */
    public void setMaxConnections(@Nullable Integer maxConnections) {
        this.maxConnections = maxConnections;
    }

    /**
     *
     * @return A list of update types the bot is subscribed to. Defaults to all update types.
     */
    @Nullable
    public List<String> getAllowedUpdates() {
        return allowedUpdates;
    }

    /**
     *
     * @param allowedUpdates A list of update types the bot is subscribed to. Defaults to all update types.
     */
    public void setAllowedUpdates(@Nullable List<String> allowedUpdates) {
        this.allowedUpdates = allowedUpdates;
    }

    @Override
    public String toString() {
        return "WebhookInfo{" +
                "url='" + url + '\'' +
                ", hasCustomCertificate=" + hasCustomCertificate +
                ", pendingUpdateCount=" + pendingUpdateCount +
                ", lastErrorDate=" + lastErrorDate +
                ", lastErrorMessages='" + lastErrorMessages + '\'' +
                ", maxConnections=" + maxConnections +
                ", allowedUpdates=" + allowedUpdates +
                '}';
    }
}
