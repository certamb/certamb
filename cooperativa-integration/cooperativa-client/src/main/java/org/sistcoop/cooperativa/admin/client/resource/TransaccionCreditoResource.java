package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.TransaccionCreditoRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface TransaccionCreditoResource {

	/**
	 * Use este endpoint para extraer informacion hacerca de una
	 * TransaccionCredito por medio de su ID.
	 * 
	 * @summary Get a TransaccionCredito by ID
	 * @statuscode 200 Si la transaccionCredito fue retornada
	 *             satisfactoriamente.
	 * @return Una transaccionCredito.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TransaccionCreditoRepresentation toRepresentation();

	/**
	 * 
	 * Use este endpoint para extornar una transaccionCredito. La transaccion es
	 * identificada por su ID
	 * 
	 * @summary Extornar una transaccionCredito by ID.
	 * @statuscode 204 Si la transaccion fue extornada satisfactoriamente.
	 * @return javax.ws.rs.core.Response
	 */
	@POST
	@Path("extornar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response enable();

}
