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
@DiscriminatorValue(value = "boveda-caja")
@NamedQueries(value = { @NamedQuery(name = "DetalleTransaccionBovedaCajaEntity.findByIdTransaccionBovedaCaja", query = "SELECT d FROM DetalleTransaccionBovedaCajaEntity d INNER JOIN d.transaccionBovedaCaja tbc WHERE tbc.id = :idTransaccionBovedaCaja AND d.valor = :valor") })
public class DetalleTransaccionBovedaCajaEntity extends DetalleTransaccionInternaEntity implements
        Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "TRANSACCION_BOVEDA_CAJA_ID")
    private TransaccionBovedaCajaEntity transaccionBovedaCaja;

    public DetalleTransaccionBovedaCajaEntity() {
        // TODO Auto-generated constructor stub
    }

    public TransaccionBovedaCajaEntity getTransaccionBovedaCaja() {
        return transaccionBovedaCaja;
    }

    public void setTransaccionBovedaCaja(TransaccionBovedaCajaEntity transaccionBovedaCaja) {
        this.transaccionBovedaCaja = transaccionBovedaCaja;
    }

}
