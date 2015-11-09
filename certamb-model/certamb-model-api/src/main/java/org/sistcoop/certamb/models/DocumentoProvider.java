package org.sistcoop.certamb.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.certamb.provider.Provider;

@Local
public interface DocumentoProvider extends Provider {

    DocumentoModel create(HistorialProyectoModel historial, String url);

    DocumentoModel findById(String id);

    List<DocumentoModel> getAll(HistorialProyectoModel historial);

}