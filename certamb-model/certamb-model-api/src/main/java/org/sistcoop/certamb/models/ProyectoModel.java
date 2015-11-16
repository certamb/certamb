package org.sistcoop.certamb.models;

import java.math.BigDecimal;
import java.util.Date;

import org.sistcoop.certamb.models.enums.EstadoProyecto;
import org.sistcoop.certamb.models.enums.TipoProyecto;

public interface ProyectoModel extends Model {

    String getId();

    String getDenominacion();

    void setDenominacion(String denominacion);

    BigDecimal getMonto();

    void setMonto(BigDecimal monto);

    TipoProyecto getTipo();

    void setTipo(TipoProyecto tipo);

    Date getFechaVigenciaDesde();

    void setFechaVigenciaDesde(Date fechaVigenciaDesde);

    Date getFechaVigenciaHasta();

    void setFechaVigenciaHasta(Date fechaVigenciaHasta);

    EstadoProyecto getEstado();

    void setEstado(EstadoProyecto estado);

    DireccionRegionalModel getDireccionRegional();

}
