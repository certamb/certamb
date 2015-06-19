package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel(value = "trabajador", description = "Trabajador")
public class TrabajadorCajaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String tipoDocumento;
	private String numeroDocumento;
	private boolean estado;

	private CajaRepresentation caja;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public CajaRepresentation getCaja() {
		return caja;
	}

	public void setCaja(CajaRepresentation caja) {
		this.caja = caja;
	}

}
