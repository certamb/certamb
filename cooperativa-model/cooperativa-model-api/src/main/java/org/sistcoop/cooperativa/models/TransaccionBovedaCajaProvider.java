package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionBovedaCajaProvider extends Provider {

    TransaccionBovedaCajaModel create(HistorialBovedaModel historialBoveda,
            HistorialBovedaCajaModel historialBovedaCaja, OrigenTransaccionBovedaCaja origen,
            String observacion);

    TransaccionBovedaCajaModel findById(String id);

    boolean remove(TransaccionBovedaCajaModel transaccionBovedaCaja);

    List<TransaccionBovedaCajaModel> getAll(HistorialBovedaModel historialBoveda);

    List<TransaccionBovedaCajaModel> getAll(HistorialBovedaCajaModel historialBovedaCaja);

    SearchResultsModel<TransaccionBovedaCajaModel> search(HistorialBovedaModel historialBoveda,
            SearchCriteriaModel criteria);

    SearchResultsModel<TransaccionBovedaCajaModel> search(HistorialBovedaCajaModel historialBovedaCaja,
            SearchCriteriaModel criteria);

}