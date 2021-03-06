/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ams.tp.sgc.service;

import ar.com.ams.tp.sgc.modelo.Recibo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("recibo")
@Stateless
public class ReciboResource {

    @Context
    private UriInfo context;

    
    @PersistenceContext(unitName = "TP_PU")
    EntityManager em;
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Recibo getPorId(@PathParam("id") Integer id) {
        return em.find(Recibo.class,id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recibo> getLista() {
        return em.createQuery("SELECT rec FROM Recibo rec").getResultList();
    }
    /**
     * PUT method for updating or creating an instance of ReciboResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRecibo(Recibo content) {
        em.merge(content);
        return Response.ok().build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postRecibo(Recibo content) {
        em.persist(content);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response postRecibo(@PathParam("id") Integer id) {
        em.remove(em.find(Recibo.class, id));
        return Response.ok().build();
    }

}
