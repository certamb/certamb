package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries(value = { @NamedQuery(name = "DetalleTransaccionEntidadBovedaEntity.findByIdTransaccionEntidadBoveda", query = "SELECT d FROM DetalleTransaccionEntidadBovedaEntity d INNER JOIN d.transaccionEntidadBoveda teb WHERE teb.id = :idTransaccionEntidadBoveda AND d.valor = :valor") })
public class DetalleTransaccionEntidadBovedaEntity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    protected String id;

    @NotNull
    @Min(value = 0)
    @Max(value = 1000)
    @Digits(integer = 4, fraction = 2)
    @Column(name = "VALOR")
    protected BigDecimal valor;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 18, fraction = 2)
    @Column(name = "CANTIDAD")
    protected int cantidad;

    @Formula("cantidad * valor")
    @Column(name = "SUBTOTALf")
    protected BigDecimal subtotal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "TRANSACCION_ENTIDAD_BOVEDA_ID")
    private TransaccionEntidadBovedaEntity transaccionEntidadBoveda;

    @Version
    private Timestamp optlk;

    public DetalleTransaccionEntidadBovedaEntity() {
        // TODO Auto-generated constructor stub
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public TransaccionEntidadBovedaEntity getTransaccionEntidadBoveda() {
        return transaccionEntidadBoveda;
    }

    public void setTransaccionEntidadBoveda(TransaccionEntidadBovedaEntity transaccionEntidadBoveda) {
        this.transaccionEntidadBoveda = transaccionEntidadBoveda;
    }

    public Timestamp getOptlk() {
        return optlk;
    }

    public void setOptlk(Timestamp optlk) {
        this.optlk = optlk;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DetalleTransaccionEntidadBovedaEntity other = (DetalleTransaccionEntidadBovedaEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
