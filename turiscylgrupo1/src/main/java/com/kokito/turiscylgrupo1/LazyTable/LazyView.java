package com.kokito.turiscylgrupo1.LazyTable;

 
import com.kokito.turiscylgrupo1.entities.Evento;
import com.kokito.turiscylgrupo1.informacion.EventoClientBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;



@ManagedBean(name="dtLazyView")
@ViewScoped
public class LazyView implements Serializable{
    private LazyDataModel<Evento> lazyModel;
     
    private Evento selectedEvento;
     
    @ManagedProperty("#{eventoService}")
    private EventoService service;
     
    @PostConstruct
    public void init() {
        lazyModel = new LazyEventoDataModel(service.createEventos());
    }
 
    public LazyDataModel<Evento> getLazyModel() {
        return lazyModel;
    }
 
    public Evento getSelectedEvento() {
        return selectedEvento;
    } 
    public void setSelectedEvento(Evento selectedEvento) {
        this.selectedEvento = selectedEvento;
   }
     
    public void setService(EventoService service) {
        this.service = service;
    }
     
    public void onRowSelect() {
        EventoClientBean eventoClientBean = new EventoClientBean();
        eventoClientBean.getEvento();
    }

}
