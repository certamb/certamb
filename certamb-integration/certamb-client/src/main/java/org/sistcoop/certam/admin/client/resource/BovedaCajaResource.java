package org.sistcoop.certam.admin.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.certamb.representations.idm.BovedaCajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface BovedaCajaResource {

	/**
	 * Use este endpoint para extraer informacion hacerca de una BovedaCaja por
	 * medio de su ID.
	 * 
	 * @summary Get a BovedaCaja by ID
	 * @statuscode 200 Si la bovedaCaja fue retornada satisfactoriamente.
	 * @return Una bovedaCaja.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BovedaCajaRepresentation toRepresentation();

	// @PUT
	// @Consumes(MediaType.APPLICATION_JSON)
	// public void update(BovedaCajaRepresentation rep);

	/**
	 * Use este endpoint para desactivar una bovedaCaja. Una bovedaCaja
	 * desactivada no podra ser usada nuevamente para realizar ninguna
	 * operacion.
	 * 
	 * @summary Disable BovedaCaja
	 * @statuscode 200 Si la bovedaCaja fue desactivada satisfactoriamente.
	 * @return javax.ws.rs.core.Response
	 */
	@POST
	@Path("disable")
	@Produces(MediaType.APPLICATION_JSON)
	public Response disable();

	@Path("historiales")
	public HistorialesBovedaCajaResource historiales();

}
