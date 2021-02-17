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
package com.microchatbots.basecamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

/**
 * @see <a href="https://github.com/basecamp/bc3-api/blob/master/sections/chatbots.md">Basecamp Chatbots</a>
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Introspected
public class Creator {
    /**
     * Unique identifier. Eg. 1007299143
     */
    private Long id;

    @JsonProperty("attachable_sgid")
    private String attachableSgid;

    private String name;

    @JsonProperty("email_address")
    private String email;

    @JsonProperty("personable_type")
    private String type;

    private String title;

    private String bio;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    private boolean admin;

    private boolean owner;

    @JsonProperty("time_zone")
    private String timeZone;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    private Company company;

    public Creator() {
    }

    /**
     *
     * @return Unique identifier. Eg. 1007299143
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id Unique identifier. Eg. 1007299143
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return Attachable Sgid
     */
    public String getAttachableSgid() {
        return attachableSgid;
    }

    /**
     *
     * @param attachableSgid Attachable sgid
     */
    public void setAttachableSgid(String attachableSgid) {
        this.attachableSgid = attachableSgid;
    }

    /**
     *
     * @return Creator's name. e.g Victor Cooper
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name  Creator's name. e.g Victor Cooper
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Creator's email address. E.g. victor@honchodesign.com
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email Creator's email address. E.g. victor@honchodesign.com
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return Type. e.g. User
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type Type. e.g. User
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return Creator's title. e.g. Chief Strategiest
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title Creator's title.  e.g. Chief Strategiest
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return Creator's bio e.g. Don't let your dreams be dreams.
     */
    public String getBio() {
        return bio;
    }

    /**
     *
     * @param bio Creator's bio e.g. Don't let your dreams be dreams.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     *
     * @return Created at Date e.g. 2016-09-22T16:21:03.625-05:00
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt Created at Date e.g. 2016-09-22T16:21:03.625-05:00
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return Updated at Date. e.g. 2016-09-22T16:21:06.184-05:00
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt Updated at Date. e.g. 2016-09-22T16:21:06.184-05:00
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return Whether the creator is an admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     *
     * @param admin Whether the creator is an admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     *
     * @return Whether the creator is the basecamp's account owner.
     */
    public boolean isOwner() {
        return owner;
    }

    /**
     *
     * @param owner Whether the creator is an owner
     */
    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    /**
     *
     * @return Creator's time zone e.g. America/Chicago
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     *
     * @param timeZone Creator's time zone e.g. America/Chicago
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     *
     * @return Creator's Avatar Url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     *
     * @param avatarUrl  Creator's Avatar Url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     *
     * @return Creator's company
     */
    public Company getCompany() {
        return company;
    }

    /**
     *
     * @param company Creator's company
     */
    public void setCompany(Company company) {
        this.company = company;
    }
}
