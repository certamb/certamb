package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.Path;

public interface CajaBovedaHistorialTransaccionesResource {

	@Path("/cajas")
	public TransaccionesCajaCajaResource transaccionesCaja();

	@Path("/bovedas")
	public TransaccionesBovedaCajaResource transaccionesBoveda();

}
