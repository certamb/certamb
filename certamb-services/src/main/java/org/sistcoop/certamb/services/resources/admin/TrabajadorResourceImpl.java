package org.sistcoop.certamb.services.resources.admin;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.ceramb.admin.client.Roles;
import org.sistcoop.certam.admin.client.resource.TrabajadorResource;
import org.sistcoop.certamb.models.TrabajadorModel;
import org.sistcoop.certamb.models.TrabajadorProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.TrabajadorRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.managers.TrabajadorManager;

@Stateless
public class TrabajadorResourceImpl implements TrabajadorResource {

	@PathParam("idTrabajador")
	private String idTrabajador;

	@Inject
	private TrabajadorManager trabajadorManager;

	@Inject
	private TrabajadorProvider trabajadorProvider;

	private TrabajadorModel getTrabajadorModel() {
		return trabajadorProvider.findById(idTrabajador);
	}

	@RolesAllowed(value = { Roles.ver_trabajadores })
	@Override
	public TrabajadorRepresentation toRepresentation() {
		TrabajadorRepresentation rep = ModelToRepresentation.toRepresentation(getTrabajadorModel());
		if (rep != null) {
			return rep;
		} else {
			throw new NotFoundException("Trabajador no encontrado");
		}
	}

	@RolesAllowed(value = { Roles.administrar_trabajadores })
	@Override
	public void update(TrabajadorRepresentation rep) {
		trabajadorManager.update(getTrabajadorModel(), rep);
	}

	@Override
	public void updateUsuario(TrabajadorRepresentation rep) {
		trabajadorManager.updateUsuario(getTrabajadorModel(), rep.getUsuario());
	}

	@Override
	public Response removeUsuario() {
		trabajadorManager.removeUsuario(getTrabajadorModel());
		return Response.ok().build();
	}

	@RolesAllowed(value = { Roles.eliminar_trabajadores })
	@Override
	public Response remove() {
		TrabajadorModel trabajador = getTrabajadorModel();
		boolean result = trabajadorProvider.remove(trabajador);
		if (result) {
			return Response.noContent().build();
		} else {
			return ErrorResponse.error("Trabajador no pudo ser eliminado", Response.Status.BAD_REQUEST);
		}
	}

}