package org.sistcoop.certamb.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface TransaccionMayorCuantiaProvider extends Provider {

    TransaccionMayorCuantiaModel create(TransaccionClienteModel transaccionClienteModel,
            BigDecimal montoMaximo);

}