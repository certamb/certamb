package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface EntidadProvider extends Provider {

    EntidadModel create(String agencia, String denominacion);

    EntidadModel findById(String id);

    EntidadModel findByDenominacion(String denominacion);

    EntidadModel findByAbreviatura(String abreviatura);

    boolean remove(EntidadModel entidad);

    List<EntidadModel> getAll();

    SearchResultsModel<EntidadModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<EntidadModel> search(SearchCriteriaModel criteria, String filterText);

}