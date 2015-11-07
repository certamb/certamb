package org.sistcoop.certamb.models.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;

@MappedSuperclass
public abstract class DetalleHistorialEntity {

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cantidad;
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DetalleHistorialEntity))
            return false;
        DetalleHistorialEntity other = (DetalleHistorialEntity) obj;
        if (cantidad != other.cantidad)
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

}
