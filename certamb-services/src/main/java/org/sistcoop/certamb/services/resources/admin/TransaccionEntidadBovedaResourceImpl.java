package org.sistcoop.certamb.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import org.sistcoop.certam.admin.client.resource.TransaccionEntidadBovedaResource;
import org.sistcoop.certamb.models.DetalleTransaccionEntidadBovedaModel;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaModel;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaProvider;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionEntidadBovedaRepresentation;

@Stateless
public class TransaccionEntidadBovedaResourceImpl implements TransaccionEntidadBovedaResource {

    @PathParam("idTransaccion")
    private String idTransaccion;

    @Inject
    private TransaccionEntidadBovedaProvider transaccionEntidadBovedaProvider;

    private TransaccionEntidadBovedaModel getTransaccionEntidadBovedaModel() {
        return transaccionEntidadBovedaProvider.findById(idTransaccion);
    }

    @Override
    public TransaccionEntidadBovedaRepresentation toRepresentation() {
        TransaccionEntidadBovedaRepresentation rep = ModelToRepresentation
                .toRepresentation(getTransaccionEntidadBovedaModel());
        if (rep != null) {
            return rep;
        } else {
            throw new NotFoundException("Transaccion no encontrada");
        }
    }

    @Override
    public List<DetalleMonedaRepresentation> detalle() {
        List<DetalleTransaccionEntidadBovedaModel> detalle = getTransaccionEntidadBovedaModel().getDetalle();
        List<DetalleMonedaRepresentation> result = new ArrayList<>();
        detalle.forEach(det -> result.add(ModelToRepresentation.toRepresentation(det)));
        return result;
    }

    @Override
    public void extornar() {
        // TODO Auto-generated method stub
        
    }

}
