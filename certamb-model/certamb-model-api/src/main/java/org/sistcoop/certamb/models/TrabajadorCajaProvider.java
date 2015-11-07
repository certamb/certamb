package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface TrabajadorCajaProvider extends Provider {

    TrabajadorCajaModel create(CajaModel caja, String tipoDocumento, String numeroDocumento);

    TrabajadorCajaModel findById(String id);

    TrabajadorCajaModel findByTipoNumeroDocumento(CajaModel caja, String tipoDocumento, String numeroDocumento);

    boolean remove(TrabajadorCajaModel trabajadorCaja);

    List<TrabajadorCajaModel> getAll(CajaModel caja);

    SearchResultsModel<TrabajadorCajaModel> search(CajaModel caja, SearchCriteriaModel criteria);

    SearchResultsModel<TrabajadorCajaModel> search(CajaModel caja, SearchCriteriaModel criteria,
            String filterText);

}