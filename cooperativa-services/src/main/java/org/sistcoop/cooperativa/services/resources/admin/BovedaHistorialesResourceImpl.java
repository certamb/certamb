package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.BovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaHistorialesResource;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;

@Stateless
public class BovedaHistorialesResourceImpl implements BovedaHistorialesResource {

	private BovedaModel bovedaModel;

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	public BovedaHistorialesResourceImpl(BovedaModel bovedaModel) {
		this.bovedaModel = bovedaModel;
	}

	@Override
	public BovedaHistorialResource get(String historial) {
		HistorialBovedaModel historialBovedaModel = historialBovedaProvider.getHistorialBovedaById(bovedaModel, historial);
		return new BovedaHistorialResourceImpl(bovedaModel, historialBovedaModel);
	}

	@Override
	public Response create(HistorialBovedaRepresentation historialBovedaRepresentation) {
		throw new BadRequestException();
	}

	@Override
	public List<HistorialBovedaRepresentation> search(Date desde, Date hasta,
			Integer firstResult, Integer maxResults) {

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

		List<HistorialBovedaModel> historialBovedaModels = null;
		if (desde == null || hasta == null) {
			historialBovedaModels = historialBovedaProvider.getHistorialesBoveda(bovedaModel, firstResult, maxResults);
		} else {
			historialBovedaModels = historialBovedaProvider.getHistorialesBoveda(bovedaModel, desde, hasta, firstResult, maxResults);
		}

		List<HistorialBovedaRepresentation> result = new ArrayList<HistorialBovedaRepresentation>();
		for (HistorialBovedaModel historialBovedaModel : historialBovedaModels) {
			result.add(ModelToRepresentation.toRepresentation(historialBovedaModel));
		}

		return result;
	}

}
