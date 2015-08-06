package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface BovedaProvider extends Provider {

    BovedaModel create(String agencia, String moneda, String denominacion);

    BovedaModel findById(String id);

    boolean remove(BovedaModel bovedaModel);

    SearchResultsModel<BovedaModel> search();

    SearchResultsModel<BovedaModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<BovedaModel> search(SearchCriteriaModel criteria, String filterText);

}