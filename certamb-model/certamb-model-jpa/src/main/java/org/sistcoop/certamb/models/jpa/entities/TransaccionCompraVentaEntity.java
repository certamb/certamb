package org.sistcoop.certamb.models.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TRANSACCION_COMPRA_VENTA")
@PrimaryKeyJoinColumn
public class TransaccionCompraVentaEntity extends TransaccionClienteEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 3)
    @Column(name = "MONEDA_RECIBIDA")
    private String monedaRecibida;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 3)
    @Column(name = "MONEDA_ENTREGADA")
    private String monedaEntregada;

    @NotNull
    @Min(value = 0)
    @Column(name = "MONTO_RECIBIDO")
    private BigDecimal montoRecibido;

    @NotNull
    @Min(value = 0)
    @Column(name = "MONTO_ENTREGADO")
    private BigDecimal montoEntregado;

    @NotNull
    @Min(value = 0)
    @Column(name = "TIPO_CAMBIO")
    private BigDecimal tipoCambio;

    @Size(min = 1)
    @Column(name = "CLIENTE")
    private String cliente;

    @NotNull
    @NotBlank
    @Column(name = "TIPO_TRANSACCION")
    private String tipoTransaccion;

    public TransaccionCompraVentaEntity() {
        // TODO Auto-generated constructor stub
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
