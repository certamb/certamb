package org.sistcoop.cooperativa.models.utils;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

public class ModelToRepresentation {

	public static BovedaRepresentation toRepresentation(BovedaModel model) {
		BovedaRepresentation rep = new BovedaRepresentation();
		
		rep.setId(model.getId());
		rep.setAgencia(model.getAgencia());
		rep.setDenominacion(model.getDenominacion());
		rep.setMoneda(model.getMoneda());
		rep.setEstadoMovimiento(model.getEstadoMovimiento());
		rep.setEstado(model.getEstado());
		
		return rep;
	}

}
