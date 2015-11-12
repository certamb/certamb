package org.sistcoop.certam.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.certamb.representations.idm.ProcedimientoRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface ProcedimientosResource {

    /**
     * @param idProyecto
     *            El ID de Proyecto.
     */
    @Path("{idProcedimiento}")
    public ProcedimientoResource procedimiento(@PathParam("idProcedimiento") String idProcedimiento);

    /**
     * Este endpoint lista todas Proyectos que pertenecen a una
     * DireccionRegional.
     * 
     * @summary List all Proyecto
     * @statuscode 200 Si la lista de proyectos fue retornada
     *             satisfactoriamente.
     * @return Una Lista de proyectos.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProcedimientoRepresentation> getAll();

}
