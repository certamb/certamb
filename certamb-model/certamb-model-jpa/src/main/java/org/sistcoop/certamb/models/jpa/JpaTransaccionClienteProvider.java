package org.sistcoop.certamb.models.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.certamb.models.HistorialBovedaCajaModel;
import org.sistcoop.certamb.models.ModelReadOnlyException;
import org.sistcoop.certamb.models.TransaccionAporteModel;
import org.sistcoop.certamb.models.TransaccionClienteModel;
import org.sistcoop.certamb.models.TransaccionClienteProvider;
import org.sistcoop.certamb.models.TransaccionCompraVentaModel;
import org.sistcoop.certamb.models.TransaccionCuentaPersonalModel;
import org.sistcoop.certamb.models.TransferenciaCuentaPersonalModel;
import org.sistcoop.certamb.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.certamb.models.jpa.entities.TransaccionAporteEntity;
import org.sistcoop.certamb.models.jpa.entities.TransaccionClienteEntity;
import org.sistcoop.certamb.models.jpa.entities.TransaccionCompraVentaEntity;
import org.sistcoop.certamb.models.jpa.entities.TransaccionCuentaPersonalEntity;
import org.sistcoop.certamb.models.jpa.entities.TransferenciaCuentaPersonalEntity;

@Named
@Stateless
@Local(TransaccionClienteProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTransaccionClienteProvider extends AbstractHibernateStorage
        implements TransaccionClienteProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public TransaccionAporteModel createTransaccionAporte(HistorialBovedaCajaModel historialBovedaCaja,
            String numeroCuenta, BigDecimal monto, int anio, int mes, String observacion) {
        if (!historialBovedaCaja.getEstado()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaCaja (estado = false) no se puede asociar entidades");
        }

        HistorialBovedaCajaEntity historialBovedaCajaEntity = this.em.find(HistorialBovedaCajaEntity.class,
                historialBovedaCaja.getId());

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        TransaccionAporteEntity transaccionAporteEntity = new TransaccionAporteEntity();
        transaccionAporteEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);
        transaccionAporteEntity.setAnio(anio);
        transaccionAporteEntity.setMes(mes);
        transaccionAporteEntity.setMonto(monto);
        transaccionAporteEntity.setNumeroCuenta(numeroCuenta);
        transaccionAporteEntity.setObservacion(observacion);
        transaccionAporteEntity.setFecha(currentDate);
        transaccionAporteEntity.setHora(currentTime);
        transaccionAporteEntity.setEstado(true);

        em.persist(transaccionAporteEntity);
        return new TransaccionAporteAdapter(em, transaccionAporteEntity);
    }

    @Override
    public TransaccionCuentaPersonalModel createTransaccionCuentaPersonal(
            HistorialBovedaCajaModel historialBovedaCaja, String numeroCuenta, BigDecimal monto,
            String referencia, String observacion) {
        if (!historialBovedaCaja.getEstado()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaCaja (estado = false) no se puede asociar entidades");
        }

        HistorialBovedaCajaEntity historialBovedaCajaEntity = this.em.find(HistorialBovedaCajaEntity.class,
                historialBovedaCaja.getId());

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        TransaccionCuentaPersonalEntity transaccionCuentaPersonalEntity = new TransaccionCuentaPersonalEntity();
        transaccionCuentaPersonalEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);
        transaccionCuentaPersonalEntity.setNumeroCuenta(numeroCuenta);
        transaccionCuentaPersonalEntity.setMonto(monto);
        transaccionCuentaPersonalEntity.setReferencia(referencia);
        transaccionCuentaPersonalEntity.setObservacion(observacion);
        transaccionCuentaPersonalEntity.setFecha(currentDate);
        transaccionCuentaPersonalEntity.setHora(currentTime);
        transaccionCuentaPersonalEntity.setEstado(true);

        em.persist(transaccionCuentaPersonalEntity);
        return new TransaccionCuentaPersonalAdapter(em, transaccionCuentaPersonalEntity);
    }

    @Override
    public TransaccionCompraVentaModel createTransaccionCompraVenta(
            HistorialBovedaCajaModel historialBovedaCaja, String tipoTransaccion, String cliente,
            String monedaEntregada, String monedaRecibida, BigDecimal montoRecibido,
            BigDecimal montoEntregado, BigDecimal tipoCambio, String observacion) {
        if (!historialBovedaCaja.getEstado()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaCaja (estado = false) no se puede asociar entidades");
        }

        HistorialBovedaCajaEntity historialBovedaCajaEntity = this.em.find(HistorialBovedaCajaEntity.class,
                historialBovedaCaja.getId());

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        TransaccionCompraVentaEntity transaccionCompraVentaEntity = new TransaccionCompraVentaEntity();
        transaccionCompraVentaEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);
        transaccionCompraVentaEntity.setObservacion(observacion);
        transaccionCompraVentaEntity.setFecha(currentDate);
        transaccionCompraVentaEntity.setHora(currentTime);
        transaccionCompraVentaEntity.setEstado(true);

        transaccionCompraVentaEntity.setCliente(cliente);
        transaccionCompraVentaEntity.setMonedaEntregada(monedaEntregada);
        transaccionCompraVentaEntity.setMonedaRecibida(monedaRecibida);
        transaccionCompraVentaEntity.setMontoRecibido(montoRecibido);
        transaccionCompraVentaEntity.setMontoEntregado(montoEntregado);
        transaccionCompraVentaEntity.setTipoCambio(tipoCambio);
        transaccionCompraVentaEntity.setTipoTransaccion(tipoTransaccion);

        em.persist(transaccionCompraVentaEntity);
        return new TransaccionCompraVentaAdapter(em, transaccionCompraVentaEntity);
    }

    @Override
    public TransferenciaCuentaPersonalModel createTransferenciaCuentaPersonal(
            HistorialBovedaCajaModel historialBovedaCaja, BigDecimal monto, String numeroCuentaOrigen,
            String numeroCuentaDestino, String referencia, String observacion) {
        if (!historialBovedaCaja.getEstado()) {
            throw new ModelReadOnlyException(
                    "HistorialBovedaCaja (estado = false) no se puede asociar entidades");
        }

        HistorialBovedaCajaEntity historialBovedaCajaEntity = this.em.find(HistorialBovedaCajaEntity.class,
                historialBovedaCaja.getId());

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        TransferenciaCuentaPersonalEntity transferenciaCuentaPersonalEntity = new TransferenciaCuentaPersonalEntity();
        transferenciaCuentaPersonalEntity.setHistorialBovedaCaja(historialBovedaCajaEntity);
        transferenciaCuentaPersonalEntity.setObservacion(observacion);
        transferenciaCuentaPersonalEntity.setFecha(currentDate);
        transferenciaCuentaPersonalEntity.setHora(currentTime);
        transferenciaCuentaPersonalEntity.setEstado(true);

        transferenciaCuentaPersonalEntity.setMonto(monto);
        transferenciaCuentaPersonalEntity.setNumeroCuentaOrigen(numeroCuentaOrigen);
        transferenciaCuentaPersonalEntity.setNumeroCuentaDestino(numeroCuentaDestino);
        transferenciaCuentaPersonalEntity.setReferencia(referencia);

        em.persist(transferenciaCuentaPersonalEntity);
        return new TransferenciaCuentaPersonalAdapter(em, transferenciaCuentaPersonalEntity);
    }

    @Override
    public TransaccionClienteModel findById(String id) {
        TransaccionClienteEntity transaccionClienteEntity = this.em.find(TransaccionClienteEntity.class, id);
        return transaccionClienteEntity != null ? new TransaccionClienteAdapter(em, transaccionClienteEntity)
                : null;
    }

    @Override
    public TransaccionAporteModel findTransaccionAporteById(String id) {
        TransaccionAporteEntity transaccionClienteEntity = this.em.find(TransaccionAporteEntity.class, id);
        return transaccionClienteEntity != null ? new TransaccionAporteAdapter(em, transaccionClienteEntity)
                : null;
    }

    @Override
    public TransaccionCuentaPersonalModel findTransaccionCuentaPersonalById(String id) {
        TransaccionCuentaPersonalEntity transaccionClienteEntity = this.em
                .find(TransaccionCuentaPersonalEntity.class, id);
        return transaccionClienteEntity != null
                ? new TransaccionCuentaPersonalAdapter(em, transaccionClienteEntity) : null;
    }

    @Override
    public TransaccionCompraVentaModel findTransaccionCompraVentaById(String id) {
        TransaccionCompraVentaEntity transaccionClienteEntity = this.em
                .find(TransaccionCompraVentaEntity.class, id);
        return transaccionClienteEntity != null
                ? new TransaccionCompraVentaAdapter(em, transaccionClienteEntity) : null;
    }

    @Override
    public TransferenciaCuentaPersonalModel findTransferenciaCuentaPersonalById(String id) {
        TransferenciaCuentaPersonalEntity transaccionClienteEntity = this.em
                .find(TransferenciaCuentaPersonalEntity.class, id);
        return transaccionClienteEntity != null
                ? new TransferenciaCuentaPersonalAdapter(em, transaccionClienteEntity) : null;
    }

}
