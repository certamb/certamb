package org.sistcoop.certamb.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.sistcoop.certamb.models.BovedaModel;
import org.sistcoop.certamb.models.BovedaProvider;
import org.sistcoop.certamb.models.EntidadModel;
import org.sistcoop.certamb.models.EntidadProvider;
import org.sistcoop.certamb.models.HistorialBovedaModel;
import org.sistcoop.certamb.models.HistorialBovedaProvider;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaModel;
import org.sistcoop.certamb.models.TransaccionEntidadBovedaProvider;
import org.sistcoop.certamb.models.enums.OrigenTransaccionEntidadBoveda;
import org.sistcoop.certamb.models.enums.TipoTransaccionEntidadBoveda;
import org.sistcoop.certamb.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;

public class TransaccionEntidadBovedaProviderTest extends AbstractTest {

    @Inject
    private EntidadProvider entidadProvider;

    @Inject
    private BovedaProvider bovedaProvider;

    @Inject
    private HistorialBovedaProvider historialBovedaProvider;

    @Inject
    private TransaccionEntidadBovedaProvider transaccionEntidadBovedaProvider;

    @Test
    public void create1() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);

        TransaccionEntidadBovedaModel transaccionEntidadBoveda = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.ENTIDAD,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, null);

        assertThat("model is Null", transaccionEntidadBoveda, is(notNullValue()));
        assertThat("id model is Null", transaccionEntidadBoveda.getId(), is(notNullValue()));
        assertThat("estado model is False", transaccionEntidadBoveda.getEstado(), is(true));
    }

    @Test
    public void create2() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);

        entidad.desactivar();
        entidad.commit();
        historialBoveda.desactivar();
        historialBoveda.commit();

        TransaccionEntidadBovedaModel transaccionEntidadBoveda = null;
        try {
            transaccionEntidadBoveda = transaccionEntidadBovedaProvider.create(entidad, historialBoveda,
                    OrigenTransaccionEntidadBoveda.ENTIDAD, TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO,
                    null);
        } catch (ModelReadOnlyException e) {
            log.info("Read only exception success");
        }

        assertThat("model is Null", transaccionEntidadBoveda, is(nullValue()));
    }

    @Test
    public void findById() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);

        TransaccionEntidadBovedaModel transaccionEntidadBoveda1 = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.ENTIDAD,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, null);

        String id = transaccionEntidadBoveda1.getId();
        TransaccionEntidadBovedaModel transaccionEntidadBoveda2 = transaccionEntidadBovedaProvider
                .findById(id);

        assertThat(transaccionEntidadBoveda1, is(equalTo(transaccionEntidadBoveda2)));
    }

    @Test
    public void getAll() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);

        @SuppressWarnings("unused")
        TransaccionEntidadBovedaModel transaccionEntidadBoveda1 = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.ENTIDAD,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, null);
        @SuppressWarnings("unused")
        TransaccionEntidadBovedaModel transaccionEntidadBoveda2 = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.ENTIDAD,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, null);

        List<TransaccionEntidadBovedaModel> transacciones1 = transaccionEntidadBovedaProvider.getAll(entidad);
        List<TransaccionEntidadBovedaModel> transacciones2 = transaccionEntidadBovedaProvider
                .getAll(historialBoveda);

        assertThat(transacciones1, is(notNullValue()));
        assertThat(transacciones2, is(notNullValue()));
        assertThat(transacciones1.size(), is(2));
        assertThat(transacciones2.size(), is(2));
    }

    @Test
    public void search() {
        EntidadModel entidad = entidadProvider.create("Banco de Credito del Peru", "BCP");
        BovedaModel boveda = bovedaProvider.create("agencia01", "PEN", "Boveda nuevos soles");
        HistorialBovedaModel historialBoveda = historialBovedaProvider.create(boveda);

        @SuppressWarnings("unused")
        TransaccionEntidadBovedaModel transaccionEntidadBoveda1 = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.ENTIDAD,
                TipoTransaccionEntidadBoveda.MOVIMIENTO_BANCARIO, null);
        @SuppressWarnings("unused")
        TransaccionEntidadBovedaModel transaccionEntidadBoveda2 = transaccionEntidadBovedaProvider.create(
                entidad, historialBoveda, OrigenTransaccionEntidadBoveda.BOVEDA,
                TipoTransaccionEntidadBoveda.PATRIMONIO, null);

        SearchCriteriaModel criteria1 = new SearchCriteriaModel();
        criteria1.addFilter("origen", OrigenTransaccionEntidadBoveda.ENTIDAD,
                SearchCriteriaFilterOperator.eq);
        SearchCriteriaModel criteria2 = new SearchCriteriaModel();
        criteria2.addFilter("tipo", TipoTransaccionEntidadBoveda.PATRIMONIO, SearchCriteriaFilterOperator.eq);
        SearchResultsModel<TransaccionEntidadBovedaModel> transacciones1 = transaccionEntidadBovedaProvider
                .search(entidad, criteria1);
        SearchResultsModel<TransaccionEntidadBovedaModel> transacciones2 = transaccionEntidadBovedaProvider
                .search(historialBoveda, criteria2);

        assertThat(transacciones1, is(notNullValue()));
        assertThat(transacciones2, is(notNullValue()));
        assertThat(transacciones1.getModels().size(), is(1));
        assertThat(transacciones2.getModels().size(), is(1));
    }

}
