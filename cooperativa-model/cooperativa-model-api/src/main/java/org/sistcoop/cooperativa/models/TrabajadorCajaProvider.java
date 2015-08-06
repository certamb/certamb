package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TrabajadorCajaProvider extends Provider {

    TrabajadorCajaModel create(CajaModel cajaModel, String tipoDocumento, String numeroDocumento);

    TrabajadorCajaModel findById(String id);

    TrabajadorCajaModel findByTipoNumeroDocumento(String tipoDocumento, String numeroDocumento);

    boolean remove(TrabajadorCajaModel trabajadorCajaModel);

    SearchResultsModel<TrabajadorCajaModel> search(SearchCriteriaModel criteria);

}