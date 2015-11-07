package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface BovedaCajaProvider extends Provider {

    BovedaCajaModel create(BovedaModel boveda, CajaModel caja);

    BovedaCajaModel findById(String id);

    BovedaCajaModel findByBovedaCaja(BovedaModel boveda, CajaModel caja);

    List<BovedaCajaModel> getAll(BovedaModel boveda);

    List<BovedaCajaModel> getAll(CajaModel caja);

    List<BovedaCajaModel> getAll(BovedaModel boveda, boolean estado);

    List<BovedaCajaModel> getAll(CajaModel caja, boolean estado);

    SearchResultsModel<BovedaCajaModel> search(BovedaModel boveda, SearchCriteriaModel criteria);

    SearchResultsModel<BovedaCajaModel> search(CajaModel caja, SearchCriteriaModel criteria);

    SearchResultsModel<BovedaCajaModel> search(BovedaModel boveda, CajaModel caja,
            SearchCriteriaModel criteria);

}