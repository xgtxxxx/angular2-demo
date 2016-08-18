package com.b2s.scrumlr.odoo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil(){};

    private static final ObjectMapper OBJECT_MAPPER;

    static{
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static <T> T fromJson(final String json, final Class<T> clazz) {
        try {
            final T t = OBJECT_MAPPER.readValue(json, clazz);
            return t;
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return null;
    }

    public static String toJson(final Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return "";
    }

    public static TypeFactory getTypeFactory(){
        return OBJECT_MAPPER.getTypeFactory();
    }

    public static <T> T fromJson(final String json, final JavaType type){
        try {
            final T t = OBJECT_MAPPER.readValue(json, type);
            return t;
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return null;
    }
}
