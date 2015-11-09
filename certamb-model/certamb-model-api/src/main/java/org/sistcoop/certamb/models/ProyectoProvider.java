package org.sistcoop.certamb.models;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface ProyectoProvider extends Provider {

    ProyectoModel create(DireccionRegionalModel direccionReional, String denominacion, BigDecimal monto);

    ProyectoModel findById(String id);

    ProyectoModel findByDenominacion(String denominacion);

    List<ProyectoModel> getAll();

    List<ProyectoModel> getAll(DireccionRegionalModel direccionRegional);

    SearchResultsModel<ProyectoModel> search(SearchCriteriaModel criteria);

    SearchResultsModel<ProyectoModel> search(SearchCriteriaModel criteria, String filterText);

    SearchResultsModel<ProyectoModel> search(DireccionRegionalModel direccionRegional,
            SearchCriteriaModel criteria);

    SearchResultsModel<ProyectoModel> search(DireccionRegionalModel direccionRegional,
            SearchCriteriaModel criteria, String filterText);

}