package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;

public class SugerenciaRepresentation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private int prioridad;
    private ProcedimientoRepresentation procedimiento;
    private ProcedimientoRepresentation procedimientoSugerencia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProcedimientoRepresentation getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(ProcedimientoRepresentation procedimiento) {
        this.procedimiento = procedimiento;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public ProcedimientoRepresentation getProcedimientoSugerencia() {
        return procedimientoSugerencia;
    }

    public void setProcedimientoSugerencia(ProcedimientoRepresentation procedimientoSugerencia) {
        this.procedimientoSugerencia = procedimientoSugerencia;
    }

}
