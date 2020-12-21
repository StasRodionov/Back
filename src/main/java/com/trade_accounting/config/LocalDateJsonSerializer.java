package com.trade_accounting.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateJsonSerializer extends JsonSerializer<LocalDate> {
    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeNumberField("year", value.getYear());
        gen.writeNumberField("month", value.getMonthValue());
        gen.writeNumberField("day", value.getDayOfMonth());

        gen.writeEndObject();
    }

    @Override
    public Class<LocalDate> handledType() {
        return LocalDate.class;
    }
}
