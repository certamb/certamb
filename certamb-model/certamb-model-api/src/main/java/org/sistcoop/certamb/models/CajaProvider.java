package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface CajaProvider extends Provider {

    CajaModel create(String agencia, String denominacion);

    CajaModel findById(String id);

    List<CajaModel> getAll();

    SearchResultsModel<CajaModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<CajaModel> search(SearchCriteriaModel criteria, String filterText);

}