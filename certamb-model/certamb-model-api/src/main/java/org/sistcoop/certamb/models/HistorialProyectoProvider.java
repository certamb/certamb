package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface HistorialProyectoProvider extends Provider {

    HistorialProyectoModel create(ProyectoModel proyecto);

    HistorialProyectoModel findById(String id);

    List<HistorialProyectoModel> getAll(ProyectoModel proyecto);

    SearchResultsModel<HistorialProyectoModel> search(ProyectoModel proyecto, SearchCriteriaModel criteria);

    SearchResultsModel<HistorialProyectoModel> search(ProyectoModel proyecto, SearchCriteriaModel criteria,
            String filterText);

}