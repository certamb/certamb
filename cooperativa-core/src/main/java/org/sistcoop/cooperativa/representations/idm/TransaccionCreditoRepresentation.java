package org.sistcoop.cooperativa.representations.idm;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TransaccionCreditoRepresentation implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String id;
    private Long numeroOperacion;
    private LocalDate fecha;
    private LocalTime hora;
    private boolean estado;
    private String observacion;
    private HistorialBovedaCajaRepresentation historialBovedaCaja;
    private List<DetalleMonedaRepresentation> detalle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(Long numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public HistorialBovedaCajaRepresentation getHistorialBovedaCaja() {
        return historialBovedaCaja;
    }

    public void setHistorialBovedaCaja(HistorialBovedaCajaRepresentation historialBovedaCaja) {
        this.historialBovedaCaja = historialBovedaCaja;
    }

    public List<DetalleMonedaRepresentation> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleMonedaRepresentation> detalle) {
        this.detalle = detalle;
    }

}
