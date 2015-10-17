package org.sistcoop.cooperativa.admin.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.TransaccionAporteRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface TransaccionAporteResource {

	/**
	 * Use este endpoint para extraer informacion hacerca de una
	 * TransaccionAporte por medio de su ID.
	 * 
	 * @summary Get a TransaccionAporte by ID
	 * @statuscode 200 Si la transaccionAporte fue retornada satisfactoriamente.
	 * @return Una transaccionAporte.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TransaccionAporteRepresentation toRepresentation();

	/**
	 * 
	 * Use este endpoint para extornar una transaccion de aporte. Un extorno
	 * significa cancelar la transaccion y devolver el dinero a los implicados.
	 * La transaccion es identificada por su ID
	 * 
	 * @summary Extornar una transaccionAporte by ID.
	 * @statuscode 204 Si la transaccion fue extornada satisfactoriamente.
	 * @return javax.ws.rs.core.Response
	 */
	@POST
	@Path("extornar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response extornar();

}
