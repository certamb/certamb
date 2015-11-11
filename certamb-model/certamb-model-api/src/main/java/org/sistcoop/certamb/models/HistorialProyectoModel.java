package org.sistcoop.certamb.models;

import java.util.Date;

import org.sistcoop.certamb.models.enums.CategoriaProyecto;

public interface HistorialProyectoModel extends Model {

    String getId();

    Date getFecha();

    CategoriaProyecto getCategoria();

    void setCategoria(CategoriaProyecto categoria);

    String getResolucion();

    void setResolucion(String resolucion);

    String getObservacion();

    void setObservacion(String observacion);

    boolean getEstado();

    void setEstado(boolean estado);
    
    EstadoProcedimientoModel getEstadoProcedimiento();

}
