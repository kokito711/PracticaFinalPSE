package com.kokito.turiscylgrupo1.LazyTable;
 
import com.kokito.turiscylgrupo1.entities.Evento;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

public class LazySorter implements Comparator<Evento> {
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    public int compare(Evento evento1, Evento evento2) {
        try {
            Object value1 = Evento.class.getField(this.sortField).get(evento1);
            Object value2 = Evento.class.getField(this.sortField).get(evento2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
