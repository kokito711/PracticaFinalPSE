/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kokito.turiscylgrupo1.booking;

import com.kokito.turiscylgrupo1.entities.Evento;
import java.io.Serializable;
import java.util.Date;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author coco7
 */
@Named
@FlowScoped("booking")
public class Booking implements Serializable {

    private int idEvento;
    private long idEntrada;
    private double precio = 25.00;
    private String tarjeta = "";
    private String CVV = "";
    private Date fecha_exp = new Date();
    private @PersistenceContext
    EntityManager em;

    public Booking() {
        double rand = Math.random() * 1000000000;
        String aux = Double.toString(rand);
        idEntrada = Long.parseLong(aux.substring(2, aux.length() - 2));
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public long getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(long idEntrada) {
        this.idEntrada = idEntrada;
    }

    public Date getFecha_exp() {
        return fecha_exp;
    }

    public void setFecha_exp(Date fecha_exp) {
        this.fecha_exp = fecha_exp;
    }

    public String getNombreEvento() {
        try {
            return em.createNamedQuery("Evento.findById", Evento.class).setParameter("id", idEvento)
                    .getSingleResult()
                    .getNombre();
        } catch (NoResultException e) {
            return "";
        }
    }

    public String getNombreEvento(int id) {
        idEvento = id;
        try {
            return em.createNamedQuery("Evento.findById", Evento.class).setParameter("id", idEvento)
                    .getSingleResult()
                    .getNombre();
        } catch (NoResultException e) {
            return "";
        }
    }

    public Date getFecha_inicioEvento() {
        try {
            return em.createNamedQuery("Evento.findById", Evento.class).setParameter("id", idEvento)
                    .getSingleResult()
                    .getHoraInicio();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Date getFecha_finEvento() {
        try {
            return em.createNamedQuery("Evento.findById", Evento.class).setParameter("id", idEvento)
                    .getSingleResult()
                    .getHoraFin();
        } catch (NoResultException e) {
            return null;
        }
    }

}
