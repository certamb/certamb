package org.sistcoop.cooperativa.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.Jsend;
import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadorResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadoresResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;

@Stateless
public class CajaTrabajadoresResourceImpl implements CajaTrabajadoresResource {

	@PathParam("caja")
	private String caja;
	
	@Inject
	private CajaProvider cajaProvider;
	
	@Inject
	private TrabajadorCajaProvider trabajadorCajaProvider;
	
	@Inject
	private RepresentationToModel representationToModel;

	@Context
	private UriInfo uriInfo;

	@Inject
	private CajaTrabajadorResource cajaTrabajadorResource;
	
	public CajaModel getCajaModel() {
		return cajaProvider.getCajaById(caja);
	}

	@Override
	public CajaTrabajadorResource trabajador(String trabajador) {
		return cajaTrabajadorResource;
	}

	@Override
	public Response create(TrabajadorCajaRepresentation trabajadorRepresentation) {
		TrabajadorCajaModel trabajadorCajaModel = representationToModel.createTrabajadorCaja(
				trabajadorRepresentation, 
				getCajaModel(),
				trabajadorCajaProvider);
		
		return Response.created(uriInfo.getAbsolutePathBuilder()
				.path(trabajadorCajaModel.getId()).build())
				.header("Access-Control-Expose-Headers", "Location")
				.entity(Jsend.getSuccessJSend(trabajadorCajaModel.getId())).build();
	}

	@Override
	public List<TrabajadorCajaRepresentation> search() {
		List<TrabajadorCajaModel> trabajadorCajaModels = getCajaModel().getTrabajadorCajas();
		List<TrabajadorCajaRepresentation> result = new ArrayList<>();
		for (TrabajadorCajaModel trabajadorCajaModel : trabajadorCajaModels) {	
			result.add(ModelToRepresentation.toRepresentation(trabajadorCajaModel));
		}
		return result;
	}

}
