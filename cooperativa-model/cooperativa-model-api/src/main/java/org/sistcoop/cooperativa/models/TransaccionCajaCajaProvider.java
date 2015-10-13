package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionCajaCajaProvider extends Provider {

    TransaccionCajaCajaModel create(HistorialBovedaCajaModel historialBovedaCajaOrigen,
            HistorialBovedaCajaModel historialBovedaCajaDestino, BigDecimal monto, String observacion);

    TransaccionCajaCajaModel findById(String id);

    List<TransaccionCajaCajaModel> getAll(HistorialBovedaCajaModel historialBovedaCaja);

    List<TransaccionCajaCajaModel> getAllOrigen(HistorialBovedaCajaModel historialBovedaCaja);

    List<TransaccionCajaCajaModel> getAllDestino(HistorialBovedaCajaModel historialBovedaCaja);

    SearchResultsModel<TransaccionCajaCajaModel> search(HistorialBovedaCajaModel historialBovedaCaja,
            SearchCriteriaModel criteria);

    SearchResultsModel<TransaccionCajaCajaModel> searchOrigen(HistorialBovedaCajaModel historialBovedaCaja,
            SearchCriteriaModel criteria);

    SearchResultsModel<TransaccionCajaCajaModel> searchDestino(HistorialBovedaCajaModel historialBovedaCaja,
            SearchCriteriaModel criteria);

}