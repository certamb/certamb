package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface EtapaProvider extends Provider {

    EtapaModel findById(String id);

    EtapaModel findByDenominacion(String denominacion);

    List<EtapaModel> getAll();

    SearchResultsModel<EtapaModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<EtapaModel> search(SearchCriteriaModel criteria, String filterText);

}