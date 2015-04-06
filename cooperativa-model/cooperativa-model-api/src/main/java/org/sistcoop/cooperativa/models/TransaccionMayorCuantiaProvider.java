package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionMayorCuantiaProvider extends Provider {

	TransaccionMayorCuantiaModel addTransaccionMayorCuantia();

}