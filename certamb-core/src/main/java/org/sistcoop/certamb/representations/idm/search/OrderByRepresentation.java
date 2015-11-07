/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sistcoop.certamb.representations.idm.search;

import java.io.Serializable;

import javax.ws.rs.DefaultValue;

/**
 * Models order-by for a search.
 *
 * @author eric.wittmann@redhat.com
 */
public class OrderByRepresentation implements Serializable {

    private static final long serialVersionUID = -7569401325900866820L;

    @DefaultValue("true")
    private boolean ascending;

    private String name;

    public OrderByRepresentation() {
        // TODO Auto-generated constructor stub
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
