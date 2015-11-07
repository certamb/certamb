package org.sistcoop.certam.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.certamb.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialBovedaCajaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
public interface HistorialBovedaCajaResource {

	/**
	 * Use este endpoint para extraer un historial de bovedaCaja por medio de su
	 * ID.
	 * 
	 * @summary Get a HistorialBovedaCaja by ID
	 * @statuscode 200 Si HistorialBovedaCaja fue retornada satisfactoriamente.
	 * @return Un HistorialBovedaCaja.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HistorialBovedaCajaRepresentation toRepresentation();

	/**
	 * Use este endpoint para cerrar un HistorialBovedaCaja. El cierre de un
	 * historial corresponde a los cierres despues del arqueo de cada caja.
	 * 
	 * @summary Cerrar Caja
	 * @param detalle
	 *            Detalle de cantidades y monedas con las que se desea cerrar la
	 *            caja.
	 * @statuscode 200 Si el HistorialBovedaCaja fue cerrado satisfactoriamente.
	 * @return javax.ws.rs.core.Response
	 */
	@POST
	@Path("cerrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cerrar(List<DetalleMonedaRepresentation> detalle);

	/**
	 * Use este endpoint congelar un HistorialBovedaCaja. Un historial congelado
	 * no podra realizar ninguna transaccion.
	 * 
	 * @summary Congelar HistorialBovedaCaja
	 * @statuscode 200 Si el HistorialBovedaCaja fue congelado
	 *             satisfactoriamente.
	 * @return javax.ws.rs.core.Response
	 */
	@POST
	@Path("congelar")
	public void congelar();

	/**
	 * Use este endpoint descongelar un HistorialBovedaCaja. Un historial
	 * descongelado si puede realizar transacciones.
	 * 
	 * @summary Descongelar HistorialBovedaCaja
	 * @statuscode 200 Si el HistorialBovedaCaja fue descongelado
	 *             satisfactoriamente.
	 * @return javax.ws.rs.core.Response
	 */
	@POST
	@Path("descongelar")
	public void descongelar();

	/**
	 * Use este endpoint para extraer el detalle de monedas y cantidades de un
	 * HistorialBovedaCaja por medio de su ID.
	 * 
	 * @summary Get detalle of HistorialBovedaCaja by ID
	 * @statuscode 200 Si el detalle fue retornado satisfactoriamente.
	 * @return Una detalle de monedas.
	 */
	@GET
	@Path("detalle")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DetalleMonedaRepresentation> detalle();

	@Path("transaccionesBovedaCaja")
	public TransaccionesBovedaCajaResource transaccionesBoveda();

	@Path("transaccionesCajaCaja")
	public TransaccionesCajaCajaResource transaccionesCaja();

	@Path("transaccionesAporte")
	public TransaccionesAporteResource transaccionesAporte();

	@Path("transaccionesCuentaPersonal")
	public TransaccionesCuentasPersonalesResource transaccionesCuentaPersonal();

	@Path("transaccionesCompraVenta")
	public TransaccionesCompraVentaResource transaccionesCompraVenta();

	@Path("transaccionesCredito")
	public TransaccionesCreditoResource transaccionesCredito();

}
