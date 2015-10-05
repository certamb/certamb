package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajasResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaTrabajadoresResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.ErrorResponseException;
import org.sistcoop.cooperativa.services.managers.CajaManager;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Caja;

@Stateless
public class CajaResourceImpl implements CajaResource {

    @PathParam("idCaja")
    private String idCaja;

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
        return cajaProvider.findById(idCaja);
    }

    @Override
    public CajaRepresentation toRepresentation() {
        CajaRepresentation rep = ModelToRepresentation.toRepresentation(getCajaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Caja no encontrada");
        }
    }

    @Override
    public void update(CajaRepresentation rep) {
        cajaManager.update(getCajaModel(), rep);
    }

    @Override
    public Response disable() {
        CajaModel caja = getCajaModel();
        List<BovedaCajaModel> bovedaCajas = caja.getBovedaCajas();

        Function<BovedaCajaModel, HistorialBovedaCajaModel> historialMapper = bovedaCaja -> bovedaCaja
                .getHistorialActivo();
        List<HistorialBovedaCajaModel> historialesBovedaCaja = bovedaCajas.stream().map(historialMapper)
                .filter(bovedaCaja -> bovedaCaja != null).collect(Collectors.toList());
        for (HistorialBovedaCajaModel historialBovedaCajaModel : historialesBovedaCaja) {
            if (historialBovedaCajaModel.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
                return new ErrorResponseException("Caja relacionada tiene saldo",
                        "Existe una caja con saldo diferente de 0.00, la boveda no puede ser desactivada",
                        Response.Status.BAD_REQUEST).getResponse();
            }
            if (historialBovedaCajaModel.isAbierto()) {
                return new ErrorResponseException("Caja relacionada abierta",
                        "Existe una caja abierta, la boveda no puede ser desactivada",
                        Response.Status.BAD_REQUEST).getResponse();
            }
        }

        boolean result = cajaManager.desactivarCaja(caja);
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Caja no pudo ser desactivada", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public Response remove() {
        CajaModel caja = getCajaModel();
        if (caja == null) {
            throw new NotFoundException("Caja no encontrada");
        }
        boolean removed = cajaProvider.remove(caja);
        if (removed) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Caja no pudo ser eliminado", Response.Status.BAD_REQUEST);
        }
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
