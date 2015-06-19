package org.sistcoop.cooperativa.models;

import java.util.Date;
import java.util.List;

import org.sistcoop.cooperativa.models.enums.ORIGEN_TRANSACCION_BOVEDACAJA;

public interface TransaccionBovedaCajaModel extends Model {

	String getId();

	HistorialBovedaModel getHistorialBoveda();

	HistorialBovedaCajaModel getHistorialBovedaCaja();

	ORIGEN_TRANSACCION_BOVEDACAJA getOrigen();

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
