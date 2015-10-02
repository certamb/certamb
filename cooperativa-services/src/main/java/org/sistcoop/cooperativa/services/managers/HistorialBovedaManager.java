package org.sistcoop.cooperativa.services.managers;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.cooperativa.models.HistorialBovedaModel;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistorialBovedaManager {

    public boolean cerrarHistorialBoveda(HistorialBovedaModel historialBoveda) {
        historialBoveda.setAbierto(false);
        historialBoveda.setEstadoMovimiento(false);
        historialBoveda.commit();

        historialBoveda.setFechaCierre(LocalDate.now());
        historialBoveda.setHoraCierre(LocalTime.now());
        historialBoveda.commit();

        return true;
    }

    public void congelar(HistorialBovedaModel historialBovedaModel) {
        historialBovedaModel.setEstadoMovimiento(false);
        historialBovedaModel.commit();
    }

    public void descongelar(HistorialBovedaModel historialBovedaModel) {
        historialBovedaModel.setEstadoMovimiento(true);
        historialBovedaModel.commit();
    }

}
