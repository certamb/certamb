package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadorResource;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;

@Stateless
public class CajaTrabajadorResourceImpl implements CajaTrabajadorResource {

    @PathParam("trabajadorCaja")
    private String trabajadorCaja;

    @Inject
    private TrabajadorCajaProvider trabajadorCajaProvider;

    private TrabajadorCajaModel getTrabajadorCajaModel() {
        return trabajadorCajaProvider.findById(trabajadorCaja);
    }

    @Override
    public TrabajadorCajaRepresentation toRepresentation() {
        TrabajadorCajaRepresentation rep = ModelToRepresentation.toRepresentation(getTrabajadorCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void update(TrabajadorCajaRepresentation trabajadorCajaRepresentation) {
        throw new NotFoundException();
    }

    @Override
    public void remove() {
        trabajadorCajaProvider.remove(getTrabajadorCajaModel());
    }

}
