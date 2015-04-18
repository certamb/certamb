package org.sistcoop.cooperativa.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

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

}
