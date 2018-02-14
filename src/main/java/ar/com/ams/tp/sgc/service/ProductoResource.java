/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ams.tp.sgc.service;

import ar.com.ams.tp.sgc.modelo.Producto;
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

@Path("producto")
@Stateless
public class ProductoResource {

    @Context
    private UriInfo context;

    
    @PersistenceContext(unitName = "TP_PU")
    EntityManager em;
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Producto getPorId(@PathParam("id") Integer id) {
        return em.find(Producto.class,id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> getLista() {
        return em.createQuery("SELECT prd FROM Producto prd").getResultList();
    }
    /**
     * PUT method for updating or creating an instance of ProductoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putProducto(Producto content) {
        em.merge(content);
        return Response.ok().build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProducto(Producto content) {
        em.persist(content);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response postProducto(@PathParam("id") Integer id) {
        em.remove(em.find(Producto.class, id));
        return Response.ok().build();
    }

}
