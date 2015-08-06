package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface CajaProvider extends Provider {

    CajaModel create(String agencia, String denominacion);

    CajaModel findById(String id);

    boolean remove(CajaModel cajaModel);

    SearchResultsModel<CajaModel> search();

    SearchResultsModel<CajaModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<CajaModel> search(SearchCriteriaModel criteria, String filterText);

}