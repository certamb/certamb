package org.sistcoop.cooperativa.models.jpa.entities;

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
@Table(name = "TRANSFERENCIA_CUENTA_PERSONAL")
@PrimaryKeyJoinColumn
public class TransferenciaCuentaPersonalEntity extends TransaccionClienteEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "NUMERO_CUENTA_ORIGEN")
    private String numeroCuentaOrigen;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "NUMERO_CUENTA_DESTINO")
    private String numeroCuentaDestino;

    @NotNull
    @Min(value = 0)
    @Column(name = "MONTO")
    private BigDecimal monto;

    @Size(min = 1, max = 100)
    @Column(name = "REFERENCIA")
    private String referencia;

    public TransferenciaCuentaPersonalEntity() {
        // TODO Auto-generated constructor stub
    }

    public String getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public void setNumeroCuentaDestino(String numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
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
