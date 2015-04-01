package org.sistcoop.cooperativa.models.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACCION_MAYOR_CUANTIA")
@PrimaryKeyJoinColumn
public class TransaccionMayorCuantiaEntity extends TransaccionClienteEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
