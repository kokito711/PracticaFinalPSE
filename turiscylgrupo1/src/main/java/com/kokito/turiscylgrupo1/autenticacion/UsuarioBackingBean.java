/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kokito.turiscylgrupo1.autenticacion;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author coco7
 */
@Named
@SessionScoped
public class UsuarioBackingBean implements Serializable{

    private int id;

    @Size(min = 3, max = 32)
    private String usuario;
    
    @Size(min = 8)
    private String password;

    @Size(max = 20)
    private String nombre;
    
    @Size(max = 30)
    private String apellidos;
    
    //@Min(18)
    @Max(99)
    private Integer edad;
    
    @Size(max = 30)
    private String provincia;
    
    private boolean isOrganizador;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public boolean isIsOrganizador() {
        return isOrganizador;
    }

    public void setIsOrganizador(boolean isOrganizador) {
        this.isOrganizador = isOrganizador;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
