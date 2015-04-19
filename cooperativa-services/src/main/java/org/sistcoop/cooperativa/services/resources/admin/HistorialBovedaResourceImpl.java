package org.sistcoop.cooperativa.services.resources.admin;

import javax.inject.Inject;

import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaResource;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;

public class HistorialBovedaResourceImpl implements HistorialBovedaResource {

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	@Override
	public HistorialBovedaRepresentation findById(Long id) {
		HistorialBovedaModel model = historialBovedaProvider.getHistorialBovedaById(id);
		return ModelToRepresentation.toRepresentation(model);		
	}

}
