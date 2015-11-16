package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface TrabajadorProvider extends Provider {

    TrabajadorModel create(DireccionRegionalModel direccionRegional, String tipoDocumento,
            String numeroDocumento);

    boolean remove(TrabajadorModel trabajador);

    TrabajadorModel findById(String id);

    TrabajadorModel findByTipoNumeroDocumento(String tipoDocumento, String numeroDocumento);

    TrabajadorModel findByUsuario(String usuario);

    List<TrabajadorModel> getAll();

    List<TrabajadorModel> getAll(DireccionRegionalModel direccionRegional);

    SearchResultsModel<TrabajadorModel> search(SearchCriteriaModel criteriaModel);

    SearchResultsModel<TrabajadorModel> search(SearchCriteriaModel criteria, String filterText);

    SearchResultsModel<TrabajadorModel> search(DireccionRegionalModel direccionRegional,
            SearchCriteriaModel criteria);

    SearchResultsModel<TrabajadorModel> search(DireccionRegionalModel direccionRegional,
            SearchCriteriaModel criteria, String filterText);

}
