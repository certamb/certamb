package org.sistcoop.cooperativa.services.resources.admin.pattern;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialesResource;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

@Stateless
public class CajaBovedaHistorialesResourceImpl implements CajaBovedaHistorialesResource {

	@Override
	public CajaBovedaHistorialResource historial(String historial) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response create(
			HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistorialBovedaCajaRepresentation> search(Date desde,
			Date hasta, Integer firstResult, Integer maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
