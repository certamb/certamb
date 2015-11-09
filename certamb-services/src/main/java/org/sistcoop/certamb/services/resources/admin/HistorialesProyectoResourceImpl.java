package org.sistcoop.certamb.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.sistcoop.certam.admin.client.resource.HistorialProyectoResource;
import org.sistcoop.certam.admin.client.resource.HistorialesProyectoResource;
import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class HistorialesProyectoResourceImpl implements HistorialesProyectoResource {

    @Override
    public HistorialProyectoResource proyecto(String idHistorialProyecto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response create(HistorialProyectoRepresentation rep) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<HistorialProyectoRepresentation> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchResultsRepresentation<ProyectoRepresentation> search(SearchCriteriaRepresentation criteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
