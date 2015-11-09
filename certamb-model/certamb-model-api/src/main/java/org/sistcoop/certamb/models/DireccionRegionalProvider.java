package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface DireccionRegionalProvider extends Provider {

    DireccionRegionalModel create(String denominacion);

    DireccionRegionalModel findById(String id);

    DireccionRegionalModel findByDenominacion(String denominacion);

    List<DireccionRegionalModel> getAll();

    SearchResultsModel<DireccionRegionalModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<DireccionRegionalModel> search(SearchCriteriaModel criteria, String filterText);

}