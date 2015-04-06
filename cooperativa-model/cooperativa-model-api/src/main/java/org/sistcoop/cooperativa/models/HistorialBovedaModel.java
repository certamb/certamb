package org.sistcoop.cooperativa.models;

import java.util.Date;
import java.util.List;

public interface HistorialBovedaModel extends Model {

	Long getId();

	Date getFechaApertura();

	Date getFechaCierre();

	void setFechaCierre(Date fechaCierre);

	Date getHoraApertura();

	Date getHoraCierre();

	void setHoraCierre(Date horaCierre);

	boolean getEstado();

	void desactivar();

	BovedaModel getBoveda();

	List<DetalleHistorialBovedaModel> getDetalle();
}
