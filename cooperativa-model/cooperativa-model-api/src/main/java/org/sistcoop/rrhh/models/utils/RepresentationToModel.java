package org.sistcoop.rrhh.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.rrhh.models.AgenciaModel;
import org.sistcoop.rrhh.models.AgenciaProvider;
import org.sistcoop.rrhh.models.SucursalModel;
import org.sistcoop.rrhh.models.SucursalProvider;
import org.sistcoop.rrhh.models.TrabajadorModel;
import org.sistcoop.rrhh.models.TrabajadorProvider;
import org.sistcoop.rrhh.representations.idm.AgenciaRepresentation;
import org.sistcoop.rrhh.representations.idm.SucursalRepresentation;
import org.sistcoop.rrhh.representations.idm.TrabajadorRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

	public SucursalModel createSucursal(SucursalRepresentation rep, SucursalProvider provider) {
		SucursalModel model = provider.addSucursal(rep.getAbreviatura(), rep.getDenominacion());
		return model;
	}

	public AgenciaModel createAgencia(SucursalModel sucursalModel, AgenciaRepresentation rep, AgenciaProvider agenciaProvider) {
		AgenciaModel agenciaModel = agenciaProvider.addAgencia(sucursalModel, rep.getCodigo(), rep.getAbreviatura(), rep.getDenominacion(), rep.getUbigeo(), rep.getDireccion());
		return agenciaModel;
	}
	
	public TrabajadorModel createTrabajador(AgenciaModel agenciaModel, TrabajadorRepresentation rep, TrabajadorProvider trabajadorProvider) {
		TrabajadorModel trabajadorModel = trabajadorProvider.addTrabajador(agenciaModel, rep.getTipoDocumento(), rep.getNumeroDocumento());
		return trabajadorModel;
	}

}
