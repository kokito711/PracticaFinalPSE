package com.kokito.TurisCyLGrupo1.json;

import com.kokito.turiscylgrupo1.entities.Evento;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class EventoReader implements MessageBodyReader<Evento> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Evento.class.isAssignableFrom(type);
    }

    @Override
    public Evento readFrom(Class<Evento> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Evento evento = new Evento();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "id":
                            evento.setId(parser.getInt());
                            break;
                        case "name":
                            evento.setNombre(parser.getString());
                            break;
                        case "hora_inicio":
                        {
                            DateFormat format = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                                            Locale.ENGLISH);
                            Date date = null;
                            try {
                                date = format.parse(parser.getString());
                            } catch (ParseException ex) {
                                LocalDate localDate = LocalDate.now();
                                try {
                                    date = format.parse(DateTimeFormatter.ofPattern("EE MMM dd HH:mm:ss z yyyy",
                                            Locale.ENGLISH).format(localDate));
                                } catch (ParseException ex1) {
                                    Logger.getLogger(EventoReader.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                            evento.setHoraInicio(date);
                            break;
                        }
                        case "hora_fin":
                        {
                           DateFormat format = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                                            Locale.ENGLISH);
                            Date date = null;
                            try {
                                date = format.parse(parser.getString());
                            } catch (ParseException ex) {
                                LocalDate localDate = LocalDate.now();
                                try {
                                    date = format.parse(DateTimeFormatter.ofPattern("EE MMM dd HH:mm:ss z yyyy",
                                            Locale.ENGLISH).format(localDate));
                                } catch (ParseException ex1) {
                                    Logger.getLogger(EventoReader.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                            evento.setHoraFin(date);
                            break;
                        }
                        case "descripcion":
                            evento.setDescripcion(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return evento;
    }
}
