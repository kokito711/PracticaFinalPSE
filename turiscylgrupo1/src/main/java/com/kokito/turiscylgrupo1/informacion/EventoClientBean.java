package com.kokito.TurisCyLGrupo1.informacion;

import com.kokito.turiscylgrupo1.entities.Evento;
import com.kokito.TurisCyLGrupo1.json.EventoReader;
import com.kokito.TurisCyLGrupo1.json.EventoWriter;
import java.util.Date;
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

@Named
@RequestScoped
public class EventoClientBean {

    Client client;
    WebTarget target;

    @Inject
    EventoBackingBean bean;

    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/turiscylgrupo1/webresources/com.kokito.pruebas.entities.evento");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public Evento[] getEventos() {
        return target.request().get(Evento[].class);
    }

    public Evento getEvento() {
        Evento m = target.register(EventoReader.class).path("{id}").resolveTemplate("id", bean.getId()).request(MediaType.APPLICATION_JSON).get(Evento.class);
        return m;
    }

    public void updateEvento() {
        Evento m = new Evento();
        m.setId(bean.getId());
        m.setNombre(bean.getNombre());
        m.setHoraInicio(bean.getFecha_inicio());
        m.setHoraFin(bean.getFecha_fin());
        m.setDescripcion(bean.getDescripcion());
        //target.register(UsuarioWriter.class).request().put(Entity.entity(m, MediaType.APPLICATION_JSON)); 
        target.path("{id}").resolveTemplate("id", bean.getId()).request().put(Entity.entity(m, MediaType.APPLICATION_JSON));
    }

    public void deleteEvento() {
        target.path("{id}").resolveTemplate("id", bean.getId()).request().delete();
    }

    public void addEvento() {
        Evento m = new Evento();
        m.setId(1);
        m.setNombre(bean.getNombre());
        m.setDescripcion(bean.getDescripcion());
        m.setHoraInicio(bean.getFecha_inicio());
        m.setHoraFin(bean.getFecha_fin());
        target.register(EventoWriter.class).request().post(Entity.entity(m, MediaType.APPLICATION_JSON));
    }
    public Date getCurrentDate(){
        return new Date();
    }
}
