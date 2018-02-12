/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ams.tp.sgc.logica;

import ar.com.ams.tp.sgc.modelo.Cliente;
import java.util.List;

/**
 *
 * @author martdominguez
 */
public interface ClienteService {
    public Cliente crear(Cliente c);
    public Cliente actualizar(Cliente c);
    public Cliente buscarPorId(Integer id);
    public Cliente buscarPorMail(String mail);
    public List<Cliente> buscarTodos(String mail);
    public void borrar(Integer id);
    
    //metodo de negocio
    // calcula cuanto debe de cuenta corriente
    // esto es la diferencia entre las facturas a nombre del cliente y los recibos entregados.
    public Double deuda(Cliente c);
}
