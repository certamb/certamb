package org.sistcoop.cooperativa.services.resources.admin.pattern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialesResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

@Stateless
public class CajaBovedaHistorialesResourceImpl implements CajaBovedaHistorialesResource {

	private CajaModel cajaModel;
	private BovedaModel bovedaModel;
	private BovedaCajaModel bovedaCajaModel;
	
	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;
	
	public CajaBovedaHistorialesResourceImpl(CajaModel cajaModel, BovedaModel bovedaModel, BovedaCajaModel bovedaCajaModel) {
		this.cajaModel = cajaModel;
		this.bovedaModel = bovedaModel;
		this.bovedaCajaModel = bovedaCajaModel;
	}

	@Override
	public CajaBovedaHistorialResource historial(String historial) {
		HistorialBovedaCajaModel historialBovedaCajaModel = historialBovedaCajaProvider.getHistorialBovedaCajaById(bovedaCajaModel, historial);
		return new CajaBovedaHistorialResourceImpl(cajaModel, bovedaModel, bovedaCajaModel, historialBovedaCajaModel);
	}

	@Override
	public Response create(HistorialBovedaCajaRepresentation historialBovedaCajaRepresentation) {
		throw new BadRequestException();
	}

	@Override
	public List<HistorialBovedaCajaRepresentation> search(Date desde,
			Date hasta, Integer firstResult, Integer maxResults) {

		if (firstResult == null) {
			firstResult = -1;
		}
		if (maxResults == null) {
			maxResults = -1;
		}
		if (desde == null || hasta == null) {
			desde = null;
			hasta = null;
		}
		
		List<HistorialBovedaCajaModel> historialBovedaCajaModels = null;
		if (desde == null || hasta == null) {
			historialBovedaCajaModels = historialBovedaCajaProvider.getHistorialesBovedaCaja(bovedaCajaModel, firstResult, maxResults);
		} else {
			historialBovedaCajaModels = historialBovedaCajaProvider.getHistorialesBovedaCaja(bovedaCajaModel, desde, hasta, firstResult, maxResults);
		}
		
		//resultado
		List<HistorialBovedaCajaRepresentation> result = new ArrayList<HistorialBovedaCajaRepresentation>();
		for (HistorialBovedaCajaModel historialBovedaCajaModel : historialBovedaCajaModels) {
			result.add(ModelToRepresentation.toRepresentation(historialBovedaCajaModel));		
		}
		
		return result;
	}

}
