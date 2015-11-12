package org.sistcoop.certamb.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.certamb.models.DireccionRegionalModel;
import org.sistcoop.certamb.models.DireccionRegionalProvider;
import org.sistcoop.certamb.models.EstadoProcedimientoModel;
import org.sistcoop.certamb.models.EstadoProcedimientoProvider;
import org.sistcoop.certamb.models.HistorialProyectoModel;
import org.sistcoop.certamb.models.HistorialProyectoProvider;
import org.sistcoop.certamb.models.ProyectoModel;
import org.sistcoop.certamb.models.ProyectoProvider;
import org.sistcoop.certamb.models.enums.CategoriaProyecto;
import org.sistcoop.certamb.models.enums.TipoProyecto;
import org.sistcoop.certamb.representations.idm.DireccionRegionalRepresentation;
import org.sistcoop.certamb.representations.idm.HistorialProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

    public DireccionRegionalModel createDireccionRegional(DireccionRegionalRepresentation rep,
            DireccionRegionalProvider provider) {
        return provider.create(rep.getDenominacion());
    }

    public ProyectoModel createProyecto(ProyectoRepresentation rep, DireccionRegionalModel direccionRegional,
            ProyectoProvider proyectoProvider, HistorialProyectoProvider historialProvider,
            EstadoProcedimientoProvider estadoProcedimientoProvider) {
        EstadoProcedimientoModel estadoProcedimiento = estadoProcedimientoProvider.findFirst();

        ProyectoModel proyectoModel = proyectoProvider.create(direccionRegional, rep.getDenominacion(),
                TipoProyecto.valueOf(rep.getTipo()), rep.getMonto());
        historialProvider.create(proyectoModel, estadoProcedimiento, null, null,
                "Historial creado por el sistema");

        return proyectoModel;
    }

    public HistorialProyectoModel createHistorialProyecto(HistorialProyectoRepresentation rep,
            ProyectoModel proyecto, EstadoProcedimientoModel estadoProcedimiento,
            HistorialProyectoProvider provider) {
        HistorialProyectoModel historialActivo = provider.findByHistorialActivo(proyecto);
        historialActivo.desactivar();
        historialActivo.commit();

        return provider.create(proyecto, estadoProcedimiento, CategoriaProyecto.valueOf(rep.getCategoria()),
                rep.getResolucion(), rep.getObservacion());
    }

    /*
     * public EntidadModel createEntidad(EntidadRepresentation representation,
     * EntidadProvider provider) { return
     * provider.create(representation.getDenominacion(),
     * representation.getAbreviatura()); }
     */

}
