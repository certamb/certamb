package org.sistcoop.cooperativa.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface TransaccionMayorCuantiaProvider extends Provider {

	TransaccionMayorCuantiaModel addTransaccionMayorCuantia(TransaccionClienteModel transaccionClienteModel, BigDecimal montoMaximo);

}