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
import org.hibernate.annotations.Type;

@Entity
@Table(name = "BOVEDA_CAJA")
public class BovedaCajaEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private boolean estado;

	private CajaEntity caja;
	private BovedaEntity boveda;
	private Set<HistorialBovedaCajaEntity> historiales = new HashSet<HistorialBovedaCajaEntity>();

	private Timestamp optlk;

	public BovedaCajaEntity() {
	}

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	@Column(name = "ESTADO")
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	public CajaEntity getCaja() {
		return caja;
	}

	public void setCaja(CajaEntity caja) {
		this.caja = caja;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	public BovedaEntity getBoveda() {
		return boveda;
	}

	public void setBoveda(BovedaEntity boveda) {
		this.boveda = boveda;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bovedaCaja")
	public Set<HistorialBovedaCajaEntity> getHistoriales() {
		return historiales;
	}

	public void setHistoriales(Set<HistorialBovedaCajaEntity> historiales) {
		this.historiales = historiales;
	}

	@Version
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
