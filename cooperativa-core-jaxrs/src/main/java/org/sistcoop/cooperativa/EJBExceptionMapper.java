package org.sistcoop.cooperativa;

import javax.ejb.EJBException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<javax.ejb.EJBException> {

	@Override
	public Response toResponse(EJBException e) {			
		
		if (e instanceof javax.ejb.EJBAccessException) {
			JsonObject model = Json.createObjectBuilder()
					.add("message", "Recurso con acceso no autorizado")
					.build();
			
			return Response.status(403).entity(model).build();
			
		} else {
			JsonObject model = Json.createObjectBuilder()
					.add("message", "Error interno en el sistema")
					.build();
			
			return Response.status(500).entity(model).build();
		}	
				
	}

}
