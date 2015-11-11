package org.sistcoop.certamb.models.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.NotBlank;
import org.sistcoop.certamb.models.enums.ResponsableEstadoProcedimiento;

@Entity
@Table(name = "ESTADO_PROCEDIMIENTO")
@NamedQueries(value = {
        @NamedQuery(name = "EstadoProcedimientoEntity.findAll", query = "SELECT e FROM EstadoProcedimientoEntity e"),
        @NamedQuery(name = "EstadoProcedimientoEntity.findFirst", query = "SELECT e FROM EstadoProcedimientoEntity e INNER JOIN e.etapa et WHERE et.orden=(SELECT MIN(aux.orden) FROM EtapaProcedimientoEntity aux) AND e.orden=(SELECT MIN(aux.orden) FROM EstadoProcedimientoEntity aux)"),
        @NamedQuery(name = "EstadoProcedimientoEntity.findByIdEtapaProcedimiento", query = "SELECT e FROM EstadoProcedimientoEntity e INNER JOIN e.etapa et WHERE et.id =:idEtapaProcedimiento") })
public class EstadoProcedimientoEntity implements Serializable {

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ETAPA_PROCEDIMIENTO_ID", foreignKey = @ForeignKey )
    private EtapaProcedimientoEntity etapa;

    @NotNull
    @Min(0)
    @Column(name = "ORDEN")
    private int orden;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 200)
    @Column(name = "DENOMINACION")
    private String denominacion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "RESPONSABLE")
    private ResponsableEstadoProcedimiento responsable;

    @NotNull
    @Min(value = 1)
    @Column(name = "PLAZO")
    private int plazo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EtapaProcedimientoEntity getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaProcedimientoEntity etapa) {
        this.etapa = etapa;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public ResponsableEstadoProcedimiento getResponsable() {
        return responsable;
    }

    public void setResponsable(ResponsableEstadoProcedimiento responsable) {
        this.responsable = responsable;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
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
        EstadoProcedimientoEntity other = (EstadoProcedimientoEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
