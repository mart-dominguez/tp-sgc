/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ams.tp.sgc.logica;

import ar.com.ams.tp.sgc.modelo.Cliente;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestDao {

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "ar.com.ams.tp.sgc.modelo")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @PersistenceContext(unitName = "TP_PU")
    EntityManager em;

    @Inject
    UserTransaction utx;

    @Test
    public void testCrearCliente() throws Exception {
        Cliente cli = new Cliente();
        cli.setCorreo("mdo@mail.com");
        cli.setNombre("Martin");
        cli.setMaximoCuentaCorriente(1000.0);
        utx.begin();
        em.joinTransaction();
        em.persist(cli);
        em.flush();
        em.refresh(cli);
        utx.commit();
        assertNotNull(cli.getId());
    }
}
