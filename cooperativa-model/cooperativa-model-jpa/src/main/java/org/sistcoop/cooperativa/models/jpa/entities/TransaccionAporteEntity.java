package org.sistcoop.cooperativa.models.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TRANSACCION_APORTE")
@PrimaryKeyJoinColumn
public class TransaccionAporteEntity extends TransaccionClienteEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;

    @NotNull
    @Min(value = 2000)
    @Column(name = "ANIO")
    private int anio;

    @Min(value = 0)
    @Max(value = 11)
    @Column(name = "MES")
    private int mes;

    @NotNull
    @Column(name = "MONTO")
    private BigDecimal monto;

    public TransaccionAporteEntity() {
        // TODO Auto-generated constructor stub
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

}
