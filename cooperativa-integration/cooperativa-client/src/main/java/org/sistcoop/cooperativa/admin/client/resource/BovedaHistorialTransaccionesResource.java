package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.Path;

public interface BovedaHistorialTransaccionesResource {

	@Path("/cajas")
	public TransaccionesBovedaCajaResource transaccionesCaja();

}
