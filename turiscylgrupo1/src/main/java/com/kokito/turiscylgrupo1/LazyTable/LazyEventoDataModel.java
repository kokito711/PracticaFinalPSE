package com.kokito.turiscylgrupo1.LazyTable;

import com.kokito.turiscylgrupo1.entities.Evento;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyEventoDataModel extends LazyDataModel<Evento> {

    private List<Evento> datasource;

    public LazyEventoDataModel(List<Evento> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Evento getRowData(String rowKey) {
        for (Evento evento : datasource) {
            if (evento.getId().equals(rowKey)) {
                return evento;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Evento evento) {
        return evento.getId();
    }

    @Override
    public List<Evento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Evento> data = new ArrayList<Evento>();

        //filter
        for (Evento evento : datasource) {
            boolean match = true;

            if (filters != null) {

                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);

                        String fieldValue = "";

                        switch (filterProperty) {
                            case "id":
                                fieldValue = String.valueOf(evento.getId());
                                break;
                            case "nombre":
                                fieldValue = String.valueOf(evento.getNombre());
                                break;
                            case "horaInicio":
                                fieldValue = String.valueOf(evento.getHoraInicio());
                                break;
                            case "horaFin":
                                fieldValue = String.valueOf(evento.getHoraFin());
                                break;
                        }

                        //String fieldValue = String.valueOf(evento.getClass().getField(filterProperty).get(evento));
                        if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        match = false;
                    }
                }
            }

            if (match) {
                data.add(evento);
            }
        }

        //sort
        if (sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}
