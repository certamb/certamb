package org.sistcoop.certamb.models.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "DETALLE_TRANSACCION_INTERNA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class DetalleTransaccionInternaEntity implements Serializable {

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
    @Column(name = "SUBTOTAL")
    protected BigDecimal subtotal;

    @Version
    private Timestamp optlk;

    public DetalleTransaccionInternaEntity() {
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

    public Timestamp getOptlk() {
        return optlk;
    }

    public void setOptlk(Timestamp optlk) {
        this.optlk = optlk;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
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
        if (!(obj instanceof DetalleTransaccionInternaEntity))
            return false;
        DetalleTransaccionInternaEntity other = (DetalleTransaccionInternaEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
