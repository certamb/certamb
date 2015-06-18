package org.sistcoop.cooperativa.models;

import java.util.Date;
import java.util.List;

public interface TransaccionBovedaCajaModel extends Model {

	String getId();

	HistorialBovedaModel getHistorialBoveda();

	HistorialBovedaCajaModel getHistorialBovedaCaja();

	String getOrigen();

	List<DetalleTransaccionBovedaCajaModel> getDetalle();

	Date getFecha();

	Date getHora();

	boolean getEstado();

	void desactivar();

	String getObservacion();

	void setObservacion(String observacion);

	boolean getEstadoSolicitud();

	void setEstadoSolicitud(boolean estadoSolicitud);

	boolean getEstadoConfirmacion();

	void setEstadoConfirmacion(boolean estadoConfirmacion);

}
