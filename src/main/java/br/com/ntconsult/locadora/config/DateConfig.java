package br.com.ntconsult.locadora.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static LocalDateSerializer LOCAL_DATE_SERIALIZER = new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT));

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LOCAL_DATE_SERIALIZER);
        return new ObjectMapper()
                .registerModule(module);
    }
}
