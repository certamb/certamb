package org.sistcoop.cooperativa.models.utils;

import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaModel;
import org.sistcoop.cooperativa.models.CajaModel;
import org.sistcoop.cooperativa.models.DetalleTransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaCajaModel;
import org.sistcoop.cooperativa.models.HistorialBovedaModel;
import org.sistcoop.cooperativa.models.TrabajadorCajaModel;
import org.sistcoop.cooperativa.models.TransaccionBovedaCajaModel;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.CajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.DetalleMonedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.TrabajadorRepresentation;
import org.sistcoop.cooperativa.representations.idm.TransaccionBovedaCajaRepresentation;

public class ModelToRepresentation {

	public static BovedaRepresentation toRepresentation(BovedaModel model) {
		
		if(model == null)
			return null;
		
		BovedaRepresentation rep = new BovedaRepresentation();
		
		rep.setId(model.getId());
		rep.setAgencia(model.getAgencia());
		rep.setDenominacion(model.getDenominacion());
		rep.setMoneda(model.getMoneda());		
		rep.setEstado(model.getEstado());
		
		return rep;
	}	

	public static CajaRepresentation toRepresentation(CajaModel model) {
		
		if(model == null)
			return null;
		
		CajaRepresentation rep = new CajaRepresentation();
		rep.setId(model.getId());
		rep.setDenominacion(model.getDenominacion());
		rep.setAgencia(model.getAgencia());		
		rep.setEstado(model.getEstado());
		
		return rep;
	}

	public static HistorialBovedaRepresentation toRepresentation(
			HistorialBovedaModel model) {
		
		if(model == null)
			return null;
		
		HistorialBovedaRepresentation rep = new HistorialBovedaRepresentation();
		rep.setId(model.getId());
		rep.setFechaApertura(model.getFechaApertura());
		rep.setHoraApertura(model.getHoraApertura());
		rep.setFechaCierre(model.getFechaCierre());
		rep.setHoraCierre(model.getHoraCierre());
		rep.setEstado(model.getEstado());
		
		return rep;
	}
	
	public static HistorialBovedaCajaRepresentation toRepresentation(
			HistorialBovedaCajaModel model) {
		
		if(model == null)
			return null;
		
		HistorialBovedaCajaRepresentation rep = new HistorialBovedaCajaRepresentation();
		rep.setId(model.getId());
		rep.setFechaApertura(model.getFechaApertura());
		rep.setHoraApertura(model.getHoraApertura());
		rep.setFechaCierre(model.getFechaCierre());
		rep.setHoraCierre(model.getHoraCierre());
		rep.setEstado(model.getEstado());
		
		BovedaCajaModel bovedaCajaModel = model.getBovedaCaja();
		BovedaModel  bovedaModel = bovedaCajaModel.getBoveda();
		BovedaRepresentation bovedaRepresentation = new BovedaRepresentation();
		bovedaRepresentation.setId(bovedaModel.getId());
		bovedaRepresentation.setAgencia(bovedaModel.getAgencia());
		bovedaRepresentation.setDenominacion(bovedaModel.getDenominacion());
		bovedaRepresentation.setMoneda(bovedaModel.getMoneda());
		rep.setBoveda(bovedaRepresentation);
		
		return rep;
	}

	public static BovedaCajaRepresentation toRepresentation(BovedaCajaModel model) {
		
		if(model == null)
			return null;
		
		BovedaCajaRepresentation rep = new BovedaCajaRepresentation();
		rep.setId(model.getId());
		rep.setSaldo(model.getSaldo());
		rep.setEstado(model.getEstado());
	
		BovedaModel bovedaModel = model.getBoveda();
		BovedaRepresentation bovedaRepresentation = new BovedaRepresentation();
		bovedaRepresentation.setId(bovedaModel.getId());
		bovedaRepresentation.setAgencia(bovedaModel.getAgencia());
		bovedaRepresentation.setDenominacion(bovedaModel.getDenominacion());
		bovedaRepresentation.setMoneda(bovedaModel.getMoneda());
		rep.setBoveda(bovedaRepresentation);
		
		CajaModel cajaModel = model.getCaja();
		CajaRepresentation cajaRepresentation = new CajaRepresentation();
		cajaRepresentation.setId(cajaModel.getId());
		cajaRepresentation.setDenominacion(cajaModel.getDenominacion());
		cajaRepresentation.setAgencia(cajaModel.getAgencia());		
		cajaRepresentation.setEstado(cajaModel.getEstado());
		rep.setCaja(cajaRepresentation);
		
		return rep;
	}

	public static TrabajadorRepresentation toRepresentation(TrabajadorCajaModel model) {
		
		if(model == null)
			return null;
		
		TrabajadorRepresentation rep = new TrabajadorRepresentation();
		rep.setId(model.getId());
		rep.setTipoDocumento(model.getTipoDocumento());
		rep.setNumeroDocumento(model.getNumeroDocumento());
		rep.setEstado(model.getEstado());
		
		CajaModel cajaModel = model.getCaja();
		CajaRepresentation cajaRepresentation = new CajaRepresentation();
		cajaRepresentation.setId(cajaModel.getId());
		cajaRepresentation.setDenominacion(cajaModel.getDenominacion());
		cajaRepresentation.setAgencia(cajaModel.getAgencia());		
		cajaRepresentation.setEstado(cajaModel.getEstado());
		rep.setCaja(cajaRepresentation);
		
		return rep;
	}

	public static TransaccionBovedaCajaRepresentation toRepresentation(
			TransaccionBovedaCajaModel transaccionBovedaCajaModel) {
		// TODO Auto-generated method stub
		return null;
	}

	public static DetalleMonedaRepresentation toRepresentation(
			DetalleTransaccionBovedaCajaModel detalleTransaccionBovedaCajaModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
