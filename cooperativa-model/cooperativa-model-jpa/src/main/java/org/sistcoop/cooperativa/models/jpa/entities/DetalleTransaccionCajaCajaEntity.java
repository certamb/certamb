package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@DiscriminatorValue(value = "caja-caja")
@NamedQueries(value = { @NamedQuery(name = "DetalleTransaccionCajaCajaEntity.findByIdTransaccionCajaCaja", query = "SELECT d FROM DetalleTransaccionCajaCajaEntity d INNER JOIN d.transaccionCajaCaja tcc WHERE tcc.id = :idTransaccionCajaCaja AND d.valor = :valor") })
public class DetalleTransaccionCajaCajaEntity extends DetalleTransaccionInternaEntity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "TRANSACCION_CAJA_CAJA_ID")
    private TransaccionCajaCajaEntity transaccionCajaCaja;

    public DetalleTransaccionCajaCajaEntity() {
        // TODO Auto-generated constructor stub
    }

    public TransaccionCajaCajaEntity getTransaccionCajaCaja() {
        return transaccionCajaCaja;
    }

    public void setTransaccionCajaCaja(TransaccionCajaCajaEntity transaccionCajaCaja) {
        this.transaccionCajaCaja = transaccionCajaCaja;
    }

}
