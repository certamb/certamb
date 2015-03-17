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
@Table(name = "HISTORIAL_BOVEDA_CAJA")
@NamedQueries({
		@NamedQuery(name = HistorialBovedaCajaEntity.findByHistorialActivo, query = "SELECT h FROM HistorialBovedaCajaEntity h INNER JOIN h.bovedaCaja c WHERE c.id = :idCaja AND h.estado = true"),
		@NamedQuery(name = HistorialBovedaCajaEntity.findByHistorialDateRange, query = "SELECT h FROM HistorialBovedaCajaEntity h INNER JOIN h.bovedaCaja c WHERE c.id = :idCaja AND h.fechaApertura BETWEEN :desde AND :hasta AND h.estado = false ORDER BY h.horaApertura DESC"),
		@NamedQuery(name = HistorialBovedaCajaEntity.findByEstado, query = "SELECT s FROM HistorialBovedaCajaEntity s WHERE s.bovedaCaja.id = :idBovedaCaja AND s.estado = :estado") })
public class HistorialBovedaCajaEntity extends HistorialEntity implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String base = "org.softgreen.sistcoop.organizacion.ejb.models.jpa.entities.HistorialCajaEntity.";
	public final static String findByHistorialActivo = base
			+ "findByHistorialActivo";
	public final static String findByHistorialDateRange = base
			+ "findByHistorialDateRange";
	public final static String findByEstado = base + "findByEstado";

	private BovedaCajaEntity bovedaCaja;
	private Set<DetalleHistorialBovedaCajaEntity> detalle = new HashSet<DetalleHistorialBovedaCajaEntity>();

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	public BovedaCajaEntity getBovedaCaja() {
		return bovedaCaja;
	}

	public void setBovedaCaja(BovedaCajaEntity bovedaCaja) {
		this.bovedaCaja = bovedaCaja;
	}

	@XmlTransient
	@OneToMany(mappedBy = "historial", fetch = FetchType.LAZY)
	public Set<DetalleHistorialBovedaCajaEntity> getDetalle() {
		return detalle;
	}

	public void setDetalle(Set<DetalleHistorialBovedaCajaEntity> detalle) {
		this.detalle = detalle;
	}

}
