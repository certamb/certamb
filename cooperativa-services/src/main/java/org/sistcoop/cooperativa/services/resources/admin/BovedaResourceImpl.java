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
import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
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
        bovedaManager.update(getBovedaModel(), rep);
    }

    @Override
    public Response disable() {
        BovedaModel boveda = getBovedaModel();
        HistorialBovedaModel historialBovedaActivo = boveda.getHistorialActivo();

        // Validar boveda
        if (!boveda.getEstado()) {
            return new ErrorResponseException("Error", "Boveda inactiva", Response.Status.BAD_REQUEST)
                    .getResponse();
        }

        // Validar historialBoveda
        if (historialBovedaActivo != null) {
            if (historialBovedaActivo.isAbierto()) {
                return new ErrorResponseException("Error", "Boveda abierta", Response.Status.BAD_REQUEST)
                        .getResponse();
            }

            List<DetalleHistorialBovedaModel> detalleHistorialBoveda = historialBovedaActivo.getDetalle();
            Function<DetalleHistorialBovedaModel, BigDecimal> mapper = detalle -> detalle.getValor()
                    .multiply(new BigDecimal(detalle.getCantidad()));
            BigDecimal saldo = detalleHistorialBoveda.stream().map(mapper).reduce(BigDecimal.ZERO,
                    BigDecimal::add);
            if (saldo.compareTo(BigDecimal.ZERO) != 0) {
                return new ErrorResponseException("Error", "Boveda saldo=" + saldo,
                        Response.Status.BAD_REQUEST).getResponse();
            }
        }

        // Validar historialBovedaCaja
        List<BovedaCajaModel> bovedaCajas = boveda.getBovedaCajas();

        Function<BovedaCajaModel, HistorialBovedaCajaModel> mapper = bovedaCaja -> bovedaCaja
                .getHistorialActivo();
        List<HistorialBovedaCajaModel> historialesBovedaCaja = bovedaCajas.stream().map(mapper)
                .filter(historialBovedaCaja -> historialBovedaCaja != null).collect(Collectors.toList());
        for (HistorialBovedaCajaModel historialBovedaCaja : historialesBovedaCaja) {
            if (historialBovedaCaja.isAbierto()) {
                return new ErrorResponseException("Error", "Boveda tiene caja(s) abierta",
                        Response.Status.BAD_REQUEST).getResponse();
            }

            List<DetalleHistorialBovedaCajaModel> detalleHistorialBoveda = historialBovedaCaja.getDetalle();
            Function<DetalleHistorialBovedaCajaModel, BigDecimal> mapperTotal = detalle -> detalle.getValor()
                    .multiply(new BigDecimal(detalle.getCantidad()));
            BigDecimal saldo = detalleHistorialBoveda.stream().map(mapperTotal).reduce(BigDecimal.ZERO,
                    BigDecimal::add);
            if (saldo.compareTo(BigDecimal.ZERO) != 0) {
                return new ErrorResponseException("Error", "Boveda tiene caja(s) con dinero",
                        Response.Status.BAD_REQUEST).getResponse();
            }
        }

        boolean result = bovedaManager.disable(boveda);
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Boveda no pudo ser desactivada", Response.Status.BAD_REQUEST);
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
