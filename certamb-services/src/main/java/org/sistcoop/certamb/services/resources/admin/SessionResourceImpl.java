package org.sistcoop.certamb.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;

import org.jboss.resteasy.spi.HttpRequest;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.sistcoop.certam.admin.client.resource.SessionResource;
import org.sistcoop.certamb.models.TrabajadorModel;
import org.sistcoop.certamb.models.TrabajadorProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.TrabajadorRepresentation;

@Stateless
public class SessionResourceImpl implements SessionResource {

	@Context
	private HttpRequest httpRequest;

	@Inject
	private TrabajadorProvider trabajadorProvider;

	@Override
	public TrabajadorRepresentation getTrabajador() {
		// Just to show how to user info from access token in REST endpoint
		KeycloakSecurityContext securityContext = (KeycloakSecurityContext) httpRequest
				.getAttribute(KeycloakSecurityContext.class.getName());
		AccessToken accessToken = securityContext.getToken();

		// Obtener Usuario
		// Set<String> roles = accessToken.getRealmAccess().getRoles();
		String usuario = accessToken.getPreferredUsername();

		TrabajadorModel trabajadorModel = trabajadorProvider.findByUsuario(usuario);
		return ModelToRepresentation.toRepresentation(trabajadorModel);
	}

	@Override
	public DireccionRegionalRepresentation getDireccionRegional() {
		// Just to show how to user info from access token in REST endpoint
		KeycloakSecurityContext securityContext = (KeycloakSecurityContext) httpRequest
				.getAttribute(KeycloakSecurityContext.class.getName());
		AccessToken accessToken = securityContext.getToken();

		// Obtener Usuario
		// Set<String> roles = accessToken.getRealmAccess().getRoles();
		String usuario = accessToken.getPreferredUsername();

		TrabajadorModel trabajadorModel = trabajadorProvider.findByUsuario(usuario);
		if (trabajadorModel != null) {
			return ModelToRepresentation.toRepresentation(trabajadorModel.getDireccionRegional());
		} else {
			return null;
		}

	}

}
