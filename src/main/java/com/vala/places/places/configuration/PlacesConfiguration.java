package com.vala.places.places.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties("places")
public class PlacesConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}