package org.sistcoop.certamb.services.utils;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.resteasy.spi.HttpRequest;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.sistcoop.certamb.models.TrabajadorModel;
import org.sistcoop.certamb.models.TrabajadorProvider;

@Stateless
public class CertambSession {

    @Inject
    private TrabajadorProvider trabajadorProvider;

    public String getUsuario(HttpRequest httpRequest) {
        KeycloakSecurityContext sc = (KeycloakSecurityContext) httpRequest
                .getAttribute(KeycloakSecurityContext.class.getName());
        AccessToken accessToken = sc.getToken();

        return accessToken.getPreferredUsername();
    }

    public Set<String> getRoles(HttpRequest httpRequest) {
        KeycloakSecurityContext sc = (KeycloakSecurityContext) httpRequest
                .getAttribute(KeycloakSecurityContext.class.getName());
        AccessToken accessToken = sc.getToken();

        return accessToken.getRealmAccess().getRoles();
    }

    public TrabajadorModel getTrabajador(HttpRequest httpRequest) {
        String usuario = getUsuario(httpRequest);

        TrabajadorModel trabajadorModel = trabajadorProvider.findByUsuario(usuario);
        return trabajadorModel;
    }

}
