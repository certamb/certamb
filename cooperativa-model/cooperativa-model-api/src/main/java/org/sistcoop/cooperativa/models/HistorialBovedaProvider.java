package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface HistorialBovedaProvider extends Provider {

    HistorialBovedaModel create(BovedaModel bovedaModel);

    HistorialBovedaModel findById(String id);

    SearchResultsModel<HistorialBovedaModel> search(SearchCriteriaModel criteria);

}