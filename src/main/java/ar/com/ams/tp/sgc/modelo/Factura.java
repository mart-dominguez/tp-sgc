/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.ams.tp.sgc.modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author martdominguez
 */

@Entity
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="ID_CLIENTE")
    private Cliente cliente;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToMany(mappedBy = "factura")
    private List<FacturaDetalle> detalle;
    @OneToMany(mappedBy = "factura")
    private List<Recibo> pagos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<FacturaDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<FacturaDetalle> detalle) {
        this.detalle = detalle;
    }

    public List<Recibo> getPagos() {
        return pagos;
    }

    public void setPagos(List<Recibo> pagos) {
        this.pagos = pagos;
    }
    
    
}
