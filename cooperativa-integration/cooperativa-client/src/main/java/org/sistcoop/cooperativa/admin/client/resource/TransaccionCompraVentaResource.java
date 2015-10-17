package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.TransaccionCompraVentaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface TransaccionCompraVentaResource {

	/**
	 * Use este endpoint para extraer informacion hacerca de una
	 * TransaccionCompraVenta por medio de su ID.
	 * 
	 * @summary Get a TransaccionCompraVenta by ID
	 * @statuscode 200 Si la transaccionCompraVenta fue retornada
	 *             satisfactoriamente.
	 * @return Una transaccionCompraVenta.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TransaccionCompraVentaRepresentation toRepresentation();

	/**
	 * 
	 * Use este endpoint para extornar una transaccionCompraVenta. La
	 * transaccion es identificada por su ID
	 * 
	 * @summary Extornar una transaccionCompraVenta by ID.
	 * @statuscode 204 Si la transaccion fue extornada satisfactoriamente.
	 * @return javax.ws.rs.core.Response
	 */
	@POST
	@Path("extornar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response extornar();

}
