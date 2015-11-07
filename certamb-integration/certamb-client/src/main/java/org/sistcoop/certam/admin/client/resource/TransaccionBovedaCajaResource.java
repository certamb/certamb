package org.sistcoop.certam.admin.client.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.certamb.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionBovedaCajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface TransaccionBovedaCajaResource {

	/**
	 * Use este endpoint para extraer informacion hacerca de una
	 * TransaccionBovedaCaja por medio de su ID.
	 * 
	 * @summary Get a TransaccionBovedaCaja by ID
	 * @statuscode 200 Si la transaccionBovedaCaja fue retornada
	 *             satisfactoriamente.
	 * @return Una transaccionBovedaCaja.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TransaccionBovedaCajaRepresentation toRepresentation();

	/**
	 * 
	 * Use este endpoint para confirmar una transaccionBovedaCaja. La
	 * transaccion es identificada por su ID
	 * 
	 * @summary Confirmar una transaccionBovedaCaja by ID.
	 * @statuscode 204 Si la transaccion fue confirmada satisfactoriamente.
	 * @return javax.ws.rs.core.Response
	 */
	@POST
	@Path("confirmar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response confirmar();

	/**
	 * 
	 * Use este endpoint para cancelar una transaccionBovedaCaja. La transaccion
	 * es identificada por su ID
	 * 
	 * @summary Cancelar una transaccionBovedaCaja by ID.
	 * @statuscode 204 Si la transaccion fue cancelada satisfactoriamente.
	 * @return javax.ws.rs.core.Response
	 */
	@POST
	@Path("cancelar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelar();

	/**
	 * 
	 * Use este endpoint para obtener el detalle de cantidad y monedas de una
	 * transaccionBovedaCaja. La transaccion es identificada por su ID
	 * 
	 * @summary Detalle una transaccionBovedaCaja by ID.
	 * @statuscode 204 Si el detalle fue retornado satisfactoriamente.
	 * @return Detalle de monedas
	 */
	@GET
	@Path("detalle")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DetalleMonedaRepresentation> detalle();

}
