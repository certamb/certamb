package org.sistcoop.certam.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.certamb.representations.idm.DocumentoRepresentation;
import org.sistcoop.certamb.representations.idm.ProyectoRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchCriteriaRepresentation;
import org.sistcoop.certamb.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface DocumentosResource {

	/**
	 * @param idProyecto
	 *            El ID de Proyecto.
	 */
	@Path("{idDocumento}")
	public DocumentoResource documento(@PathParam("idDocumento") String idDocumento);

	/**
	 * Use este endpoint para crear un Proyecto en una DireccionRegional.
	 * 
	 * @summary Create Proyecto
	 * @param rep
	 *            La nueva Proyecto.
	 * @statuscode 200 Si el proyecto fue creada satisfactoriamente.
	 * @return Informacion acerca del proyecto creada.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(DocumentoRepresentation rep);

	/**
	 * Este endpoint lista todas Proyectos que pertenecen a una DireccionRegional.
	 * 
	 * @summary List all Proyecto
	 * @statuscode 200 Si la lista de proyectos fue retornada
	 *             satisfactoriamente.
	 * @return Una Lista de proyectos.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DocumentoRepresentation> getAll();	

	/**
	 * Este endpoint provee una forma de buscar proyectos. Los criterios de
	 * busqueda estan definidos por los parametros enviados.
	 * 
	 * @summary Search for Proyectos
	 * @param criteria
	 *            Criterio de busqueda.
	 * @statuscode 200 Si la busqueda fue realizada satisfactoriamente.
	 * @return Los resultados de la busqueda (una pagina de proyectos).
	 */
	@POST
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public SearchResultsRepresentation<DocumentoRepresentation> search(SearchCriteriaRepresentation criteria);
}
