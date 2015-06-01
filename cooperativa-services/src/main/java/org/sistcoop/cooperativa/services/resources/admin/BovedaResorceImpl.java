package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.sistcoop.cooperativa.admin.client.Roles;
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

@Stateless
@SecurityDomain("keycloak")
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

	@RolesAllowed(Roles.ver_bovedas)
	@Override
	public BovedaRepresentation findById(String id) {
		BovedaModel model = bovedaProvider.getBovedaById(id);
		return ModelToRepresentation.toRepresentation(model);
	}
	
	@RolesAllowed(Roles.administrar_bovedas)
	@Override
	public Response create(BovedaRepresentation bovedaRepresentation) {	
		BovedaModel bovedaModel = representationToModel.createBoveda(bovedaRepresentation, bovedaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder()
								.path(bovedaModel.getId().toString()).build())
								.header("Access-Control-Expose-Headers", "Location")
								.entity(bovedaModel.getId()).build();		
	}

	@RolesAllowed({Roles.administrar_bovedas, Roles.administrar_bovedas_agencia})
	@Override
	public void update(String id, BovedaRepresentation bovedaRepresentation) {				
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

	@RolesAllowed({Roles.administrar_bovedas, Roles.administrar_bovedas_agencia})	
	@Override
	public void desactivar(String id) {		
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

	@RolesAllowed({Roles.administrar_bovedas, Roles.administrar_bovedas_agencia})
	@Override
	public void abrir(String id, BigDecimal[] denominaciones) {
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

	@RolesAllowed({Roles.administrar_bovedas, Roles.administrar_bovedas_agencia})
	@Override
	public void cerrar(String id) {		
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

	@RolesAllowed({Roles.administrar_bovedas, Roles.administrar_bovedas_agencia})
	@Override
	public void congelar(String id) {		
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

	@RolesAllowed({Roles.administrar_bovedas, Roles.administrar_bovedas_agencia})
	@Override
	public void descongelar(String id) {		
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

	@RolesAllowed(Roles.ver_bovedas)
	@Override
	public List<DetalleMonedaRepresentation> getDetalle(String id) {		
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

	@RolesAllowed(Roles.ver_bovedas)
	@Override
	public List<BovedaRepresentation> searchBovedas(String agencia, Boolean estado, String filterText, Integer firstResult, Integer maxResults) {
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

	@RolesAllowed(Roles.ver_bovedas)
	@Override
	public List<HistorialBovedaRepresentation> searchHistoriales(String id, Date desde, Date hasta, Integer firstResult, Integer maxResults) {
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
	
	/*@Context
    private HttpRequest httpRequest;	
	@Context 
	private SecurityContext securityContext;	
	
	private void validarAdministrarBovedasPorAgencia(String urlAgenciaDeBoveda){		
		//keycloak username
        KeycloakSecurityContext securityContextKeycloak = (KeycloakSecurityContext) httpRequest.getAttribute(KeycloakSecurityContext.class.getName());                
        AccessToken accessToken = securityContextKeycloak.getToken();             
        
		if(securityContext.isUserInRole(Roles.administrar_bovedas)){			
        	//crear trababajador
        } else if(securityContext.isUserInRole(Roles.administrar_bovedas_agencia)){        	
        	//verificar que el usuario tenga la agencia y sucursal correcta
        	String username = accessToken.getPreferredUsername();
        	
        	//obtener agencia del usuario
        	String urlAgenciaDelUsuario = null;              	
        	
        	if(!urlAgenciaDeBoveda.equals(urlAgenciaDelUsuario)) {
        		throw new InternalServerErrorException("El usuario no tiene permisos en esta agencia");
        	}
        } else {        	        	
        	throw new InternalServerErrorException();
        }   
	}*/

}
