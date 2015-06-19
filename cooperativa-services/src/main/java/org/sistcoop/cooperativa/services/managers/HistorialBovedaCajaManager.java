package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistorialBovedaCajaManager {

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	@Inject
	private DetalleHistorialBovedaProvider detalleHistorialBovedaProvider;

	public void abrirHistorialBovedaCaja(
			HistorialBovedaCajaModel historialBovedaCajaModel,
			BigDecimal[] denominaciones) {
		// TODO Auto-generated method stub
		
	}

	public void cerrarHistorialBovedaCaja(
			HistorialBovedaCajaModel historialBovedaCajaModel) {
		// TODO Auto-generated method stub
		
	}

	
}
