package org.sistcoop.certamb.services.resources.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.BovedaCajasResource;
import org.sistcoop.certam.admin.client.resource.CajaResource;
import org.sistcoop.certam.admin.client.resource.CajaTrabajadoresResource;
import org.sistcoop.certamb.models.BovedaCajaModel;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.CajaProvider;
import org.sistcoop.certamb.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.CajaRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;
import org.sistcoop.certamb.services.ErrorResponseException;
import org.sistcoop.certamb.services.managers.CajaManager;
import org.sistcoop.certamb.services.resources.producers.BovedaCajas_Caja;

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

        // Validar caja
        if (!caja.getEstado()) {
            return new ErrorResponseException("Error", "Caja inactiva", Response.Status.BAD_REQUEST)
                    .getResponse();
        }

        // Validar historialBovedaCaja
        Function<BovedaCajaModel, HistorialBovedaCajaModel> historialMapper = bovedaCaja -> bovedaCaja
                .getHistorialActivo();
        List<HistorialBovedaCajaModel> historialesBovedaCaja = bovedaCajas.stream().map(historialMapper)
                .filter(historialBovedaCaja -> historialBovedaCaja != null).collect(Collectors.toList());
        for (HistorialBovedaCajaModel historialBovedaCaja : historialesBovedaCaja) {
            if (historialBovedaCaja.isAbierto()) {
                return new ErrorResponseException("Error", "Caja abierta", Response.Status.BAD_REQUEST)
                        .getResponse();
            }

            List<DetalleHistorialBovedaCajaModel> detalleHistorialBoveda = historialBovedaCaja.getDetalle();
            Function<DetalleHistorialBovedaCajaModel, BigDecimal> mapper = detalle -> detalle.getValor()
                    .multiply(new BigDecimal(detalle.getCantidad()));
            BigDecimal saldo = detalleHistorialBoveda.stream().map(mapper).reduce(BigDecimal.ZERO,
                    BigDecimal::add);
            if (saldo.compareTo(BigDecimal.ZERO) != 0) {
                return new ErrorResponseException("Error", "Caja con dinero", Response.Status.BAD_REQUEST)
                        .getResponse();
            }
        }

        boolean result = cajaManager.disable(caja);
        if (result) {
            return Response.noContent().build();
        } else {
            return ErrorResponse.error("Caja no pudo ser desactivada", Response.Status.BAD_REQUEST);
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
