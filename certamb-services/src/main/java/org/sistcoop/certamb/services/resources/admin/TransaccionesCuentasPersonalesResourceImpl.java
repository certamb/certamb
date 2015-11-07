package org.sistcoop.certamb.services.resources.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.certam.admin.client.resource.CajaResource;
import org.sistcoop.certam.admin.client.resource.CajasResource;
import org.sistcoop.certam.admin.client.resource.TransaccionCuentaPersonalResource;
import org.sistcoop.certam.admin.client.resource.TransaccionesCuentasPersonalesResource;
import org.sistcoop.certamb.models.CajaModel;
import org.sistcoop.certamb.models.CajaProvider;
import org.sistcoop.certamb.models.ModelDuplicateException;
import org.sistcoop.certamb.models.search.PagingModel;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.models.utils.ModelToRepresentation;
import org.sistcoop.certamb.models.utils.RepresentationToModel;
import org.sistcoop.certamb.representations.idm.CajaRepresentation;
import org.sistcoop.certamb.representations.idm.TransaccionCuentaPersonalRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.certamb.services.ErrorResponse;

@Stateless
public class TransaccionesCuentasPersonalesResourceImpl implements TransaccionesCuentasPersonalesResource {

    @Override
    public TransaccionCuentaPersonalResource transaccion(String idTransaccion) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response create(TransaccionCuentaPersonalRepresentation rep) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TransaccionCuentaPersonalRepresentation> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsRepresentation<TransaccionCuentaPersonalRepresentation> search(
            SearchCriteriaRepresentation criteria) {
        // TODO Auto-generated method stub
        return null;
    }

   

}
