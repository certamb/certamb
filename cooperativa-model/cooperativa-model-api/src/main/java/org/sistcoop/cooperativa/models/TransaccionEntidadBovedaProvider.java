package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionEntidadBovedaProvider extends Provider {

    TransaccionEntidadBovedaModel create(EntidadModel entidad, HistorialBovedaModel historialBoveda,
            OrigenTransaccionBovedaCaja origen, String observacion);

    TransaccionEntidadBovedaModel findById(String id);

    boolean remove(TransaccionEntidadBovedaModel transaccionEntidadBoveda);

    List<TransaccionEntidadBovedaModel> getAll(EntidadModel entidad);

    List<TransaccionEntidadBovedaModel> getAll(HistorialBovedaModel historialBoveda);

    SearchResultsModel<TransaccionEntidadBovedaModel> search(EntidadModel entidad,
            SearchCriteriaModel criteria);

    SearchResultsModel<TransaccionEntidadBovedaModel> search(HistorialBovedaModel historialBoveda,
            SearchCriteriaModel criteria);

}