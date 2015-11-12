package org.sistcoop.certamb.models.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "SUGERENCIA")
@NamedQueries(value = {
        @NamedQuery(name = "SugerenciaEntity.findAll", query = "SELECT s FROM SugerenciaEntity s"),
        @NamedQuery(name = "SugerenciaEntity.findByIdProcedimiento", query = "SELECT s FROM SugerenciaEntity s INNER JOIN s.procedimiento p WHERE p.id =:idProcedimiento") })
public class SugerenciaEntity implements Serializable {

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
    @Min(0)
    @Column(name = "PRIORIDAD")
    private int prioridad;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROCEDIMIENTO_ID", foreignKey = @ForeignKey )
    private ProcedimientoEntity procedimiento;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROCEDIMIENTO_SUGERENCIA_ID", foreignKey = @ForeignKey )
    private ProcedimientoEntity procedimientoSugerencia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProcedimientoEntity getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(ProcedimientoEntity procedimiento) {
        this.procedimiento = procedimiento;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public ProcedimientoEntity getProcedimientoSugerencia() {
        return procedimientoSugerencia;
    }

    public void setProcedimientoSugerencia(ProcedimientoEntity procedimientoSugerencia) {
        this.procedimientoSugerencia = procedimientoSugerencia;
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
        SugerenciaEntity other = (SugerenciaEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
