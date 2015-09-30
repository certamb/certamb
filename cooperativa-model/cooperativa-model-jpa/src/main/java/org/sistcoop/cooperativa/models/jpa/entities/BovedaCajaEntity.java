package org.sistcoop.cooperativa.models.jpa.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "BOVEDA_CAJA")
@NamedQueries(value = {
        @NamedQuery(name = "BovedaCajaEntity.findByIdBoveda", query = "SELECT bc FROM BovedaCajaEntity bc INNER JOIN bc.boveda b WHERE b.id = :idBoveda"),
        @NamedQuery(name = "BovedaCajaEntity.findByIdCaja", query = "SELECT bc FROM BovedaCajaEntity bc INNER JOIN bc.caja c WHERE c.id = :idCaja"),
        @NamedQuery(name = "BovedaCajaEntity.findByIdBovedaIdCaja", query = "SELECT bc FROM BovedaCajaEntity bc INNER JOIN bc.boveda b INNER JOIN bc.caja c WHERE b.id = :idBoveda AND c.id = :idCaja") })
public class BovedaCajaEntity implements java.io.Serializable {

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
    @Type(type = "org.hibernate.type.TrueFalseType")
    @Column(name = "ESTADO")
    private boolean estado;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "CAJA_ID")
    private CajaEntity caja;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey, name = "BOVEDA_ID")
    private BovedaEntity boveda;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bovedaCaja")
    private Set<HistorialBovedaCajaEntity> historiales = new HashSet<HistorialBovedaCajaEntity>();

    @Version
    private Timestamp optlk;

    public BovedaCajaEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public CajaEntity getCaja() {
        return caja;
    }

    public void setCaja(CajaEntity caja) {
        this.caja = caja;
    }

    public BovedaEntity getBoveda() {
        return boveda;
    }

    public void setBoveda(BovedaEntity boveda) {
        this.boveda = boveda;
    }

    public Set<HistorialBovedaCajaEntity> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(Set<HistorialBovedaCajaEntity> historiales) {
        this.historiales = historiales;
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
        result = prime * result + ((boveda == null) ? 0 : boveda.hashCode());
        result = prime * result + ((caja == null) ? 0 : caja.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BovedaCajaEntity))
            return false;
        BovedaCajaEntity other = (BovedaCajaEntity) obj;
        if (boveda == null) {
            if (other.boveda != null)
                return false;
        } else if (!boveda.equals(other.boveda))
            return false;
        if (caja == null) {
            if (other.caja != null)
                return false;
        } else if (!caja.equals(other.caja))
            return false;
        return true;
    }

}
