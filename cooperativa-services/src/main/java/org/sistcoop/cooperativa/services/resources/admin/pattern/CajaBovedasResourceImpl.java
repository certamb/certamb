package org.sistcoop.cooperativa.services.resources.admin.pattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedasResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialesResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaHistorialesResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;

@Stateless
public class CajaBovedasResourceImpl implements CajaBovedasResource {

	private BovedaModel bovedaModel;

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	public CajaBovedasResourceImpl(CajaModel cajaModel) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CajaBovedaResource boveda(String boveda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response create(BovedaRepresentation bovedaRepresentation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BovedaRepresentation> search() {
		// TODO Auto-generated method stub
		return null;
	}

	

}