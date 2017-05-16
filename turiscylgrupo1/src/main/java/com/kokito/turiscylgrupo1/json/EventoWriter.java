package com.kokito.TurisCyLGrupo1.json;

import com.kokito.turiscylgrupo1.entities.Evento;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class EventoWriter implements MessageBodyWriter<Evento>{

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Evento.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Evento t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Evento t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(entityStream);
        gen.writeStartObject().write("id",t.getId()).write("name",t.getNombre())
                .write("hora_inicio",t.getHoraInicio().toString())
                .write("hora_fin",t.getHoraFin().toString())
                .write("descripcion",t.getDescripcion()).writeEnd();
        gen.flush();
    }    
}
