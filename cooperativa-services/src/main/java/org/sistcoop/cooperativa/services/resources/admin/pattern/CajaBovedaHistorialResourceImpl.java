package org.sistcoop.cooperativa.services.resources.admin.pattern;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialTransaccionesResource;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

@Stateless
public class CajaBovedaHistorialResourceImpl implements CajaBovedaHistorialResource {

	@Override
	public HistorialBovedaCajaRepresentation historial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	

}
