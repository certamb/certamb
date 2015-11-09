package org.sistcoop.certamb.models;

import java.util.List;

import org.sistcoop.certamb.models.enums.CategoriaProyecto;

public interface HistorialProyectoModel extends Model {

    String getId();

    String getFecha();

    CategoriaProyecto getCategoria();

    void setCategoria(CategoriaProyecto categoria);

    String getResolucion();

    void setResolucion(String resolucion);

    String getObservacion();

    void setObservacion(String observacion);

    boolean getEstado();

    void setEstado(boolean estado);

    ProyectoModel getProyecto();

    EstadoProcedimientoModel getEstadoProcedimiento();

    List<DocumentoModel> getDocumentos();

}
