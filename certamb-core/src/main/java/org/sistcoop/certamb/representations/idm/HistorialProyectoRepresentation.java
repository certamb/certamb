package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private Date fechaVigenciaDesde;
    private Date fechaVigenciaHasta;

    private String responsableTipoDocumento;
    private String responsableNumeroDocumento;

    private ProyectoRepresentation proyecto;
    private ProcedimientoRepresentation procedimiento;
    private Set<DocumentoRepresentation> documentos = new HashSet<DocumentoRepresentation>();

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

    public ProyectoRepresentation getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoRepresentation proyecto) {
        this.proyecto = proyecto;
    }

    public ProcedimientoRepresentation getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(ProcedimientoRepresentation procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Set<DocumentoRepresentation> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<DocumentoRepresentation> documentos) {
        this.documentos = documentos;
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

    public String getResponsableTipoDocumento() {
        return responsableTipoDocumento;
    }

    public void setResponsableTipoDocumento(String responsableTipoDocumento) {
        this.responsableTipoDocumento = responsableTipoDocumento;
    }

    public String getResponsableNumeroDocumento() {
        return responsableNumeroDocumento;
    }

    public void setResponsableNumeroDocumento(String responsableNumeroDocumento) {
        this.responsableNumeroDocumento = responsableNumeroDocumento;
    }

}
