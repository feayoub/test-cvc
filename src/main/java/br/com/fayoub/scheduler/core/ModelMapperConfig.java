package br.com.fayoub.scheduler.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A bean for a ModelMapper
 * Very useful to map objects between classes
 * For more information see {@link ModelMapper}
 */
@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
