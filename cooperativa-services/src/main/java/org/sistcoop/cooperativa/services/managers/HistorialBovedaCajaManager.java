package org.sistcoop.cooperativa.services.managers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.DetalleHistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistorialBovedaCajaManager {

	public boolean cerrarHistorialBovedaCaja(HistorialBovedaCajaModel historialBovedaCajaModel, List<DetalleMonedaRepresentation> detalle) {
		Calendar calendar = Calendar.getInstance();

		historialBovedaCajaModel.setAbierto(false);
		historialBovedaCajaModel.setEstadoMovimiento(false);
		historialBovedaCajaModel.commit();

		historialBovedaCajaModel.setFechaCierre(calendar.getTime());
		historialBovedaCajaModel.setHoraCierre(calendar.getTime());
		historialBovedaCajaModel.commit();
		
		List<DetalleHistorialBovedaCajaModel> detalleHistorialBovedaCajaModels = historialBovedaCajaModel.getDetalle();
		for (DetalleHistorialBovedaCajaModel detalleHistorialBovedaCajaModel : detalleHistorialBovedaCajaModels) {
			BigDecimal valorDetalleHistorialBovedaCaja = detalleHistorialBovedaCajaModel.getValor();
			for (DetalleMonedaRepresentation detalleMonedaRepresentation : detalle) {
				BigDecimal valorDetalleMonedaRepresentation = detalleMonedaRepresentation.getValor();
				if(valorDetalleHistorialBovedaCaja.compareTo(valorDetalleMonedaRepresentation) == 0) {
					detalleHistorialBovedaCajaModel.setCantidad(detalleMonedaRepresentation.getCantidad());
					detalleHistorialBovedaCajaModel.commit();
					break;
				}
			}
		}
		
		return true;
	}

	public void congelar(HistorialBovedaCajaModel historialBovedaCajaModel) {
		historialBovedaCajaModel.setEstadoMovimiento(false);
		historialBovedaCajaModel.commit();
	}

	public void descongelar(HistorialBovedaCajaModel historialBovedaCajaModel) {
		historialBovedaCajaModel.setEstadoMovimiento(true);
		historialBovedaCajaModel.commit();
	}

}
