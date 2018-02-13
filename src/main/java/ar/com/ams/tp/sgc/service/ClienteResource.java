/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ams.tp.sgc.service;

import ar.com.ams.tp.sgc.modelo.Cliente;
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

@Path("cliente")
@Stateless
public class ClienteResource {

    @Context
    private UriInfo context;

    
    @PersistenceContext(unitName = "TP_PU")
    EntityManager em;
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Cliente getPorId(@PathParam("id") Integer id) {
        return em.find(Cliente.class,id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getLista() {
        return em.createQuery("SELECT cli FROM Cliente cli").getResultList();
    }
    /**
     * PUT method for updating or creating an instance of ClienteResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCliente(Cliente content) {
        em.merge(content);
        return Response.ok().build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postCliente(Cliente content) {
        em.persist(content);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response postCliente(@PathParam("id") Integer id) {
        em.remove(em.find(Cliente.class, id));
        return Response.ok().build();
    }

}
