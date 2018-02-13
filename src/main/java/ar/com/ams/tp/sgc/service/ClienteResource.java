/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ams.tp.sgc.service;

import ar.com.ams.tp.sgc.modelo.Cliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author martdominguez
 */
@Path("cliente")
@Stateless
public class ClienteResource {

    @Context
    private UriInfo context;

    
    @PersistenceContext(unitName = "TP_PU")
    EntityManager em;
    /**
     * Creates a new instance of ClienteResource
     */
    public ClienteResource() {
    }

    /**
     * Retrieves representation of an instance of ar.com.ams.tp.sgc.logica.ClienteResource
     * @return an instance of ar.com.ams.tp.sgc.modelo.Cliente
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Cliente getJson(@PathParam("id") Integer id) {
        return em.find(Cliente.class,id);
    }

    /**
     * PUT method for updating or creating an instance of ClienteResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Cliente content) {
        em.merge(content);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postJson(Cliente content) {
        em.persist(content);
    }

}
