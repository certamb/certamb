package org.sistcoop.rrhh.models.jpa.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "AGENCIA", indexes = { @Index(columnList = "id") })
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@NamedQueries(value = {
		@NamedQuery(name = AgenciaEntity.findByCodigo, query = "SELECT a FROM AgenciaEntity a WHERE a.codigo = :codigo"),
		@NamedQuery(name = AgenciaEntity.findByEstado, query = "SELECT a FROM AgenciaEntity a WHERE a.estado = :estado"),
		@NamedQuery(name = AgenciaEntity.findByFilterText, query = "SELECT a FROM AgenciaEntity a WHERE a.codigo LIKE :filterText OR (UPPER(a.denominacion) LIKE :filterText OR a.abreviatura LIKE :filterText) AND a.estado = TRUE"),
		@NamedQuery(name = AgenciaEntity.findBySucursalAndFilterText, query = "SELECT a FROM AgenciaEntity a WHERE a.sucursal.id = :idSucursal AND ( a.codigo LIKE :filterText OR (UPPER(a.denominacion) LIKE :filterText OR a.abreviatura LIKE :filterText) ) AND a.estado = TRUE")})
public class AgenciaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String base = "org.softgreen.sistcoop.organizacion.ejb.models.jpa.entities.Agencia.";
	public final static String findByCodigo = base + "findByCodigo";
	public final static String findByEstado = base + "findByEstado";
	public static final String findByFilterText = base + "findByFilterText";//por defecto solo busca activos
	public static final String findBySucursalAndFilterText = base + "findBySucursalAndFilterText";//por defecto solo busca activos	

	private Integer id;
	private String codigo;
	private String denominacion;
	private String abreviatura;
	private String direccion;
	private String ubigeo;
	private boolean estado;

	private SucursalEntity sucursal;

	private Set<TrabajadorEntity> trabajadores = new HashSet<TrabajadorEntity>();

	private Timestamp optlk;

	public AgenciaEntity() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(generator = "SgGenericGenerator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull
	@Size(min = 2, max = 2)
	@NotBlank
	@NotEmpty
	@NaturalId
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@NotNull
	@Size(min = 1, max = 60)
	@NotBlank
	@NotEmpty
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@NotNull
	@Size(min = 1, max = 30)
	@NotBlank
	@NotEmpty
	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	
	@NotNull
	@Size(min = 1, max = 150)
	@NotBlank
	@NotEmpty
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	@NotNull
	@Size(min = 6, max = 6)
	@NotBlank
	@NotEmpty
	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey)
	public SucursalEntity getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalEntity sucursal) {
		this.sucursal = sucursal;
	}

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agencia")
	public Set<TrabajadorEntity> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(Set<TrabajadorEntity> trabajadores) {
		this.trabajadores = trabajadores;
	}

	@XmlTransient
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
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AgenciaEntity))
			return false;
		AgenciaEntity other = (AgenciaEntity) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
}
