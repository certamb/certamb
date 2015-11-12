package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;

public class SugerenciaRepresentation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private ProcedimientoRepresentation procedimiento;

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

}
