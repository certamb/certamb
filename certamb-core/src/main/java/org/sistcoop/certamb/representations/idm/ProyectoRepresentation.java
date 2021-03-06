package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProyectoRepresentation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String denominacion;
    private BigDecimal monto;
    private String tipo;
    private String estado;

    private Date fechaVigenciaDesde;
    private Date fechaVigenciaHasta;

    private DireccionRegionalRepresentation direccionRegional;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DireccionRegionalRepresentation getDireccionRegional() {
        return direccionRegional;
    }

    public void setDireccionRegional(DireccionRegionalRepresentation direccionRegional) {
        this.direccionRegional = direccionRegional;
    }

    public Date getFechaVigenciaDesde() {
        return fechaVigenciaDesde;
    }

    public void setFechaVigenciaDesde(Date fechaVigenciaDesde) {
        this.fechaVigenciaDesde = fechaVigenciaDesde;
    }

    public Date getFechaVigenciaHasta() {
        return fechaVigenciaHasta;
    }

    public void setFechaVigenciaHasta(Date fechaVigenciaHasta) {
        this.fechaVigenciaHasta = fechaVigenciaHasta;
    }

}
