package br.com.fayoub.scheduler.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Class responsible to convert a java.time.LocalDate to a String in a determined pattern.
 * For more information see {@link Converter}
 * <p>
 * This class was necessary to correctly transfer information between the controller and the view
 */
@Component
public class LocalDateToStringConverter implements Converter<LocalDate, String> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public String convert(LocalDate localDate) {
        return formatter.format(localDate);
    }
}
