package org.sistcoop.cooperativa.services.resources;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.jboss.logging.Logger;
import org.sistcoop.cooperativa.messages.MessagesProvider;
import org.sistcoop.cooperativa.models.exceptions.ModelException;
import org.sistcoop.cooperativa.services.ErrorResponse;

public class ModelExceptionMapper implements ExceptionMapper<ModelException> {

    private static final Logger logger = Logger.getLogger(ModelExceptionMapper.class);

    @Inject
    private MessagesProvider messagesProvider;

    @Override
    public Response toResponse(ModelException ex) {
        String message = messagesProvider.getMessage(ex.getMessage(), ex.getParameters());

        logger.error(message, ex);
        return ErrorResponse.error(message, Response.Status.BAD_REQUEST);
    }

}
