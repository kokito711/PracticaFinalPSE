package com.kokito.TurisCyLGrupo1.json;

import com.kokito.turiscylgrupo1.entities.Grupos;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)

public class GruposReader implements MessageBodyReader<Grupos> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Grupos.class.isAssignableFrom(type);
    }

    @Override
    public Grupos readFrom(Class<Grupos> type, Type genericType, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException, WebApplicationException {
        Grupos grupo = new Grupos();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "grupos":
                        {
                          grupo.setGrupos(parser.getString());
                        }
                        case "usuario":
                        {
                          grupo.setUsuario(parser.getString());
                        }
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return grupo;
    }
}
