package org.sistcoop.cooperativa.services.resources.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.BovedaCajasResource;
import org.sistcoop.cooperativa.admin.client.resource.BovedaResource;
import org.sistcoop.cooperativa.admin.client.resource.HistorialesBovedaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.BovedaProvider;
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.ErrorResponseException;
import org.sistcoop.cooperativa.services.managers.BovedaManager;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Boveda;

@Stateless
public class BovedaResourceImpl implements BovedaResource {

    @PathParam("idBoveda")
    private String idBoveda;

    @Inject
    private BovedaManager bovedaManager;

    @Inject
    private BovedaProvider bovedaProvider;

    @Context
    private UriInfo uriInfo;

    @Inject
    private HistorialesBovedaResource bovedaHistorialesResource;

    @Inject
    @BovedaCajas_Boveda
    private BovedaCajasResource bovedaCajasResource;

    private BovedaModel getBovedaModel() {
        return bovedaProvider.findById(idBoveda);
    }

    @Override
    public BovedaRepresentation toRepresentation() {
        BovedaRepresentation rep = ModelToRepresentation.toRepresentation(getBovedaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Boveda no encontrada");
        }
    }

    @Override
    public void update(BovedaRepresentation rep) {
        bovedaManager.updateBoveda(getBovedaModel(), rep);
    }

    @Override
    public Response disable() {
        BovedaModel boveda = getBovedaModel();
        HistorialBovedaModel historialBovedaActivo = boveda.getHistorialActivo();
        if (historialBovedaActivo != null) {
            if (historialBovedaActivo.isAbierto()) {
                return new ErrorResponseException("Boveda abierta", "Boveda abierta, no se puede desactivar",
                        Response.Status.BAD_REQUEST).getResponse();
            }

            List<DetalleHistorialBovedaModel> detalleHistorialBoveda = historialBovedaActivo.getDetalle();
            Function<DetalleHistorialBovedaModel, BigDecimal> totalMapper = detalle -> detalle.getValor()
                    .multiply(new BigDecimal(detalle.getCantidad()));

            // Hallar saldo de boveda
            BigDecimal saldoHistorialBoveda = detalleHistorialBoveda.stream().map(totalMapper)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            if (saldoHistorialBoveda.compareTo(BigDecimal.ZERO) != 0) {
                return new ErrorResponseException("Boveda saldo invalido",
                        "Boveda tiene saldo=" + saldoHistorialBoveda
                                + ", no se puede desactivar hasta que tenga 0.00 de saldo",
                        Response.Status.BAD_REQUEST).getResponse();
            }
        }

        // Hallar cajas relacionadas
        List<BovedaCajaModel> bovedaCajas = boveda.getBovedaCajas();

        Function<BovedaCajaModel, HistorialBovedaCajaModel> historialMapper = bovedaCaja -> bovedaCaja
                .getHistorialActivo();
        List<HistorialBovedaCajaModel> historialesBovedaCaja = bovedaCajas.stream().map(historialMapper)
                .filter(bovedaCaja -> bovedaCaja != null).collect(Collectors.toList());
        for (HistorialBovedaCajaModel historialBovedaCajaModel : historialesBovedaCaja) {
            if (historialBovedaCajaModel.isAbierto()) {
                return new ErrorResponseException("Caja relacionada abierta",
                        "Existe una caja abierta, la boveda no puede ser desactivada",
                        Response.Status.BAD_REQUEST).getResponse();
            }
        }

        boolean disabled = bovedaManager.disableBoveda(boveda);
        if (disabled) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Boveda no pudo ser desactivado", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public HistorialesBovedaResource historiales() {
        return bovedaHistorialesResource;
    }

    @Override
    public BovedaCajasResource bovedaCajas() {
        return bovedaCajasResource;
    }

}
