package org.sistcoop.cooperativa.models.jpa.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "HISTORIAL_BOVEDA")
@NamedQueries(value = { 
		@NamedQuery(name = HistorialBovedaEntity.findByEstado, query = "SELECT s FROM HistorialBovedaEntity s WHERE s.boveda.id = :idBoveda AND s.estado = :estado") })
public class HistorialBovedaEntity extends HistorialEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String base = "org.softgreen.sistcoop.organizacion.ejb.models.jpa.entities.HistorialBovedaEntity.";
	public final static String findByEstado = base + "findByEstado";

	private BovedaEntity boveda;
	private Set<DetalleHistorialBovedaEntity> detalle = new HashSet<DetalleHistorialBovedaEntity>();

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	public BovedaEntity getBoveda() {
		return boveda;
	}

	public void setBoveda(BovedaEntity boveda) {
		this.boveda = boveda;
	}

	@XmlTransient
	@OneToMany(mappedBy = "historial", fetch = FetchType.LAZY)
	public Set<DetalleHistorialBovedaEntity> getDetalle() {
		return detalle;
	}

	public void setDetalle(Set<DetalleHistorialBovedaEntity> detalle) {
		this.detalle = detalle;
	}

}
