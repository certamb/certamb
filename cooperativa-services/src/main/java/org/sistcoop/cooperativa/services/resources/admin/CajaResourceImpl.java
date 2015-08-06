package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajasResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadoresResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.services.managers.CajaManager;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Caja;

@Stateless
public class CajaResourceImpl implements CajaResource {

    @PathParam("caja")
    private String caja;

    @Inject
    private CajaManager cajaManager;

    @Inject
    private CajaProvider cajaProvider;

    @Inject
    @BovedaCajas_Caja
    private BovedaCajasResource cajaBovedasResource;

    @Inject
    private CajaTrabajadoresResource cajaTrabajadoresResource;

    private CajaModel getCajaModel() {
        return cajaProvider.findById(caja);
    }

    @Override
    public CajaRepresentation caja() {
        CajaRepresentation rep = ModelToRepresentation.toRepresentation(getCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void update(CajaRepresentation cajaRepresentation) {
        cajaManager.update(getCajaModel(), cajaRepresentation);

    }

    @Override
    public void enable() {
        throw new NotFoundException();
    }

    @Override
    public void disable() {
        cajaManager.desactivarCaja(getCajaModel());
    }

    @Override
    public void remove() {
        cajaProvider.remove(getCajaModel());
    }

    @Override
    public BovedaCajasResource bovedasCaja() {
        return cajaBovedasResource;
    }

    @Override
    public CajaTrabajadoresResource trabajadoresCaja() {
        return cajaTrabajadoresResource;
    }

}
