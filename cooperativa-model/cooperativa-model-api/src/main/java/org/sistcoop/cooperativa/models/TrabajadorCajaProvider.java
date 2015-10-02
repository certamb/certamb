package org.sistcoop.cooperativa.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.cooperativa.models.search.SearchCriteriaModel;
import org.sistcoop.cooperativa.models.search.SearchResultsModel;
import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TrabajadorCajaProvider extends Provider {

    TrabajadorCajaModel create(CajaModel caja, String tipoDocumento, String numeroDocumento);

    TrabajadorCajaModel findById(String id);

    TrabajadorCajaModel findByTipoNumeroDocumento(CajaModel caja, String tipoDocumento, String numeroDocumento);

    boolean remove(TrabajadorCajaModel trabajadorCaja);

    List<TrabajadorCajaModel> getAll(CajaModel caja);

    SearchResultsModel<TrabajadorCajaModel> search(CajaModel caja, SearchCriteriaModel criteria);

}