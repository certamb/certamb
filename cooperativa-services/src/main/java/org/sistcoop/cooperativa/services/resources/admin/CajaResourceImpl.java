package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.CajaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.MonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.cooperativa.services.managers.CajaManager;

@Stateless
public class CajaResourceImpl implements CajaResource {

	@Inject
	private CajaProvider cajaProvider;
	
	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;
	
	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;
	
	@Inject
	private TrabajadorCajaProvider trabajadorCajaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;
	
	@Inject
	private CajaManager cajaManager;
	
	@Context
	protected UriInfo uriInfo;

	@Override
	public CajaRepresentation findById(Integer id) {
		CajaModel model = cajaProvider.getCajaById(id);
		return ModelToRepresentation.toRepresentation(model);		
	}
	
	@Override
	public Response create(CajaRepresentation cajaRepresentation) {
		CajaModel model = representationToModel.createCaja(cajaRepresentation, cajaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId().toString()).build()).header("Access-Control-Expose-Headers", "Location").entity(Jsend.getSuccessJSend(model.getId())).build();
	}

	@Override
	public void update(Integer id, CajaRepresentation cajaRepresentation) {
		CajaModel model = cajaProvider.getCajaById(id);
		model.setDenominacion(cajaRepresentation.getDenominacion());
		model.commit();		
	}

	@Override
	public void desactivar(Integer id) {
		CajaModel model = cajaProvider.getCajaById(id);
		if (model == null) {
			throw new NotFoundException("Caja no encontrada");
		}
		if (model.isAbierto())
			throw new BadRequestException("Caja abierta, no se puede desactivar");
		
		if (!model.getEstado()) {
			throw new BadRequestException("Caja inactiva, no se puede desactivar nuevamente");
		}
		
		//Caja debe tener saldo 0.00 en todas sus bovedas asignadas
		List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			BigDecimal saldo = bovedaCajaModel.getSaldo();
			if(saldo.compareTo(BigDecimal.ZERO) != 0) {
				throw new BadRequestException("Caja/Boveda debe tener saldo 0.00");
			}
			
			HistorialBovedaCajaModel historialBovedaCajaModel = bovedaCajaModel.getHistorialActivo();
			List<DetalleHistorialBovedaCajaModel> detalleHistorialBovedaCajaModels = historialBovedaCajaModel.getDetalle();
			for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaCajaModel : detalleHistorialBovedaCajaModels) {
				if(detalleHistorialBovedaCajaModel.getCantidad() != 0){
					throw new BadRequestException("Caja/Boveda debe tener saldo 0.00");
				}
			}
		}
						
		//Validar bovedas relacionadas que esten cerradas
		for (BovedaCajaModel bovCajModel : bovedaCajaModels) {
			BovedaModel bovedaModel = bovCajModel.getBoveda();		
			if (bovedaModel.isAbierto()) {
				throw new BadRequestException("Todas las bovedas asignadas deben estar cerradas");
			}											
		}
		
		boolean result = cajaManager.desactivarCaja(model);
		if(!result) {
			throw new InternalServerErrorException("Error interno, no se pudo desactivar la Boveda");
		}
	}
	
	@Override
	public void abrir(Integer id) {
		CajaModel model = cajaProvider.getCajaById(id);
		if (model == null) {
			throw new NotFoundException("Caja no encontrada");
		}
		if (model.isAbierto()) {
			throw new BadRequestException("Caja abierta, no se puede abrir nuevamente");
		}		
		if (!model.getEstado()) {
			throw new BadRequestException("Caja inactiva, no se puede abrir");
		}
		
		//Validar bovedas relacionadas
		List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
			if(!bovedaModel.isAbierto()){
				throw new BadRequestException("Bovedas relacionadas deben estar abiertas");
			}
		}
		
		boolean result = cajaManager.abrirCaja(model);
		if(!result) {
			throw new InternalServerErrorException("Error interno, no se pudo abrir la Caja");
		}
	}	
	
	@Override
	public void cerrar(Integer id, List<MonedaRepresentation> monedas) {
		CajaModel model = cajaProvider.getCajaById(id);
		if (model == null) {
			throw new NotFoundException("Caja no encontrada");
		}
		if (!model.isAbierto()) {
			throw new BadRequestException("Caja cerrada, no se puede cerrar nuevamente");
		}		
		if (!model.getEstado()) {
			throw new BadRequestException("Caja inactiva, no se puede cerrar");
		}
		
		//Validar bovedas relacionadas
		List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
			if (!bovedaModel.isAbierto()) {
				throw new BadRequestException("Bovedas relacionadas deben estar abiertas");
			}
		}
		
		boolean result = cajaManager.cerrarCaja(model, monedas);
		if(!result) {
			throw new InternalServerErrorException("Error interno, no se pudo cerrar la Caja");
		}
	}
	
	@Override
	public void congelar(Integer id) {
		CajaModel model = cajaProvider.getCajaById(id);
		if (model == null) {
			throw new NotFoundException("Caja no encontrada");
		}
		if (!model.isAbierto()) {
			throw new BadRequestException("Caja cerrada, no se puede congelar");
		}
		if (model.getEstadoMovimiento()) {
			throw new BadRequestException("Caja congelada, no se puede congelar nuevamente");
		}
		if (!model.getEstado()) {
			throw new BadRequestException("Caja inactiva, no se puede congelar");
		}
		
		model.setEstadoMovimiento(false);
		model.commit();
	}

	@Override
	public void descongelar(Integer id) {
		CajaModel model = cajaProvider.getCajaById(id);
		if (model == null) {
			throw new NotFoundException("Caja no encontrada");
		}
		if (!model.isAbierto()) {
			throw new BadRequestException("Caja cerrada, no se puede congelar");
		}
		if (!model.getEstadoMovimiento()) {
			throw new BadRequestException("Caja descongelada, no se puede descongelar nuevamente");
		}
		if (!model.getEstado()) {
			throw new BadRequestException("Caja inactiva, no se puede congelar");
		}
		
		model.setEstadoMovimiento(true);
		model.commit();
	}
	
	@Override
	public List<MonedaRepresentation> getDetalle(Integer id) {
		//TODO here
		CajaModel model = cajaProvider.getCajaById(id);
		if (model == null) {
			throw new NotFoundException("Caja no encontrada");
		}
		List<BovedaCajaModel> bovedaCajaModels = model.getBovedaCajas();		
		List<HistorialBovedaCajaModel> historialBovedaCajaModels = new ArrayList<>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
			HistorialBovedaCajaModel historialActivo = bovedaCajaModel.getHistorialActivo();
			if(historialActivo != null) {
				historialBovedaCajaModels.add(historialActivo);
			}
		}		
		if(historialBovedaCajaModels.size() == 0){
			return null;
		}
			
		//result
		List<MonedaRepresentation> result = new ArrayList<>();
		
		for (HistorialBovedaCajaModel historialBovedaCajaModel : historialBovedaCajaModels) {
			BovedaCajaModel bovedaCajaModel = historialBovedaCajaModel.getBovedaCaja();
			BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
			List<DetalleHistorialBovedaCajaModel> detalleHistorialBovedaCajaModels = historialBovedaCajaModel.getDetalle();
			
			//Objeto a insertar en el resultado
			MonedaRepresentation monedaRepresentation = new MonedaRepresentation();
			String moneda = bovedaModel.getMoneda();
			List<DetalleMonedaRepresentation> detalle = new ArrayList<>();
						
			for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaCajaModel : detalleHistorialBovedaCajaModels) {
				int cantidad = detalleHistorialBovedaCajaModel.getCantidad();				
				BigDecimal valor = detalleHistorialBovedaCajaModel.getValor();

				DetalleMonedaRepresentation detalleMonedaRepresentation = new DetalleMonedaRepresentation();
				detalleMonedaRepresentation.setCantidad(cantidad);
				detalleMonedaRepresentation.setValor(valor);
				
				detalle.add(detalleMonedaRepresentation);
			}
			
			monedaRepresentation.setMoneda(moneda);
			monedaRepresentation.setDetalle(detalle);
			
			result.add(monedaRepresentation);			
		}
				
		return result;
	}
	
	@Override
	public List<CajaRepresentation> searchCajas(String agencia, Boolean estado,
			String filterText, Integer firstResult, Integer maxResults) {
		
		List<CajaModel> list;
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
			list = cajaProvider.getCajas(agencia, true, filterText, firstResult, maxResults);
		} else {
			list = cajaProvider.getCajas(agencia, estado, filterText, firstResult, maxResults);
		}

		List<CajaRepresentation> result = new ArrayList<CajaRepresentation>();
		for (CajaModel cajaModel : list) {
			result.add(ModelToRepresentation.toRepresentation(cajaModel));
		}
		
		return result;
		
	}

	@Override
	public List<HistorialBovedaCajaRepresentation> searchHistoriales(
			Integer id, List<String> monedas, Date desde, Date hasta,
			Integer firstResult, Integer maxResults) {
		
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
		
		CajaModel cajaModel = cajaProvider.getCajaById(id);
		List<BovedaCajaModel> bovedaCajaModels = cajaModel.getBovedaCajas();
		
		//extrayendo las monedas solicitadas
		List<BovedaCajaModel> bovedaCajaModelSolicitados = null;
		if(monedas == null || monedas.size() == 0){
			bovedaCajaModelSolicitados = bovedaCajaModels;
		} else {
			bovedaCajaModelSolicitados = new ArrayList<BovedaCajaModel>();
			for (BovedaCajaModel bovedaCajaModel : bovedaCajaModels) {
				BovedaModel bovedaModel = bovedaCajaModel.getBoveda();
				for (int i = 0; i < monedas.size(); i++) {
					if(bovedaModel.getMoneda().equals(monedas.get(i))){
						bovedaCajaModelSolicitados.add(bovedaCajaModel);
					}
				}
			}
		}
		
		//buscando los historiales solicitados
		List<HistorialBovedaCajaModel> historialBovedaCajaModelsTotal = new ArrayList<HistorialBovedaCajaModel>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajaModelSolicitados) {			
			List<HistorialBovedaCajaModel> historialBovedaCajaModels = null;
			if (desde == null || hasta == null) {
				historialBovedaCajaModels = historialBovedaCajaProvider.getHistorialesBovedaCaja(bovedaCajaModel, firstResult, maxResults);
			} else {
				historialBovedaCajaModels = historialBovedaCajaProvider.getHistorialesBovedaCaja(bovedaCajaModel, desde, hasta, firstResult, maxResults);
			}
			historialBovedaCajaModelsTotal.addAll(historialBovedaCajaModels);
		}
		
		//resultado
		List<HistorialBovedaCajaRepresentation> result = new ArrayList<HistorialBovedaCajaRepresentation>();
		for (HistorialBovedaCajaModel historialBovedaCajaModel : historialBovedaCajaModelsTotal) {
			result.add(ModelToRepresentation.toRepresentation(historialBovedaCajaModel));		
		}
		
		return result;
	}

	@Override
	public void addBovedaCaja(Integer id, List<BovedaCajaRepresentation> bovedaCajaRepresentations) {
				
		CajaModel cajaModel = cajaProvider.getCajaById(id);
		
		List<BovedaModel> bovedaModels = new ArrayList<>();
		for (BovedaCajaRepresentation bovedaCajaRepresentation : bovedaCajaRepresentations) {
			BovedaRepresentation bovedaRepresentation = bovedaCajaRepresentation.getBoveda();	
			BovedaModel bovedaModel = bovedaProvider.getBovedaById(bovedaRepresentation.getId());
			bovedaModels.add(bovedaModel);
		}
		
		cajaManager.addBovedas(cajaModel, bovedaModels);
				
	}

	@Override
	public List<BovedaCajaRepresentation> getBovedaCajas(Integer id) {
		CajaModel cajaModel = cajaProvider.getCajaById(id);
		List<BovedaCajaModel> bovedaCajas = cajaModel.getBovedaCajas();
		List<BovedaCajaRepresentation> result = new ArrayList<BovedaCajaRepresentation>();
		for (BovedaCajaModel bovedaCajaModel : bovedaCajas) {
			result.add(ModelToRepresentation.toRepresentation(bovedaCajaModel));
		}		
		return result;
	}

	@Override
	public void desactivarBovedaCaja(Integer id, Integer idBovedaCaja) {
		
		CajaModel cajaModel = cajaProvider.getCajaById(id);
		//validar caja cerrada	
		if (cajaModel.isAbierto()) {
			throw new BadRequestException("La caja debe estar cerrada");
		}
			
		BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.getBovedaCajaById(idBovedaCaja);	
		//validar boveda cerrada
		BovedaModel bovedaModel = bovedaCajaModel.getBoveda();		
		if (bovedaModel.isAbierto()) {
			throw new BadRequestException("La boveda debe estar cerrada");
		}
		//Caja debe tener saldo 0.00 en todas sus bovedas asignadas		
		BigDecimal saldo = bovedaCajaModel.getSaldo();
		if(saldo.compareTo(BigDecimal.ZERO) != 0) {
			throw new BadRequestException("Caja/Boveda debe tener saldo 0.00");
		}	
		
		bovedaCajaModel.desactivar();
		bovedaCajaModel.commit();
		
	}
	@Override
	public Response addTrabajadorCaja(Integer id, TrabajadorCajaRepresentation trabajadorCajaRepresentation) {
		CajaModel cajaModel = cajaProvider.getCajaById(id);
		String tipoDocumento = trabajadorCajaRepresentation.getTipoDocumento();
		String numeroDocumento = trabajadorCajaRepresentation.getNumeroDocumento();
		
		TrabajadorCajaModel model = trabajadorCajaProvider.addTrabajadorCaja(cajaModel, tipoDocumento, numeroDocumento);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId().toString()).build()).header("Access-Control-Expose-Headers", "Location").entity(Jsend.getSuccessJSend(model.getId())).build();
	}

	
	@Override
	public List<TrabajadorCajaRepresentation> getTrabajadorCajas(Integer id) {
		return null;
	}

}
