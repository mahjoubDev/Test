package com.proxym.serialiser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Class used for date serialisation .
 * 
 * @author Nessrine.
 * @version 1.0.
 * 
 */


public class DateDeserializer extends JsonDeserializer<Date> {

private SimpleDateFormat dateFormat = new SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");

@Override
public Date deserialize(JsonParser paramJsonParser,
        DeserializationContext paramDeserializationContext)
        throws IOException, JsonProcessingException {
    String str = paramJsonParser.getText().trim();
    try {
        return dateFormat.parse(str);
    } catch (ParseException e) {

    }
    return paramDeserializationContext.parseDate(str);
}
}
