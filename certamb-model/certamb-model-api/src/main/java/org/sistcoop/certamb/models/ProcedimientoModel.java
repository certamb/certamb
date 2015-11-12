package org.sistcoop.certamb.models;

import org.sistcoop.certamb.models.enums.EstadoProyecto;
import org.sistcoop.certamb.models.enums.Responsable;

public interface ProcedimientoModel extends Model {

    String getId();

    String getDenominacion();

    void setDenominacion(String denominacion);

    int getOrden();

    void setOrden(int orden);

    Responsable getResponsable();

    void setResponsable(Responsable responsable);

    int getPlazo();

    void setPlazo(int plazo);

    EstadoProyecto getEstado();

    boolean getRequiereCategoria();
    
    boolean getRequiereResolucion();
    
    EtapaModel getEtapa();

}
