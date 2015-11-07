/*
 * Copyright 2015 JBoss Inc
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

/**
 * The operator to use for a single search criteria filter. Typically we use eq,
 * but other operators are occassionally useful as well.
 *
 * @author eric.wittmann@redhat.com
 */
public enum SearchCriteriaFilterOperatorRepresentation {

    bool_eq, eq, neq, gt, gte, lt, lte, like;

}
