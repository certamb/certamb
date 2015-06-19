package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.BadRequestException;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialTransaccionesResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

@Stateless
public class CajaBovedaHistorialResourceImpl implements CajaBovedaHistorialResource {

	private CajaModel cajaModel;
	private BovedaModel bovedaModel;
	private BovedaCajaModel bovedaCajaModel;
	private HistorialBovedaCajaModel historialBovedaCajaModel;
	
	public CajaBovedaHistorialResourceImpl(CajaModel cajaModel,
			BovedaModel bovedaModel, BovedaCajaModel bovedaCajaModel,
			HistorialBovedaCajaModel historialBovedaCajaModel) {
		this.cajaModel = cajaModel;
		this.bovedaModel = bovedaModel;
		this.bovedaCajaModel = bovedaCajaModel;
		this.historialBovedaCajaModel = historialBovedaCajaModel;
	}

	@Override
	public HistorialBovedaCajaRepresentation historial() {
		return ModelToRepresentation.toRepresentation(historialBovedaCajaModel);
	}

	@Override
	public void update() {
		throw new BadRequestException();
	}

	@Override
	public void remove() {
		throw new BadRequestException();
	}

	@Override
	public void abrir(BigDecimal[] denominaciones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cerrar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void congelar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void descongelar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DetalleMonedaRepresentation> detalle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CajaBovedaHistorialTransaccionesResource transacciones() {
		return new CajaBovedaHistorialTransaccionesResourceImpl(cajaModel,
				bovedaModel, bovedaCajaModel, historialBovedaCajaModel);
	}

}
