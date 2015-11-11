package org.sistcoop.certam.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.certamb.representations.idm.EtapaProcedimientoRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Path("etapasProcedimiento")
@Consumes(MediaType.APPLICATION_JSON)
public interface EtapasProcedimientoResource {

    /**
     * @param idDireccionRegional
     *            El ID de DireccionRegional.
     */
    @Path("{idEtapaProcedimiento}")
    public EtapaProcedimientoResource direccionRegional(
            @PathParam("idEtapaProcedimiento") String idEtapaProcedimiento);

    /**
     * Este endpoint lista todas las direccionesRegionales que existen en el
     * sistema.
     * 
     * @summary List all direccionesRegionales
     * @statuscode 200 Si la lista de direccionesRegionales fue retornada
     *             satisfactoriamente.
     * @return Una Lista de direccionesRegionales.
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<EtapaProcedimientoRepresentation> getAll();

}
