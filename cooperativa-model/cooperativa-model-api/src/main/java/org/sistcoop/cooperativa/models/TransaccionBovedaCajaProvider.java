package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionBovedaCajaProvider extends Provider {

    TransaccionBovedaCajaModel create(HistorialBovedaModel historialBovedaModel,
            HistorialBovedaCajaModel historialBovedaCajaModel, OrigenTransaccionBovedaCaja origen,
            String observacion);

    TransaccionBovedaCajaModel findById(String id);

    boolean remove(TransaccionBovedaCajaModel transaccionBovedaCajaModel);

    SearchResultsModel<TransaccionBovedaCajaModel> search(SearchCriteriaModel criteria);

}