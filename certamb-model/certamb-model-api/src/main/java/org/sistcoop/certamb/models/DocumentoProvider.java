package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface DocumentoProvider extends Provider {

    DocumentoModel create(HistorialProyectoModel historial);

    DireccionRegionalModel findById(String id);

    List<DireccionRegionalModel> getAll(HistorialProyectoModel historial);

}