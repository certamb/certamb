package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface EtapaProcedimientoProvider extends Provider {

    EtapaProcedimientoModel findById(String id);

    EtapaProcedimientoModel findByDenominacion(String denominacion);

    List<EtapaProcedimientoModel> getAll();

    SearchResultsModel<EtapaProcedimientoModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<EtapaProcedimientoModel> search(SearchCriteriaModel criteria, String filterText);

}