package com.away1994.lang.io.seriablize.serializer;

import com.away1994.lang.symbols.Symbol;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class PassDeseriablizer extends JsonDeserializer<Symbol> {
    @Override
    public Symbol deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return null;
    }
}
