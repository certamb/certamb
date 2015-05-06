package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.services.managers.BovedaManager;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/bovedas")
@Api(value = "/bovedas", description = "Operaciones sobre boveda")
@Stateless
public class BovedaResorceImpl implements BovedaResource {

	@Inject
	private BovedaProvider bovedaProvider;

	@Inject
	private HistorialBovedaProvider historialBovedaProvider;

	@Inject
	private RepresentationToModel representationToModel;

	@Inject
	private BovedaManager bovedaManager;

	@Context
	protected UriInfo uriInfo;

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Buscar boveda por ID", notes = "Busca bovedas activas e inactivas", response = BovedaRepresentation.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "ID invalido"), @ApiResponse(code = 404, message = "Boveda no encontrada") })
	public BovedaRepresentation findById(
			@ApiParam(value = "ID de boveda", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id) {
		
		//TODO here
		BovedaModel model = bovedaProvider.getBovedaById(id);
		return ModelToRepresentation.toRepresentation(model);
	}

	@POST
	@ApiOperation(value = "Crear boveda", notes = "Crear una boveda en el sistema")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Boveda creada"), @ApiResponse(code = 404, message = "Error al crear boveda") })
	public Response create(
			@ApiParam(value = "boveda", required = true)
			@NotNull
			@Valid BovedaRepresentation bovedaRepresentation) {	
		
		//TODO here
		BovedaModel bovedaModel = representationToModel.createBoveda(bovedaRepresentation, bovedaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder()
								.path(bovedaModel.getId().toString()).build())
								.header("Access-Control-Expose-Headers", "Location")
								.entity(bovedaModel.getId()).build();		
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Actualizar boveda", notes = "Actualiza boveda (Actualiza solo el atributo denominacion)")	
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Boveda actualizada"), @ApiResponse(code = 404, message = "Error al actualizar boveda") })
	public void update(
			@ApiParam(value = "ID de boveda", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id, 
			
			@ApiParam(value = "boveda", required = true)
			@NotNull
			@Valid BovedaRepresentation bovedaRepresentation) {
		
		//TODO here
		BovedaModel model = bovedaProvider.getBovedaById(id);
		if (model == null) {
			throw new NotFoundException("Boveda not found");
		}
		if (!model.getEstado()) {
			throw new BadRequestException("Boveda inactiva, no se puede actualizar");
		}

		model.setDenominacion(bovedaRepresentation.getDenominacion());
		model.commit();
	}

	@POST
	@Path("/{id}/desactivar")
	@ApiOperation(value = "Desactivar boveda", notes = "Desactiva boveda y BovedaCaja's relacionadas")	
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Boveda desactivada"), @ApiResponse(code = 404, message = "Error al desactivar boveda") })	
	public void desactivar(
			@ApiParam(value = "ID de boveda", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id) {
		
		//TODO here
		BovedaModel model = bovedaProvider.getBovedaById(id);
		if (model == null) {
			throw new NotFoundException("Boveda not found.");
		}
		if (model.isAbierto()) {
			throw new BadRequestException("Boveda abierta, no se puede desactivar");	
		}			
		if (!model.getEstado()) {
			throw new BadRequestException("Boveda inactiva, no se puede desactivar nuevamente");
		}

		// Boveda debe tener saldo 0.00
		HistorialBovedaModel historialBovedaModel = model.getHistorialActivo();
		List<DetalleHistorialBovedaModel> detalleHistorialBovedaModels = historialBovedaModel.getDetalle();
		for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalleHistorialBovedaModels) {
			if (detalleHistorialBovedaModel.getCantidad() != 0) {
				throw new BadRequestException("Boveda debe tener saldo 0.00");
			}
		}

		// Validar cajas relacionadas
		List<BovedaCajaModel> list = model.getBovedaCajas();
		for (BovedaCajaModel bovCajModel : list) {
			BigDecimal saldo = bovCajModel.getSaldo();
			CajaModel caja = bovCajModel.getCaja();
			if (caja.isAbierto()) {
				throw new BadRequestException("Boveda debe tener todas sus cajas cerradas");
			}
			if (saldo.compareTo(BigDecimal.ZERO) != 0) {
				throw new BadRequestException("Boveda debe tener todas sus cajas con saldo 0.00");
			}
		}

		boolean result = bovedaManager.desactivarBoveda(model);
		if (!result) {
			throw new InternalServerErrorException("Error interno, no se pudo desactivar la Boveda");
		}
	}

	@POST
	@Path("/{id}/abrir")
	@ApiOperation(value = "Abrir boveda", notes = "Abre boveda")	
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Boveda abierta"), @ApiResponse(code = 404, message = "Error al abrir boveda") })	
	public void abrir(
			@ApiParam(value = "ID de boveda", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id, 
			
			@ApiParam(value = "Array de denominaciones con las que se abrira la boveda", required = false)
			BigDecimal[] denominaciones) {

		//TODO here
		BovedaModel model = bovedaProvider.getBovedaById(id);
		if (model == null) {
			throw new NotFoundException("Boveda no encontrada.");
		}
		if (model.isAbierto()) {
			throw new BadRequestException("Boveda abierta, no se puede abrir nuevamente.");
		}
		if (!model.getEstado()) {
			throw new BadRequestException("Boveda inactiva, no se puede abrir");
		}

		// Validar cajas relacionadas
		List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			CajaModel cajaModel = bovedaCajaModel.getCaja();
			if (cajaModel.isAbierto()) {
				throw new BadRequestException("Cajas relacionadas deben estar cerradas");
			}
		}

		boolean result = bovedaManager.abrirBoveda(model, denominaciones);
		if (!result) {
			throw new InternalServerErrorException("Error interno, no se pudo abrir la Boveda");
		}
	}

	@POST
	@Path("/{id}/cerrar")
	@ApiOperation(value = "Abrir boveda", notes = "Cierra boveda")	
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Boveda cerrada"), @ApiResponse(code = 404, message = "Error al cerrar boveda") })	
	public void cerrar(
			@ApiParam(value = "ID de boveda", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id) {
		
		//TODO here
		BovedaModel model = bovedaProvider.getBovedaById(id);
		if (model == null) {
			throw new NotFoundException("Boveda no encontrada");
		}
		if (!model.isAbierto()) {
			throw new BadRequestException("Boveda cerrada, no se puede cerrar nuevamente.");
		}
		if (!model.getEstado()) {
			throw new BadRequestException("Boveda inactiva, no se puede cerrar.");
		}

		List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			CajaModel cajaModel = bovedaCajaModel.getCaja();
			if (cajaModel.isAbierto()) {
				throw new BadRequestException("Boveda tiene cajas abiertas, no se puede cerrar");
			}
		}

		boolean result = bovedaManager.cerrarBoveda(model);
		if (!result) {
			throw new InternalServerErrorException("Error interno, no se pudo cerrar la Boveda");
		}
	}

	@POST
	@Path("/{id}/congelar")
	@ApiOperation(value = "Congelar boveda", notes = "Congela boveda")	
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Boveda congelada"), @ApiResponse(code = 404, message = "Error al congelar boveda") })
	public void congelar(
			@ApiParam(value = "ID de boveda", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id) {
		
		//TODO here
		BovedaModel model = bovedaProvider.getBovedaById(id);
		if (model == null) {
			throw new NotFoundException("Boveda no encontrada");
		}
		if (!model.isAbierto()) {
			throw new BadRequestException(
					"Boveda cerrada, no se puede congelar");
		}
		if (model.getEstadoMovimiento()) {
			throw new BadRequestException(
					"Boveda congelada, no se puede congelar nuevamente");
		}
		if (!model.getEstado()) {
			throw new BadRequestException(
					"Boveda inactiva, no se puede congelar");
		}

		model.setEstadoMovimiento(false);
		model.commit();
	}

	@POST
	@Path("/{id}/descongelar")
	@ApiOperation(value = "Descongelar boveda", notes = "Descongela boveda")	
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Boveda descongelada"),@ApiResponse(code = 404, message = "Error al descongelar boveda") })	
	public void descongelar(
			@ApiParam(value = "ID de boveda", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id) {
		
		//TODO here
		BovedaModel model = bovedaProvider.getBovedaById(id);
		if (model == null) {
			throw new NotFoundException("Boveda no encontrada");
		}
		if (!model.isAbierto()) {
			throw new BadRequestException("Boveda cerrada, no se puede congelar");
		}
		if (!model.getEstadoMovimiento()) {
			throw new BadRequestException("Boveda descongelada, no se puede descongelar nuevamente");
		}
		if (!model.getEstado()) {
			throw new BadRequestException("Boveda inactiva, no se puede congelar");
		}

		model.setEstadoMovimiento(true);
		model.commit();
	}

	@GET
	@Path("/{id}/detalle")
	@ApiOperation(value = "Detalle de monedas en boveda", notes = "Devuelve las denominaciones de moneda que tiene el historial activo de la boveda",response = List.class)	
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Boveda abierta"),@ApiResponse(code = 404, message = "Error al abrir boveda") })	
	public List<DetalleMonedaRepresentation> getDetalle(
			@ApiParam(value = "ID de boveda", required = true)
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id) {
		
		//TODO here
		BovedaModel model = bovedaProvider.getBovedaById(id);
		if (model == null) {
			throw new NotFoundException("Boveda no encontrada");
		}
		HistorialBovedaModel historialBovedaModel = model.getHistorialActivo();
		if (historialBovedaModel == null) {
			return null;
		}

		List<DetalleHistorialBovedaModel> detalle = historialBovedaModel.getDetalle();
		List<DetalleMonedaRepresentation> result = new ArrayList<DetalleMonedaRepresentation>();
		for (DetalleHistorialBovedaModel detalleHistorialBovedaModel : detalle) {
			int cantidad = detalleHistorialBovedaModel.getCantidad();
			BigDecimal valor = detalleHistorialBovedaModel.getValor();

			DetalleMonedaRepresentation rep = new DetalleMonedaRepresentation();
			rep.setCantidad(cantidad);
			rep.setValor(valor);

			result.add(rep);
		}

		return result;

	}

	@GET	
	@ApiOperation(value = "Busca bovedas", notes = "Busca bovedas segun los parametros enviados",response = List.class)	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busqueda completada"),@ApiResponse(code = 404, message = "Error al buscar boveda") })	
	public List<BovedaRepresentation> searchBovedas(
			@ApiParam(value = "Codigo de agencia", required = false)
			@QueryParam("agencia")
			@Size(min = 1, max = 100) String agencia,
			
			@ApiParam(value = "Indica buscar bovedas activas o inactivas", required = false)
			@QueryParam("estado") Boolean estado,
			
			@ApiParam(value = "Busca coincidencias segun el filtro", required = false)
			@QueryParam("filterText")
			@Size(min = 0, max = 100) String filterText, 
			
			@ApiParam(value = "Para indicar el puntero del primer resultado", required = false)
			@QueryParam("firstResult") 
			@Min(value = 0) Integer firstResult, 
			
			@ApiParam(value = "Para indicar el  puntero del ultimo resultado", required = false)
			@QueryParam("maxResults") 
			@Min(value = 1) Integer maxResults) {

		//TODO here
		List<BovedaModel> list;
		if (estado == null) {
			if (filterText == null) {
				filterText = "";
			}
			if (firstResult == null) {
				firstResult = -1;
			}
			if (maxResults == null) {
				maxResults = -1;
			}
			list = bovedaProvider.getBovedas(agencia, true, filterText,
					firstResult, maxResults);
		} else {
			list = bovedaProvider.getBovedas(agencia, estado, filterText,
					firstResult, maxResults);
		}

		List<BovedaRepresentation> result = new ArrayList<BovedaRepresentation>();
		for (BovedaModel bovedaModel : list) {
			result.add(ModelToRepresentation.toRepresentation(bovedaModel));
		}

		return result;
	}

	/*
	 * * Historial boveda*
	 */

	@GET
	@Path("/{id}/historiales")
	@ApiOperation(value = "Buscar historiales boveda", notes = "Busca historiales de boveda segun los parametros enviados",response = HistorialBovedaRepresentation.class)	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busqueda completada"),@ApiResponse(code = 404, message = "Error al buscar boveda historial") })	
	public List<HistorialBovedaRepresentation> searchHistoriales(
			@ApiParam(value = "ID de boveda", required = true)
			@PathParam("id") 
			@NotNull
			@Min(value = 1) Integer id,
			
			@ApiParam(value = "Historiales mayores que DESDE", required = false)
			@QueryParam("desde") Date desde,
			
			@ApiParam(value = "Historiales menores que HASTA", required = false)
			@QueryParam("hasta") Date hasta,
			
			@ApiParam(value = "Para indicar el puntero del primer resultado", required = false)
			@QueryParam("firstResult") 
			@Min(value = 0) Integer firstResult, 
			
			@ApiParam(value = "Para indicar el  puntero del ultimo resultado", required = false)
			@QueryParam("maxResults") 
			@Min(value = 1) Integer maxResults) {

		//TODO here
		if (firstResult == null) {
			firstResult = -1;
		}
		if (maxResults == null) {
			maxResults = -1;
		}
		if (desde == null || hasta == null) {
			desde = null;
			hasta = null;
		}

		BovedaModel bovedaModel = bovedaProvider.getBovedaById(id);
		List<HistorialBovedaModel> historialBovedaModels = null;
		if (desde == null || hasta == null) {
			historialBovedaModels = historialBovedaProvider
					.getHistorialesBoveda(bovedaModel, firstResult, maxResults);
		} else {
			historialBovedaModels = historialBovedaProvider
					.getHistorialesBoveda(bovedaModel, desde, hasta,
							firstResult, maxResults);
		}

		List<HistorialBovedaRepresentation> result = new ArrayList<HistorialBovedaRepresentation>();
		for (HistorialBovedaModel historialBovedaModel : historialBovedaModels) {
			result.add(ModelToRepresentation
					.toRepresentation(historialBovedaModel));
		}

		return result;

	}

}
