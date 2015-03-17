package org.sistcoop.rrhh.models;

import javax.ejb.Local;

import org.sistcoop.rrhh.provider.Provider;

@Local
public interface TrabajadorProvider extends Provider {

	TrabajadorModel addTrabajador(
			AgenciaModel agenciaModel, 
			String tipoDocumento, 
			String numeroDocumento);

	boolean removeTrabajador(TrabajadorModel trabajadorModel);

	TrabajadorModel getTrabajadorByUsuario(String usuario);

	TrabajadorModel getTrabajadorById(Integer id);

	TrabajadorModel getTrabajadorByTipoNumeroDocumento(String tipoDocumento, String numeroDocumento);

}
