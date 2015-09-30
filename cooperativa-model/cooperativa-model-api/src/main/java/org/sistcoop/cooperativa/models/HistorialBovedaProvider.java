package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface HistorialBovedaProvider extends Provider {

    HistorialBovedaModel create(BovedaModel boveda);

    HistorialBovedaModel findById(String id);

    HistorialBovedaModel findByHistorialActivo(BovedaModel boveda);

    List<HistorialBovedaModel> getAll(BovedaModel boveda);

    SearchResultsModel<HistorialBovedaModel> search(BovedaModel boveda, SearchCriteriaModel criteria);

}