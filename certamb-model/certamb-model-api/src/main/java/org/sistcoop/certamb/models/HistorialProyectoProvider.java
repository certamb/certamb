package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.models.enums.CategoriaProyecto;
import org.sistcoop.certamb.models.search.SearchCriteriaModel;
import org.sistcoop.certamb.models.search.SearchResultsModel;
import org.sistcoop.certamb.provider.Provider;

@Local
public interface HistorialProyectoProvider extends Provider {

    HistorialProyectoModel create(ProyectoModel proyecto, EstadoProcedimientoModel estadoProcedimiento,
            CategoriaProyecto categoria, String resolucion, String observacion);

    HistorialProyectoModel findById(String id);
    
    HistorialProyectoModel findByHistorialActivo(ProyectoModel proyecto);

    List<HistorialProyectoModel> getAll(ProyectoModel proyecto);

    SearchResultsModel<HistorialProyectoModel> search(ProyectoModel proyecto, SearchCriteriaModel criteria);

}