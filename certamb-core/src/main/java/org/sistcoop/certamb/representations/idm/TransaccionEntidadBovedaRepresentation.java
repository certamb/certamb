package org.sistcoop.certamb.representations.idm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TransaccionEntidadBovedaRepresentation implements java.io.Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String id;
    private LocalDate fecha;
    private LocalTime hora;
    private String observacion;
    private boolean estado;

    private String origen;
    private String tipo;

    private HistorialBovedaRepresentation historialBoveda;
    private EntidadRepresentation entidad;

    private List<DetalleMonedaRepresentation> detalle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public HistorialBovedaRepresentation getHistorialBoveda() {
        return historialBoveda;
    }

    public void setHistorialBoveda(HistorialBovedaRepresentation historialBoveda) {
        this.historialBoveda = historialBoveda;
    }

    public EntidadRepresentation getEntidad() {
        return entidad;
    }

    public void setEntidad(EntidadRepresentation entidad) {
        this.entidad = entidad;
    }

    public List<DetalleMonedaRepresentation> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleMonedaRepresentation> detalle) {
        this.detalle = detalle;
    }

}
