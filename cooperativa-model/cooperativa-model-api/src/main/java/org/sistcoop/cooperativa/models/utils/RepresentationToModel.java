package org.sistcoop.cooperativa.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

	public BovedaModel createBoveda(BovedaRepresentation bovedaRepresentation, BovedaProvider bovedaProvider) {
		
		BovedaModel bovedaModel = bovedaProvider.addBoveda(
				bovedaRepresentation.getAgencia(), 
				bovedaRepresentation.getMoneda(),
				bovedaRepresentation.getDenominacion());
		
		return bovedaModel;
		
	}

	public CajaModel createCaja(CajaRepresentation cajaRepresentation,
			CajaProvider cajaProvider) {
		
		CajaModel cajaModel = cajaProvider.addCaja(
				cajaRepresentation.getAgencia(), 
				cajaRepresentation.getDenominacion());
				
		return cajaModel;
	}

}
