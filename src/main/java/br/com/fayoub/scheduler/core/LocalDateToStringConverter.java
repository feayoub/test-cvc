package br.com.fayoub.scheduler.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateToStringConverter implements Converter<LocalDate, String> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public String convert(LocalDate localDate) {
        return formatter.format(localDate);
    }
}
