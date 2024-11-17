package com.wolfott.mangement.administration.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;

@Converter(autoApply = true)
public class JsonConverter implements AttributeConverter<Object, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error serializing object to JSON", e);
        }
    }

    @Override
    public Object convertToEntityAttribute(String str) {
        try {
            return objectMapper.readValue(str, Object.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error deserializing JSON to object", e);
        }
    }
}
