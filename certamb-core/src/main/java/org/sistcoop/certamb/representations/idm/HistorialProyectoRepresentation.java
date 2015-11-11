package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;
import java.util.Date;

public class HistorialProyectoRepresentation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private Date fecha;
    private String categoria;
    private String resolucion;
    private String observacion;
    private boolean estado;

    private EstadoProcedimientoRepresentation estadoProcedimiento;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
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

    public EstadoProcedimientoRepresentation getEstadoProcedimiento() {
        return estadoProcedimiento;
    }

    public void setEstadoProcedimiento(EstadoProcedimientoRepresentation estadoProcedimiento) {
        this.estadoProcedimiento = estadoProcedimiento;
    }

}
