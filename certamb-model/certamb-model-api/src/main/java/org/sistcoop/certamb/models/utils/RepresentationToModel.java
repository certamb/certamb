package org.sistcoop.certamb.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.ProcedimientoModel;
import org.sistcoop.certamb.models.ProcedimientoProvider;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.HistorialProyectoProvider;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.ProyectoProvider;
import org.sistcoop.certamb.models.TrabajadorModel;
import org.sistcoop.certamb.models.TrabajadorProvider;
import org.sistcoop.certamb.models.enums.CategoriaProyecto;
import org.sistcoop.certamb.models.enums.TipoProyecto;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.TrabajadorRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

    public DireccionRegionalModel createDireccionRegional(DireccionRegionalRepresentation rep,
            DireccionRegionalProvider provider) {
        return provider.create(rep.getDenominacion());
    }

    public TrabajadorModel createTrabajador(TrabajadorRepresentation rep,
            DireccionRegionalModel direccionRegional, TrabajadorProvider provider) {
        return provider.create(direccionRegional, rep.getTipoDocumento(), rep.getNumeroDocumento());
    }

    public ProyectoModel createProyecto(ProyectoRepresentation rep, DireccionRegionalModel direccionRegional,
            ProyectoProvider proyectoProvider, HistorialProyectoProvider historialProvider,
            ProcedimientoProvider estadoProcedimientoProvider) {
        ProcedimientoModel procedimientoModel = estadoProcedimientoProvider.findFirst();

        ProyectoModel proyectoModel = proyectoProvider.create(direccionRegional, rep.getDenominacion(),
                TipoProyecto.valueOf(rep.getTipo()), rep.getMonto());
        historialProvider.create(proyectoModel, procedimientoModel, null, null,
                "Historial creado por el sistema");

        return proyectoModel;
    }

    public HistorialProyectoModel createHistorialProyecto(HistorialProyectoRepresentation rep,
            ProyectoModel proyecto, ProcedimientoModel procedimiento, HistorialProyectoProvider provider) {
        HistorialProyectoModel historialActivo = provider.findByHistorialActivo(proyecto);
        historialActivo.desactivar();
        historialActivo.commit();

        HistorialProyectoModel historialProyectoModel = provider.create(proyecto, procedimiento,
                rep.getCategoria() != null ? CategoriaProyecto.valueOf(rep.getCategoria()) : null,
                rep.getResolucion(), rep.getObservacion());

        // Verificar fin de procedimiento
        ProcedimientoModel procedimientoModel = historialProyectoModel.getProdedimiento();
        proyecto.setEstado(procedimientoModel.getEstado());
        proyecto.commit();

        return historialProyectoModel;
    }

    /*
     * public EntidadModel createEntidad(EntidadRepresentation representation,
     * EntidadProvider provider) { return
     * provider.create(representation.getDenominacion(),
     * representation.getAbreviatura()); }
     */

}
