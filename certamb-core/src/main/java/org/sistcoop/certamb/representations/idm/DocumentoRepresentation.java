package org.sistcoop.certamb.representations.idm;

import java.io.Serializable;

public class DocumentoRepresentation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String url;
    private HistorialProyectoRepresentation historial;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HistorialProyectoRepresentation getHistorial() {
        return historial;
    }

    public void setHistorial(HistorialProyectoRepresentation historial) {
        this.historial = historial;
    }

}
