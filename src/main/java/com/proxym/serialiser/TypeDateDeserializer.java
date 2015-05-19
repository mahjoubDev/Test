package com.proxym.serialiser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.proxym.enums.TypeDate;

/**
 * 
 * @author Nessrine.
 * @version 1.0
 *
 */
public class TypeDateDeserializer extends JsonDeserializer<TypeDate> {

	@Override
	public TypeDate deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		
		return TypeDate.fromString(parser.getText().toUpperCase());
	}

}
