package com.kokito.turiscylgrupo1.informacion;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

@Named
@SessionScoped
public class EventoBackingBean implements Serializable {

    private Integer id;
    @Size(min = 3, max = 32)
    private String nombre;
    @Size(max = 65535)
    private String descripcion;
    private Date fecha_inicio;
    private Date fecha_fin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}
