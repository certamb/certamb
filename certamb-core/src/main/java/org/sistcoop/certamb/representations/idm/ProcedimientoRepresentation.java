package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProcedimientoRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private int orden;
	private String denominacion;
	private String responsable;
	private int plazo;
	private String estado;
	private EtapaRepresentation etapa;

	private boolean requiereCategoria;
	private boolean requiereResolucion;
	private boolean requiereFechaVigencia;

	private List<SugerenciaRepresentation> sugerencias = new ArrayList<SugerenciaRepresentation>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public EtapaRepresentation getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaRepresentation etapa) {
		this.etapa = etapa;
	}

	public List<SugerenciaRepresentation> getSugerencias() {
		return sugerencias;
	}

	public void setSugerencias(List<SugerenciaRepresentation> sugerencias) {
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

}
