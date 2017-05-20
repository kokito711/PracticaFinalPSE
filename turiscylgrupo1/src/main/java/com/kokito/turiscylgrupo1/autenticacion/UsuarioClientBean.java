/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kokito.turiscylgrupo1.autenticacion;

import com.kokito.turiscylgrupo1.entities.Grupos;
import com.kokito.turiscylgrupo1.entities.Usuario;
import com.kokito.turiscylgrupo1.json.GruposWriter;
import com.kokito.turiscylgrupo1.json.UsuarioReader;
import com.kokito.turiscylgrupo1.json.UsuarioWriter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author coco7
 */
@Named
@RequestScoped
public class UsuarioClientBean {

    Client client;
    WebTarget target;
    private static MessageDigest digester;

    @Inject
    UsuarioBackingBean bean;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/turiscylgrupo1/webresources/com.kokito.turiscylgrupo1.entities.usuario");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Usuario[] getUsuarios() {
        return target.request().get(Usuario[].class);
    }

    public Usuario getUsuario() {
        Usuario m = target.register(UsuarioReader.class).path("{usuarioId}").resolveTemplate("usuarioId", bean.getId()).request(MediaType.APPLICATION_JSON).get(Usuario.class);
        return m;
    }

    public void updateUsuario() {
        Usuario m = new Usuario();
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
        m.setId(bean.getId());
        m.setUsuario(bean.getUsuario());
        m.setPassword(crypt(bean.getPassword()));
        m.setNombre(bean.getNombre());
        m.setApellidos(bean.getApellidos());
        m.setEdad(bean.getEdad());
        m.setProvincia(bean.getProvincia());
        //target.register(UsuarioWriter.class).request().put(Entity.entity(m, MediaType.APPLICATION_JSON)); 
        target.path("{id}").resolveTemplate("id", bean.getId()).request().put(Entity.entity(m, MediaType.APPLICATION_JSON));
        clean();
    }

    public void deleteUsuario() {
        //target auxiliar para eliminar en grupos para hacer login
        WebTarget target_aux = client.target("http://localhost:8080/turiscylgrupo1/webresources/com.kokito.turiscylgrupo1.entities.grupos");
        target.path("{id}").resolveTemplate("id", bean.getId()).request().delete();
        target_aux.path("{usuario}").resolveTemplate("usuario", bean.getUsuario()).request().delete();
    }

    public void addUsuario() throws NoSuchAlgorithmException {
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
        Usuario m = new Usuario();
        //target auxiliar para añadir en grupos para hacer login
        WebTarget target_aux = client.target("http://localhost:8080/turiscylgrupo1/webresources/com.kokito.turiscylgrupo1.entities.grupos");
        m.setId(1);
        m.setUsuario(bean.getUsuario());
        m.setPassword(crypt(bean.getPassword()));
        m.setNombre(bean.getNombre());
        m.setApellidos(bean.getApellidos());
        if (bean.getEdad() != null) {
            m.setEdad(bean.getEdad());
        }
        else{
            m.setEdad(0);
        }
        m.setProvincia(bean.getProvincia());
        target.register(UsuarioWriter.class).request().post(Entity.entity(m, MediaType.APPLICATION_JSON));
        Grupos g = new Grupos();
        if (bean.isIsOrganizador()) {
            g.setGrupos("organizador");
        } else {
            g.setGrupos("turista");
        }
        g.setUsuario(bean.getUsuario());
        target_aux.register(GruposWriter.class).request().post(Entity.entity(g, MediaType.APPLICATION_JSON));
    }
    
    //método que limpia el bean
    public void clean(){
        bean.setNombre(null);
        bean.setApellidos(null);
        bean.setEdad(null);
        bean.setProvincia(null);
        bean.setPassword(null);
    }
    //Métodos para encriptar contraseña en MD5   

    private String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }
}
