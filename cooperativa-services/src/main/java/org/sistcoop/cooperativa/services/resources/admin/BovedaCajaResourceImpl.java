package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;

public class BovedaCajaResourceImpl implements BovedaCajaResource {

	@Inject
	private BovedaCajaProvider bovedaCajaProvider;	

	@Override
	public BovedaCajaRepresentation findById(Integer id) {
		BovedaCajaModel model = bovedaCajaProvider.getBovedaCajaById(id);
		return ModelToRepresentation.toRepresentation(model);	
	}

	@Override
	public void desactivar(Integer id) {
		BovedaCajaModel model = bovedaCajaProvider.getBovedaCajaById(id);	
		if (model == null) {
			throw new NotFoundException("BovedaCaja no encontrada");
		}
		if (!model.getEstado()) {
			throw new BadRequestException("BovedaCaja inactiva, no se puede desactivar nuevamente");
		}
		
		//validar boveda cerrada
		BovedaModel bovedaModel = model.getBoveda();		
		if (bovedaModel.isAbierto()) {
			throw new BadRequestException("La boveda debe estar cerrada");
		}
		
		//validar caja cerrada
		CajaModel cajaModel = model.getCaja();
		if (cajaModel.isAbierto()) {
			throw new BadRequestException("La caja debe estar cerrada");
		}
		
		//Caja debe tener saldo 0.00 en todas sus bovedas asignadas		
		BigDecimal saldo = model.getSaldo();
		if(saldo.compareTo(BigDecimal.ZERO) != 0) {
			throw new BadRequestException("Caja/Boveda debe tener saldo 0.00");
		}												
		
		model.desactivar();
		model.commit();
		
	}

}
