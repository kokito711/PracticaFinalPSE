/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kokito.turiscylgrupo1.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author coco7
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.kokito.TurisCyLGrupo1.json.EventoReader.class);
        resources.add(com.kokito.TurisCyLGrupo1.json.EventoWriter.class);
        resources.add(com.kokito.TurisCyLGrupo1.json.GruposReader.class);
        resources.add(com.kokito.TurisCyLGrupo1.json.GruposWriter.class);
        resources.add(com.kokito.TurisCyLGrupo1.json.UsuarioReader.class);
        resources.add(com.kokito.TurisCyLGrupo1.json.UsuarioWriter.class);
        resources.add(com.kokito.turiscylgrupo1.rest.EventoFacadeREST.class);
        resources.add(com.kokito.turiscylgrupo1.rest.GruposFacadeREST.class);
        resources.add(com.kokito.turiscylgrupo1.rest.UsuarioFacadeREST.class);
    }
    
}
