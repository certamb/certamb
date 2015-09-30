package org.sistcoop.cooperativa.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.rrhh.representations.idm.ErrorRepresentation;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public class ErrorResponse {

    public static Response exists(String message) {
        return ErrorResponse.error(message, Response.Status.CONFLICT);
    }

    public static Response error(String message, Response.Status status) {
        ErrorRepresentation error = new ErrorRepresentation();
        error.setErrorMessage(message);
        return Response.status(status).entity(error).type(MediaType.APPLICATION_JSON).build();
    }

}
