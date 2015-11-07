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
 * Encapsulates paging information. Useful when listing beans or searching for
 * beans. In these cases the criteria might match a large number of beans, and
 * we only want to return a certain number of them.
 *
 * @author eric.wittmann@redhat.com
 */
public class PagingRepresentation implements Serializable {

    private static final long serialVersionUID = -7218662169900773534L;

    @DefaultValue("1")
    private int page;

    @DefaultValue("20")
    private int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
