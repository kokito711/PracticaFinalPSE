package com.kokito.turiscylgrupo1.json;

import com.kokito.turiscylgrupo1.entities.Usuario;
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
public class UsuarioWriter implements MessageBodyWriter<Usuario>{

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Usuario.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Usuario t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Usuario t, Class<?> type, Type genericType, Annotation[] annotations, 
            MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, 
            OutputStream entityStream) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(entityStream);
        gen.writeStartObject().write("id",t.getId()).write("nombre",t.getNombre())
                .write("apellidos",t.getApellidos())
                .write("edad",t.getEdad())
                .write("password",t.getPassword())
                .write("provincia", t.getProvincia())
                .write("usuario", t.getUsuario())
                .writeEnd();
        gen.flush();
    }    
}
