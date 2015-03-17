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

@XmlRootElement(name = "trabajador")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TrabajadorRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String tipoDocumento;
	private String numeroDocumento;
	private String usuario;
	private Boolean estado;

	private AgenciaRepresentation agencia;
	private AreaRepresentation area;
	private CargoRepresentation cargo;

	@XmlAttribute
	@Min(value = 1)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute
	@Size(min = 1, max = 20)
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@XmlAttribute
	@Size(min = 1, max = 20)
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	@XmlAttribute
	@Size(min = 1, max = 40)
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	public AgenciaRepresentation getAgencia() {
		return agencia;
	}

	public void setAgencia(AgenciaRepresentation agencia) {
		this.agencia = agencia;
	}

	@XmlElement
	public AreaRepresentation getArea() {
		return area;
	}

	public void setArea(AreaRepresentation area) {
		this.area = area;
	}

	@XmlElement
	public CargoRepresentation getCargo() {
		return cargo;
	}

	public void setCargo(CargoRepresentation cargo) {
		this.cargo = cargo;
	}

}