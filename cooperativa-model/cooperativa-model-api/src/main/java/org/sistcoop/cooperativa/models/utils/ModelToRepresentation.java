package org.sistcoop.cooperativa.models.utils;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;

public class ModelToRepresentation {

	public static BovedaRepresentation toRepresentation(BovedaModel model) {
		
		if(model == null)
			return null;
		
		BovedaRepresentation rep = new BovedaRepresentation();
		
		rep.setId(model.getId());
		rep.setAgencia(model.getAgencia());
		rep.setDenominacion(model.getDenominacion());
		rep.setMoneda(model.getMoneda());
		rep.setEstadoMovimiento(model.getEstadoMovimiento());
		rep.setEstado(model.getEstado());
		
		return rep;
	}

	public static HistorialBovedaRepresentation toRepresentation(
			HistorialBovedaModel model) {
		
		if(model == null)
			return null;
		
		HistorialBovedaRepresentation rep = new HistorialBovedaRepresentation();
		rep.setId(model.getId());
		rep.setFechaApertura(model.getFechaApertura());
		rep.setHoraApertura(model.getHoraApertura());
		rep.setFechaCierre(model.getFechaCierre());
		rep.setHoraCierre(model.getHoraCierre());
		rep.setEstado(model.getEstado());
		
		return rep;
	}

}
