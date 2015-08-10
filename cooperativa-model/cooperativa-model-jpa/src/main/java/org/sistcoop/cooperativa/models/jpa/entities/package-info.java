/**
 * 
 * GenericGenerator 
 * */
@GenericGenerator(name = "SgGenericGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @Parameter(name = "prefer_sequence_per_entity", value = "true"),
        @Parameter(name = "optimizer ", value = "pooled") })
/**
 * 
 * NamedQueries 
 * */
@NamedQueries(value = {
        @NamedQuery(name = "Trabajador.getByTipoNumeroDocumento", query = "SELECT t FROM TrabajadorCajaEntity t WHERE t.tipoDocumento = :tipoDocumento AND t.numeroDocumento = :numeroDocumento AND t.estado = TRUE"),
        @NamedQuery(name = "Trabajador.getByIdCaja", query = "SELECT t FROM TrabajadorCajaEntity t WHERE t.caja.id = :idCaja"),

        @NamedQuery(name = "TransaccionBovedaCaja.getByIdHistorialBoveda", query = "SELECT t FROM TransaccionBovedaCajaEntity t WHERE t.historialBoveda.id = :idHistorialBoveda"),
        @NamedQuery(name = "TransaccionBovedaCaja.getByIdHistorialBovedaCaja", query = "SELECT t FROM TransaccionBovedaCajaEntity t WHERE t.historialBovedaCaja.id = :idHistorialBovedaCaja"),

        @NamedQuery(name = "TransaccionCajaCaja.getByIdHistorialBovedaCaja", query = "SELECT t FROM TransaccionCajaCajaEntity t WHERE t.historialBovedaCajaOrigen.id = :idHistorialBovedaCajaModel OR t.historialBovedaCajaDestino.id = :idHistorialBovedaCajaModel"),
        @NamedQuery(name = "TransaccionCajaCaja.getByIdHistorialBovedaCajaOrigen", query = "SELECT t FROM TransaccionCajaCajaEntity t WHERE t.historialBovedaCajaOrigen.id = :idHistorialBovedaCajaOrigen"),
        @NamedQuery(name = "TransaccionCajaCaja.getByIdHistorialBovedaCajaDestino", query = "SELECT t FROM TransaccionCajaCajaEntity t WHERE t.historialBovedaCajaDestino.id = :idHistorialBovedaCajaDestino") })
/**
 * @author carlosthe19916@gmail.com
 *
 */
package org.sistcoop.cooperativa.models.jpa.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.NamedQueries;