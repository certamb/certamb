package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CajaBovedaHistorialResource {

	@GET
	public HistorialBovedaCajaRepresentation historial();

	@PUT
	public void update();

	@DELETE
	public void remove();

	@POST
	@Path("/cerrar")
	public void cerrar();

	@POST
	public void congelar();

	@POST
	public void descongelar();

	@GET
	@Path("/detalle")
	public List<DetalleMonedaRepresentation> detalle();

	@Path("/transaccionesBovedaCaja")
	public TransaccionesCajaCajaResource transaccionesCaja();

	@Path("/transaccionesCajaCaja")
	public TransaccionesBovedaCajaResource transaccionesBoveda();
	
}
