package org.sistcoop.cooperativa.services.resources.admin;

import javax.inject.Inject;

import org.sistcoop.cooperativa.admin.client.resource.HistorialBovedaCajaResource;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

public class HistorialBovedaCajaResourceImpl implements HistorialBovedaCajaResource {

	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;

	@Override
	public HistorialBovedaCajaRepresentation findById(Long id) {
		HistorialBovedaCajaModel model = historialBovedaCajaProvider.getHistorialBovedaCajaById(id);
		return ModelToRepresentation.toRepresentation(model);		
	}

}
