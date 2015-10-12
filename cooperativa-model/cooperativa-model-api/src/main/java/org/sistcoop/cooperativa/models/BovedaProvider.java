package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface BovedaProvider extends Provider {

    BovedaModel create(String agencia, String moneda, String denominacion);

    BovedaModel findById(String id);

    List<BovedaModel> getAll();

    SearchResultsModel<BovedaModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<BovedaModel> search(SearchCriteriaModel criteria, String filterText);

}