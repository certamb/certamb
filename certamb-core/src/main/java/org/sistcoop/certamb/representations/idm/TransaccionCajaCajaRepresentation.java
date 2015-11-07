package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TransaccionCajaCajaRepresentation implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String id;

    private LocalDate fecha;
    private LocalTime hora;
    private String observacion;
    private boolean estadoSolicitud;
    private boolean estadoConfirmacion;

    private HistorialBovedaCajaRepresentation historialBovedaCajaOrigen;
    private HistorialBovedaCajaRepresentation historialBovedaCajaDestino;
    private BigDecimal monto;
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

    public boolean isEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(boolean estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public boolean isEstadoConfirmacion() {
        return estadoConfirmacion;
    }

    public void setEstadoConfirmacion(boolean estadoConfirmacion) {
        this.estadoConfirmacion = estadoConfirmacion;
    }

    public HistorialBovedaCajaRepresentation getHistorialBovedaCajaOrigen() {
        return historialBovedaCajaOrigen;
    }

    public void setHistorialBovedaCajaOrigen(HistorialBovedaCajaRepresentation historialBovedaCajaOrigen) {
        this.historialBovedaCajaOrigen = historialBovedaCajaOrigen;
    }

    public HistorialBovedaCajaRepresentation getHistorialBovedaCajaDestino() {
        return historialBovedaCajaDestino;
    }

    public void setHistorialBovedaCajaDestino(HistorialBovedaCajaRepresentation historialBovedaCajaDestino) {
        this.historialBovedaCajaDestino = historialBovedaCajaDestino;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public List<DetalleMonedaRepresentation> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleMonedaRepresentation> detalle) {
        this.detalle = detalle;
    }

}
