package org.sistcoop.certamb.models.jpa.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "PROCEDIMIENTO")
@NamedQueries(value = {
		@NamedQuery(name = "ProcedimientoEntity.findAll", query = "SELECT e FROM ProcedimientoEntity e"),
		@NamedQuery(name = "ProcedimientoEntity.findFirst", query = "SELECT e FROM ProcedimientoEntity e INNER JOIN e.etapa et WHERE et.orden=(SELECT MIN(aux.orden) FROM EtapaEntity aux) AND e.orden=(SELECT MIN(aux.orden) FROM ProcedimientoEntity aux)"),
		@NamedQuery(name = "ProcedimientoEntity.findByIdEtapa", query = "SELECT e FROM ProcedimientoEntity e INNER JOIN e.etapa et WHERE et.id =:idEtapa") })
public class ProcedimientoEntity implements Serializable {

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
	@Column(name = "ORDEN")
	private int orden;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 200)
	@Column(name = "DENOMINACION")
	private String denominacion;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "RESPONSABLE")
	private String responsable;

	@NotNull
	@Min(value = 1)
	@Column(name = "PLAZO")
	private int plazo;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "ESTADO")
	private String estado;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ETAPA_ID", foreignKey = @ForeignKey )
	private EtapaEntity etapa;

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	@Column(name = "REQUIERE_CATEGORIA")
	private boolean requiereCategoria;

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	@Column(name = "REQUIERE_RESOLUCION")
	private boolean requiereResolucion;

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	@Column(name = "REQUIERE_FECHA_VIGENCIA")
	private boolean requiereFechaVigencia;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "procedimiento")
	private Set<SugerenciaEntity> sugerencias = new HashSet<SugerenciaEntity>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EtapaEntity getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaEntity etapa) {
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

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Set<SugerenciaEntity> getSugerencias() {
		return sugerencias;
	}

	public void setSugerencias(Set<SugerenciaEntity> sugerencias) {
		this.sugerencias = sugerencias;
	}

	public boolean isRequiereCategoria() {
		return requiereCategoria;
	}

	public void setRequiereCategoria(boolean requiereCategoria) {
		this.requiereCategoria = requiereCategoria;
	}

	public boolean isRequiereResolucion() {
		return requiereResolucion;
	}

	public void setRequiereResolucion(boolean requiereResolucion) {
		this.requiereResolucion = requiereResolucion;
	}

	public boolean isRequiereFechaVigencia() {
		return requiereFechaVigencia;
	}

	public void setRequiereFechaVigencia(boolean requiereFechaVigencia) {
		this.requiereFechaVigencia = requiereFechaVigencia;
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
		ProcedimientoEntity other = (ProcedimientoEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
