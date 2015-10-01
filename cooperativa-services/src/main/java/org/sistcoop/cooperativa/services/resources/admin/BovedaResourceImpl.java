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
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;
import org.sistcoop.cooperativa.services.ErrorResponseException;
import org.sistcoop.cooperativa.services.managers.BovedaManager;
import org.sistcoop.cooperativa.services.resources.producers.BovedaCajas_Boveda;

@Stateless
public class BovedaResourceImpl implements BovedaResource {

    @PathParam("boveda")
    private String boveda;

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
        return bovedaProvider.findById(boveda);
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
    public void update(BovedaRepresentation bovedaRepresentation) {
        bovedaManager.updateBoveda(getBovedaModel(), bovedaRepresentation);
    }

    @Override
    public Response enable() {
        throw new NotFoundException();
    }

    @Override
    public Response disable() {
        BovedaModel boveda = getBovedaModel();
        HistorialBovedaModel historialBovedaActivo = boveda.getHistorialActivo();
        if (historialBovedaActivo.isAbierto()) {
            return new ErrorResponseException("Boveda abierta", "Boveda abierta, no se puede desactivar",
                    Response.Status.BAD_REQUEST).getResponse();
        }

        // Hallar saldo de boveda
        List<DetalleHistorialBovedaModel> detalleHistorialBoveda = historialBovedaActivo.getDetalle();
        Function<DetalleHistorialBovedaModel, BigDecimal> totalMapper = detalle -> detalle.getValor()
                .multiply(new BigDecimal(detalle.getCantidad()));
        BigDecimal saldoHistorialBoveda = detalleHistorialBoveda.stream().map(totalMapper)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Hallar cajas relacionadas
        List<BovedaCajaModel> bovedaCajas = boveda.getBovedaCajas();
        List<BovedaCajaModel> bovedaCajasAbiertas = bovedaCajas.stream()
                .filter(bovedaCaja -> bovedaCaja.getHistorialActivo().isAbierto())
                .collect(Collectors.toList());
        List<BovedaCajaModel> bovedaCajasSaldo = bovedaCajas
                .stream()
                .filter(bovedaCaja -> bovedaCaja.getHistorialActivo().getSaldo().compareTo(BigDecimal.ZERO) != 0)
                .collect(Collectors.toList());

        if (saldoHistorialBoveda.compareTo(BigDecimal.ZERO) != 0 || !bovedaCajasAbiertas.isEmpty()
                || !bovedaCajasSaldo.isEmpty()) {
            String errorDescription = new String();
            if (saldoHistorialBoveda.compareTo(BigDecimal.ZERO) != 0) {
                errorDescription.concat("Boveda tiene saldo=" + saldoHistorialBoveda
                        + ", no se puede desactivar hasta que tenga 0.00 de saldo.");
            }
            if (!bovedaCajasAbiertas.isEmpty()) {
                errorDescription.concat("Boveda tiene cajas abiertas, cajas="
                        + bovedaCajasAbiertas.toString());
            }
            if (!bovedaCajasSaldo.isEmpty()) {
                errorDescription.concat("Boveda tiene cajas con saldo, cajas="
                        + bovedaCajasAbiertas.toString());
            }
            return new ErrorResponseException("Boveda estado invalido", errorDescription,
                    Response.Status.BAD_REQUEST).getResponse();
        }

        boolean disabled = bovedaManager.disableBoveda(boveda);
        if (disabled) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Boveda no pudo ser desactivado", Response.Status.BAD_REQUEST);
        }
    }

    @Override
    public Response remove() {
        BovedaModel boveda = getBovedaModel();
        if (boveda == null) {
            throw new NotFoundException("Boveda no encontrada");
        }
        boolean removed = bovedaProvider.remove(boveda);
        if (removed) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Boveda no pudo ser eliminado", Response.Status.BAD_REQUEST);
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
