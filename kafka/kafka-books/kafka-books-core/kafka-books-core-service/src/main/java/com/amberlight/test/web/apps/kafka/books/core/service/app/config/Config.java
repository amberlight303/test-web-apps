package com.amberlight.test.web.apps.kafka.books.core.service.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;

import java.util.Set;

@Configuration
public class Config {

    @Bean
    @Primary
    public ConversionService applicationConversionService(
            @Autowired(required = false) Set<Converter<?, ?>> converters) {
        ConfigurableConversionService conversionService = new ApplicationConversionService();
        if (converters != null) {
            for (Converter<?, ?> converter : converters) {
                conversionService.addConverter(converter);
            }
        }
        return conversionService;
    }

}
