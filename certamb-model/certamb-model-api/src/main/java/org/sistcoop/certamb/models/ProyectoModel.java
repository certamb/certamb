package org.sistcoop.certamb.models;

import java.math.BigDecimal;

import org.sistcoop.certamb.models.enums.TipoProyecto;

public interface ProyectoModel extends Model {

    String getId();

    String getDenominacion();

    void setDenominacion(String denominacion);

    BigDecimal getMonto();

    void setMonto(BigDecimal monto);

    TipoProyecto getTipo();

    void setTipo(TipoProyecto tipo);

}
