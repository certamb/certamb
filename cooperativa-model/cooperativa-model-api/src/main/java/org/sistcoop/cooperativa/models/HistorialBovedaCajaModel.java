package org.sistcoop.cooperativa.models;

import java.util.Date;
import java.util.List;

public interface HistorialBovedaCajaModel extends Model {

	Long getId();

	Date getFechaApertura();

	Date getFechaCierre();

	void setFechaCierre(Date fechaCierre);

	Date getHoraApertura();

	Date getHoraCierre();

	void setHoraCierre(Date horaCierre);

	boolean getEstado();

	void desactivar();

	BovedaCajaModel getBovedaCaja();

	List<DetalleHistorialBovedaCajaModel> getDetalle();
}
