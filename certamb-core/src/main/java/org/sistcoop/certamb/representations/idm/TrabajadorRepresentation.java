package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;

public class TrabajadorRepresentation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String tipoDocumento;
    private String numeroDocumento;
    private String usuario;
    private DireccionRegionalRepresentation direccionRegional;

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public DireccionRegionalRepresentation getDireccionRegional() {
        return direccionRegional;
    }

    public void setDireccionRegional(DireccionRegionalRepresentation direccionRegional) {
        this.direccionRegional = direccionRegional;
    }

}
