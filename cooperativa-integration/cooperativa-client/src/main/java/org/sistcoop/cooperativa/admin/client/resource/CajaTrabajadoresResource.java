package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.TrabajadorRepresentation;

public interface CajaTrabajadoresResource {

	@Path("/{trabajador}")
	public CajaTrabajadorResource trabajador(@PathParam("trabajador") String trabajador);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(TrabajadorRepresentation trabajadorRepresentation);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TrabajadorRepresentation> search();

}