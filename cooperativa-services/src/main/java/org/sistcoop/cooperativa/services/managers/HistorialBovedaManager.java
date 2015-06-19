package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.BovedaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistorialBovedaManager {

	public void abrirHistorialBoveda(BovedaModel bovedaModel, BigDecimal[] denominaciones) {
		// TODO Auto-generated method stub
		
	}

	public void cerrarHistorialBoveda(BovedaModel bovedaModel) {
		// TODO Auto-generated method stub
		
	}

}
