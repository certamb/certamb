package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.certamb.models.enums.TipoTransaccionEntidadBoveda;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface TransaccionEntidadBovedaProvider extends Provider {

    TransaccionEntidadBovedaModel create(EntidadModel entidad, HistorialBovedaModel historialBoveda,
            OrigenTransaccionEntidadBoveda origen, TipoTransaccionEntidadBoveda tipo, String observacion);

    TransaccionEntidadBovedaModel findById(String id);

    List<TransaccionEntidadBovedaModel> getAll(EntidadModel entidad);

    List<TransaccionEntidadBovedaModel> getAll(HistorialBovedaModel historialBoveda);

    SearchResultsModel<TransaccionEntidadBovedaModel> search(EntidadModel entidad,
            SearchCriteriaModel criteria);

    SearchResultsModel<TransaccionEntidadBovedaModel> search(HistorialBovedaModel historialBoveda,
            SearchCriteriaModel criteria);

}