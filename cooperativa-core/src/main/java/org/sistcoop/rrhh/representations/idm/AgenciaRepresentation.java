package org.sistcoop.rrhh.representations.idm;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "agencia")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AgenciaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String codigo;
	private String abreviatura;
	private String denominacion;
	private String direccion;
	private String ubigeo;
	private Boolean estado;

	private SucursalRepresentation sucursal;

	@XmlAttribute
	@Min(value = 1)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute
	@Size(min = 2, max = 2)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@XmlAttribute
	@Size(min = 1, max = 30)
	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@XmlAttribute
	@Size(min = 1, max = 60)
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@XmlAttribute
	@Size(min = 1, max = 150)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@XmlAttribute
	@Size(min = 6, max = 6)
	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	@XmlAttribute
	@AssertTrue
	public Boolean isEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@XmlElement
	public SucursalRepresentation getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalRepresentation sucursal) {
		this.sucursal = sucursal;
	}

}
