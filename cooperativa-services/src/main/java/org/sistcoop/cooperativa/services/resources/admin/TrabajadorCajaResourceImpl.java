package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.sistcoop.cooperativa.admin.client.resource.TrabajadorCajaResource;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;

@Stateless
public class TrabajadorCajaResourceImpl implements TrabajadorCajaResource {

	@Inject
	private TrabajadorCajaProvider trabajadorCajaProvider;	

	@Override
	public TrabajadorCajaRepresentation findById(Integer id) {
		TrabajadorCajaModel model = trabajadorCajaProvider.getTrabajadorCajaById(id);
		return ModelToRepresentation.toRepresentation(model);	
	}

	@Override
	public void desactivar(Integer id) {
		TrabajadorCajaModel model = trabajadorCajaProvider.getTrabajadorCajaById(id);	
		if (model == null) {
			throw new NotFoundException("TrabajadorCaja no encontrada");
		}
		if (!model.getEstado()) {
			throw new BadRequestException("TrabajadorCaja inactivo, no se puede desactivar nuevamente");
		}															
		
		model.desactivar();
		model.commit();		
	}

}
