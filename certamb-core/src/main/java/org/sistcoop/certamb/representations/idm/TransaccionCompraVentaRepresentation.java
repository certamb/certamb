package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TransaccionCompraVentaRepresentation implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String id;
    private Long numeroOperacion;
    private LocalDate fecha;
    private LocalTime hora;
    private boolean estado;
    private String observacion;
    private HistorialBovedaCajaRepresentation historialBovedaCaja;
    private List<DetalleMonedaRepresentation> detalle;

    private String monedaRecibida;
    private String monedaEntregada;
    private BigDecimal montoRecibido;
    private BigDecimal montoEntregado;
    private BigDecimal tipoCambio;
    private String cliente;
    private String tipoTransaccion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(Long numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public HistorialBovedaCajaRepresentation getHistorialBovedaCaja() {
        return historialBovedaCaja;
    }

    public void setHistorialBovedaCaja(HistorialBovedaCajaRepresentation historialBovedaCaja) {
        this.historialBovedaCaja = historialBovedaCaja;
    }

    public List<DetalleMonedaRepresentation> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleMonedaRepresentation> detalle) {
        this.detalle = detalle;
    }

    public String getMonedaRecibida() {
        return monedaRecibida;
    }

    public void setMonedaRecibida(String monedaRecibida) {
        this.monedaRecibida = monedaRecibida;
    }

    public String getMonedaEntregada() {
        return monedaEntregada;
    }

    public void setMonedaEntregada(String monedaEntregada) {
        this.monedaEntregada = monedaEntregada;
    }

    public BigDecimal getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(BigDecimal montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    public BigDecimal getMontoEntregado() {
        return montoEntregado;
    }

    public void setMontoEntregado(BigDecimal montoEntregado) {
        this.montoEntregado = montoEntregado;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

}
