package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface EntidadProvider extends Provider {

    EntidadModel create(String denominacion, String abreviatura);

    EntidadModel findById(String id);

    EntidadModel findByDenominacion(String denominacion);

    EntidadModel findByAbreviatura(String abreviatura);

    boolean remove(EntidadModel entidad);

    List<EntidadModel> getAll();

    SearchResultsModel<EntidadModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<EntidadModel> search(SearchCriteriaModel criteria, String filterText);

}