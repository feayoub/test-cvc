package br.com.fayoub.scheduler.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String stringDate) {
        return LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}