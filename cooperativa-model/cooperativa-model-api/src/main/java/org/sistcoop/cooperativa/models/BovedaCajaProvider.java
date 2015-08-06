package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface BovedaCajaProvider extends Provider {

    BovedaCajaModel create(BovedaModel bovedaModel, CajaModel cajaModel);

    BovedaCajaModel findById(String id);

    boolean remove(BovedaCajaModel bovedaCajaModel);

    SearchResultsModel<BovedaCajaModel> search(SearchCriteriaModel criteria);
   
}