
/*
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
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
package org.sistcoop.cooperativa;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.keycloak.adapters.HttpFacade;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;

/**
 *
 * @author Juraci Paixão Kröhling <juraci at kroehling.de>
 */
public class PathBasedKeycloakConfigResolver implements KeycloakConfigResolver {

	private final Map<String, KeycloakDeployment> cache = new ConcurrentHashMap<String, KeycloakDeployment>();

    @Override
    public KeycloakDeployment resolve(HttpFacade.Request request) {
        String path = request.getHeader("referer");       
        int consoleIndex = path.indexOf("/console");
        if (consoleIndex == -1) {
            throw new IllegalStateException("Not able to resolve realm from the request path!");
        }
   
        String consoleBaseUrl = path.substring(0, consoleIndex);                     
        String realm = consoleBaseUrl.split("/")[3];              
        if(realm.equals("master")) {
        	realm = "master";
        } else if(realm.equals("sucursales")){
        	realm = "default";
        } else {
        	throw new IllegalStateException("Not able to resolve realm from the request path!");
        }

        KeycloakDeployment deployment = cache.get("sistcoop-" + realm);
        if (null == deployment) {
            // not found on the simple cache, try to load it from the file system
            InputStream is = getClass().getResourceAsStream("/sistcoop-" + realm + ".json");
            if (is == null) {            	
                throw new IllegalStateException("Not able to find the file sistcoop-" + realm + ".json");
            }
            deployment = KeycloakDeploymentBuilder.build(is);
            cache.put("sistcoop-" + realm, deployment);
        }

        return deployment;
    }
    
}