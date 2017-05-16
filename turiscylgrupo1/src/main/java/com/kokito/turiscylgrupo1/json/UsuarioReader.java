package com.kokito.TurisCyLGrupo1.json;

import com.kokito.turiscylgrupo1.entities.Usuario;
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

public class UsuarioReader implements MessageBodyReader<Usuario> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Usuario.class.isAssignableFrom(type);
    }

    @Override
    public Usuario readFrom(Class<Usuario> type, Type genericType, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException, WebApplicationException {
        Usuario usuario = new Usuario();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "id":
                            usuario.setId(parser.getInt());
                            break;
                        case "nombre":
                            usuario.setNombre(parser.getString());
                            break;
                        case "apellidos":
                            usuario.setApellidos(parser.getString());
                            break;
                        case "edad":
                            usuario.setEdad(parser.getInt());
                            break;
                        case "password":
                            usuario.setPassword(parser.getString());
                            break;
                        case "provincia":
                            usuario.setProvincia(parser.getString());
                            break;
                        case "usuario":
                            usuario.setUsuario(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return usuario;
    }
}
