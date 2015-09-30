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
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DETALLE_TRANSACCION_CLIENTE")
public class DetalleTransaccionClienteEntity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @NotNull
    @Min(value = 0)
    @Max(value = 1000)
    @Digits(integer = 4, fraction = 2)
    @Column(name = "VALOR")
    private BigDecimal valor;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 18, fraction = 2)
    @Column(name = "CANTIDAD")
    private int cantidad;

    @Formula("cantidad * valor")
    @Column(name = "SUBTOTAL")
    private BigDecimal subtotal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "TRANSACCION_CLIENTE_ID")
    private TransaccionClienteEntity transaccionCliente;

    @Version
    private Timestamp optlk;

    public DetalleTransaccionClienteEntity() {
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

    public TransaccionClienteEntity getTransaccionCliente() {
        return transaccionCliente;
    }

    public void setTransaccionCliente(TransaccionClienteEntity transaccionCliente) {
        this.transaccionCliente = transaccionCliente;
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
        result = prime * result + ((transaccionCliente == null) ? 0 : transaccionCliente.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DetalleTransaccionClienteEntity))
            return false;
        DetalleTransaccionClienteEntity other = (DetalleTransaccionClienteEntity) obj;
        if (transaccionCliente == null) {
            if (other.transaccionCliente != null)
                return false;
        } else if (!transaccionCliente.equals(other.transaccionCliente))
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }

}
