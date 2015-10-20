package org.sistcoop.cooperativa.services.resources.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.cooperativa.admin.client.resource.CajaResource;
import org.sistcoop.cooperativa.admin.client.resource.CajasResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionAporteResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionCompraVentaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionCreditoResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionCuentaPersonalResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesAporteResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCompraVentaResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCreditoResource;
import org.sistcoop.cooperativa.admin.client.resource.TransaccionesCuentasPersonalesResource;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.CajaProvider;
import org.sistcoop.cooperativa.models.ModelDuplicateException;
import org.sistcoop.cooperativa.models.search.PagingModel;
import org.sistcoop.cooperativa.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.models.utils.RepresentationToModel;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionAporteRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCompraVentaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCreditoRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionCuentaPersonalRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.cooperativa.services.ErrorResponse;

@Stateless
public class TransaccionesCreditoResourceImpl implements TransaccionesCreditoResource {

    @Override
    public TransaccionCreditoResource transaccion(String idTransaccion) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response create(TransaccionCreditoRepresentation rep) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TransaccionCreditoRepresentation> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsRepresentation<TransaccionCreditoRepresentation> search(
            SearchCriteriaRepresentation criteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
