package org.sistcoop.certamb.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.EstadoProcedimientoResource;
import org.sistcoop.certam.admin.client.resource.EstadosProcedimientoResource;
import org.sistcoop.certamb.representations.idm.EstadoProcedimientoRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class EstadosProcedimientoResourceImpl implements EstadosProcedimientoResource {

    @Override
    public EstadoProcedimientoResource estadoProcedimiento(String idEstadoProcedimiento) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response create(EstadoProcedimientoRepresentation rep) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EstadoProcedimientoRepresentation> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsRepresentation<EstadoProcedimientoRepresentation> search(
            SearchCriteriaRepresentation criteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
