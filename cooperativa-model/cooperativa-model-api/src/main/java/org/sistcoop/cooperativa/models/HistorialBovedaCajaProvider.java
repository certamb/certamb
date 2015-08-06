package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface HistorialBovedaCajaProvider extends Provider {

    HistorialBovedaCajaModel create(BovedaCajaModel bovedaCajaModel);

    HistorialBovedaCajaModel findById(String id);

    SearchResultsModel<HistorialBovedaCajaModel> search(SearchCriteriaModel criteria);

}