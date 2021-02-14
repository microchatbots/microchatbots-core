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
package com.microchatbots.basecamp;

import io.micronaut.core.annotation.Introspected;

/**
 * @see <a href="https://github.com/basecamp/bc3-api/blob/master/sections/chatbots.md">Basecamp Chatbots</a>
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Introspected
public class Company {
    /**
     * Company Unique identifier.
     */
    private Long id;

    private String name;

    /**
     * Constructor.
     */
    public Company() {
    }

    /**
     *
     * @return Company Unique Identifier. E.g. 1033447817
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id Company Unique Identifier. e.g. 1033447817
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return Compnay
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
