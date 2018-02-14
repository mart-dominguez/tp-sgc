/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ams.tp.sgc.service;

import ar.com.ams.tp.sgc.modelo.Cliente;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author martdominguez
 */
@RunWith(Arquillian.class)
public class TestClienteRest {

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "ar.com.ams.tp.sgc.modelo")
                .addPackages(true, "ar.com.ams.tp.sgc.service")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @Test
    public void testClienteAPI() {
        Client clientHttp = ClientBuilder.newClient();
       
        Cliente cli1 = new Cliente();
        cli1.setCorreo("correo@mail.com");
        cli1.setNombre("PEPE");
        cli1.setMaximoCuentaCorriente(1500.0);

        WebTarget myResource = clientHttp.target("http://localhost:8080/test/api/cliente");
        Response r = myResource.request(MediaType.APPLICATION_JSON)
                .post(Entity.json(cli1));
        assertTrue(r.getStatus() >= 200);
        assertTrue(r.getStatus() < 400);

        Cliente cli2 = new Cliente();
        cli2.setCorreo("correo2@mail.com");
        cli2.setNombre("MARIA");
        cli2.setMaximoCuentaCorriente(100.0);
        
        r = myResource.request(MediaType.APPLICATION_JSON)
                .post(Entity.json(cli2));
        assertTrue(r.getStatus() >= 200);
        assertTrue(r.getStatus() < 400);
        
         Cliente cli3 = myResource.path("{id_cliente}")
                .resolveTemplate("id_cliente", "2")  
                .request().get(Cliente.class);
        
        assertEquals(cli3.getCorreo(), cli2.getCorreo());
        assertEquals(cli3.getMaximoCuentaCorriente(), cli2.getMaximoCuentaCorriente());

        cli3.setMaximoCuentaCorriente(9999.99);
        r = myResource.request(MediaType.APPLICATION_JSON)
                .put(Entity.json(cli3));
        assertTrue(r.getStatus() >= 200);
        assertTrue(r.getStatus() < 400);
        
        Cliente cli4 = myResource.path("{id_cliente}")
                .resolveTemplate("id_cliente", "2")  
                .request().get(Cliente.class);
        
        assertEquals(cli4.getCorreo(), cli3.getCorreo());
        assertEquals(cli4.getMaximoCuentaCorriente(), cli3.getMaximoCuentaCorriente());
        
        List<Cliente> lista=  myResource.request(MediaType.APPLICATION_JSON)
                        .get(new GenericType<List<Cliente>>() {});

        Integer tamanioEsperado = 2;
        Integer tamanioObtenido = lista.size();
        assertEquals(tamanioEsperado,tamanioObtenido);
    }
}
