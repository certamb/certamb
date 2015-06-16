package org.sistcoop.cooperativa.models.jpa.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CAJA", indexes = { @Index(columnList = "id") })
@NamedQueries({		
		@NamedQuery(name = CajaEntity.findByAgencia, query = "SELECT c FROM CajaEntity c WHERE c.agencia = :agencia"),
		@NamedQuery(name = CajaEntity.findByAgenciaAndFilterText, query = "SELECT c FROM CajaEntity c WHERE c.agencia = :agencia AND c.denominacion LIKE :filterText") })
public class CajaEntity {

	public static final String base = "org.sistcoop.cooperativa.models.jpa.entities.CajaEntity.";
	public static final String findByAgencia = base + "findByAgencia";
	public static final String findByAgenciaAndFilterText = base + "findByAgenciaAndFilterText";

	private Integer id;
	private String denominacion;
	private boolean estado;
	private boolean abierto;
	private boolean estadoMovimiento;

	private String agencia;

	private Set<BovedaCajaEntity> bovedaCajas = new HashSet<BovedaCajaEntity>();
	private Set<TrabajadorCajaEntity> trabajadorCajas = new HashSet<TrabajadorCajaEntity>();

	private Timestamp optlk;

	@Id
	@GeneratedValue(generator = "SgGenericGenerator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull
	@Size(min = 1, max = 20)
	@NotBlank
	@NotEmpty
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}

	@NotNull
	@Type(type = "org.hibernate.type.TrueFalseType")
	public boolean isEstadoMovimiento() {
		return estadoMovimiento;
	}

	public void setEstadoMovimiento(boolean estadoMovimiento) {
		this.estadoMovimiento = estadoMovimiento;
	}

	@NotNull
	@NotBlank
	@Size(min = 1, max = 200)
	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caja")
	public Set<BovedaCajaEntity> getBovedaCajas() {
		return bovedaCajas;
	}

	public void setBovedaCajas(Set<BovedaCajaEntity> bovedaCajas) {
		this.bovedaCajas = bovedaCajas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caja")
	public Set<TrabajadorCajaEntity> getTrabajadorCajas() {
		return trabajadorCajas;
	}

	public void setTrabajadorCajas(Set<TrabajadorCajaEntity> trabajadorCajas) {
		this.trabajadorCajas = trabajadorCajas;
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
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((denominacion == null) ? 0 : denominacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CajaEntity))
			return false;
		CajaEntity other = (CajaEntity) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (denominacion == null) {
			if (other.denominacion != null)
				return false;
		} else if (!denominacion.equals(other.denominacion))
			return false;
		return true;
	}

}
