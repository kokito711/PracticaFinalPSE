package com.kokito.turiscylgrupo1.LazyTable;

import com.kokito.turiscylgrupo1.entities.Evento;
import java.util.Comparator;
import java.util.Date;
import org.primefaces.model.SortOrder;

public class LazySorter implements Comparator<Evento> {

    private String sortField;

    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Evento evento1, Evento evento2) {
        try {
            Object value1;
            Object value2;
            switch (sortField) {
                case "id": {
                    value1 = evento1.getId();
                    value2 = evento2.getId();
                    break;
                }
                case "nombre": {
                    value1 = evento1.getNombre().toLowerCase();
                    value2 = evento2.getNombre().toLowerCase();
                    break;
                }
                case "horaInicio": {
                    value1 = evento1.getHoraInicio();
                    value2 = evento2.getHoraInicio();
                    break;
                }
                case "horaFin": {
                    value1 = evento1.getHoraFin();
                    value2 = evento2.getHoraFin();
                    break;
                }
                default:
                    value1=null;
                    value2 = null;
            }
            int value = ((Comparable) value1).compareTo(value2);
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
