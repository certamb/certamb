package org.sistcoop.cooperativa.services.resources.admin.pattern;

import javax.ejb.Stateless;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialesResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;

@Stateless
public class CajaBovedaResourceImpl implements CajaBovedaResource {

	public CajaBovedaResourceImpl(CajaModel cajaModel) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BovedaRepresentation boveda() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(BovedaRepresentation bovedaRepresentation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CajaBovedaHistorialesResource historiales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	

}
