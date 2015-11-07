package org.sistcoop.certamb.models.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TRANSACCION_CUENTA_PERSONAL")
@PrimaryKeyJoinColumn
public class TransaccionCuentaPersonalEntity extends TransaccionClienteEntity {

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
    @Column(name = "MONTO")
    private BigDecimal monto;

    @Size(min = 1, max = 100)
    @Column(name = "REFERENCIA")
    private String referencia;

    public TransaccionCuentaPersonalEntity() {
        // TODO Auto-generated constructor stub
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

}
