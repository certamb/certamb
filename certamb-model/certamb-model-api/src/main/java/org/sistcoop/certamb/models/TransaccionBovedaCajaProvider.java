package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface TransaccionBovedaCajaProvider extends Provider {

    TransaccionBovedaCajaModel create(HistorialBovedaModel historialBoveda,
            HistorialBovedaCajaModel historialBovedaCaja, OrigenTransaccionBovedaCaja origen,
            String observacion);

    TransaccionBovedaCajaModel findById(String id);

    List<TransaccionBovedaCajaModel> getAll(HistorialBovedaModel historialBoveda);

    List<TransaccionBovedaCajaModel> getAll(HistorialBovedaCajaModel historialBovedaCaja);

    SearchResultsModel<TransaccionBovedaCajaModel> search(HistorialBovedaModel historialBoveda,
            SearchCriteriaModel criteria);

    SearchResultsModel<TransaccionBovedaCajaModel> search(HistorialBovedaCajaModel historialBovedaCaja,
            SearchCriteriaModel criteria);

}