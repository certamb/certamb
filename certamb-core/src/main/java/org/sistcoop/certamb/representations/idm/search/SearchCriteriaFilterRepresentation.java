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
 * Represents a single filter or search criteria. This is used when searching
 * for beans.
 *
 * @author eric.wittmann@redhat.com
 */
public class SearchCriteriaFilterRepresentation implements Serializable {

    private static final long serialVersionUID = -1199180207971619165L;

    private String name;
    private Object value;

    @DefaultValue("eq")
    private SearchCriteriaFilterOperatorRepresentation operator;

    public SearchCriteriaFilterRepresentation() {
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SearchCriteriaFilterOperatorRepresentation getOperator() {
        return operator;
    }

    public void setOperator(SearchCriteriaFilterOperatorRepresentation operator) {
        this.operator = operator;
    }

}
