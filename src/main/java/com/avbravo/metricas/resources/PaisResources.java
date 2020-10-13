/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avbravo.metricas.resources;

import com.avbravo.jmoordb.util.JmoordbUtil;
import com.avbravo.metricas.entity.Pais;
import com.avbravo.metricas.repository.PaisRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.Timed;

/**
 *
 * @author avbravo
 */
@Path("pais")
@ApplicationScoped
public class PaisResources {

    @Inject
    PaisRepository paisRepository;

    @Inject
    @Metric

    @Counted(description = "contador de llamadas a hola", absolute = true)
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/hello")
    public Response hola() {
        return Response.ok("Hola desde Payara Micro!").build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    // ​@Counted(name = "countMe", absolute = true, reusable = true, tags={"tag1=value1"})
    @Timed(name = "getAllPais",
            description = "Monitor the time getAll Method takes",
            unit = MetricUnits.MILLISECONDS,
            absolute = true)
    public List<Pais> get() {
        return paisRepository.findAll();
    }

    @Metered(name = "create-pais",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor the rate events occured",
            absolute = true)
    @POST
    public Response create(Pais pais) {
        paisRepository.save(pais);
        return Response.ok().build();
    }

    @GET
    @Path("/get-int-value")
    @Gauge(unit = MetricUnits.NONE, name = "intValue", absolute = true)
    public int getIntValue() {
        return 3;
    }

    // <editor-fold defaultstate="collapsed" desc="@Path("/findall")">
    @GET
    @Path("/findall")
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "findAllPais",
            description = "Monitor the time find All Method takes",
            unit = MetricUnits.MILLISECONDS,
            absolute = true)
    public List<Pais> findAll() {
     
        return paisRepository.findAll();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="@Path("/update")">
    @PUT
    @Path("/update")
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Pais pais) {
        try {

            if (paisRepository.update(pais)) {
                return Response.status(201).entity("Updated").build();
            }

            return Response.status(400).entity("was not updated: " + paisRepository.getException().getLocalizedMessage()).build();
        } catch (Exception e) {

            return Response.status(400).entity("Error!!" + e.getLocalizedMessage()).build();

        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="@Path("/delete")">
    @DELETE
    @Path("/delete/{idboleta}")
    @RolesAllowed({"admin"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_XML)
    public Response delete(@PathParam("idboleta") Integer idboleta) {
        try {

            Document doc = new Document("idboleta", idboleta);
            if (paisRepository.delete(doc)) {
                return Response.status(201).entity("Ok").build();
            }
            return Response.status(400).entity("No se pudo eliminar" + paisRepository.getException().getLocalizedMessage()).build();
        } catch (Exception e) {

            return Response.status(400).entity("No se pudo eliminar").build();
        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" @Path("/jsonquery")">
    /**
     * Recive json para consulta y ordenaciones y paginacion y realiza la
     * busqueda
     *
     * @param query
     * @param sort
     * @param pageNumber
     * @param rowForPage
     * @return
     */
    @GET
    @Path("/jsonquery")
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Pais> jsonQuery(@QueryParam("query") String query, @QueryParam("sort") String sort,
            @QueryParam("pagenumber") Integer pageNumber, @QueryParam("rowforpage") Integer rowForPage) {
        List<Pais> suggestions = new ArrayList<>();
        try {

            Document docQuery = paisRepository.jsonToDocument(query);
            Document docSort = paisRepository.jsonToDocument(sort);

            suggestions = paisRepository.findPagination(docQuery, pageNumber, rowForPage, docSort);

        } catch (Exception e) {
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());

        }

        return suggestions;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="     @Path("/jsonquerywithoutpagination")">
    /**
     * Hace cosultas sin paginacion
     *
     * @param query
     * @param sort
     * @return
     */
    @GET
    @Path("/jsonquerywithoutpagination")
    @RolesAllowed({"admin"})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

    public List<Pais> jsonQueryWithoutPagination(@QueryParam("query") String query, @QueryParam("sort") String sort) {
        List<Pais> suggestions = new ArrayList<>();
        try {

            Document docQuery = paisRepository.jsonToDocument(query);
            Document docSort = paisRepository.jsonToDocument(sort);

            suggestions = paisRepository.findBy(docQuery, docSort);

        } catch (Exception e) {
            System.out.println(JmoordbUtil.nameOfMethod() + " " + e.getLocalizedMessage());

        }

        return suggestions;
    }

// </editor-fold>
}
