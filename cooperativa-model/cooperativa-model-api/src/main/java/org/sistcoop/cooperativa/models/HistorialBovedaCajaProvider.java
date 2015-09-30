package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface HistorialBovedaCajaProvider extends Provider {

    HistorialBovedaCajaModel create(BovedaCajaModel bovedaCajaModel);

    HistorialBovedaCajaModel findById(String id);
    
    HistorialBovedaCajaModel findByHistorialActivo(BovedaCajaModel bovedaCaja);

    List<HistorialBovedaCajaModel> getAll(BovedaCajaModel bovedaCaja);

    SearchResultsModel<HistorialBovedaCajaModel> search(BovedaCajaModel bovedaCaja,
            SearchCriteriaModel criteria);

}