package com.kokito.turiscylgrupo1.LazyTable;

import com.kokito.turiscylgrupo1.entities.Evento;
import com.kokito.turiscylgrupo1.rest.EventoFacadeREST;
import com.kokito.turiscylgrupo1.entities.Evento;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.NoResultException;


@ManagedBean(name = "eventoService")
@ApplicationScoped
public class EventoService {

    @EJB
    private EventoFacadeREST eventoFacadeREST;
       
    public List<Evento> createEventos() {
        try {
            List<Evento> list = eventoFacadeREST.findAll();
            if (list.isEmpty()) {
                return null;
            }
            return list;
        } catch (NoResultException e) {
            return null;
        }
        
    }
}
