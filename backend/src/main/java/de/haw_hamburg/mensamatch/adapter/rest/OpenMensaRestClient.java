package de.haw_hamburg.mensamatch.adapter.rest;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestClient;

import java.text.SimpleDateFormat;

@Configuration
public class OpenMensaRestClient {

    @Value("${rest.openmensa.base.url}")
    String baseUrl;

    @Bean
    public RestClient restClient(Jackson2ObjectMapperBuilder builder) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .messageConverters(httpMessageConverters -> {
                    httpMessageConverters.add(new MappingJackson2HttpMessageConverter(builder.build()));
                    httpMessageConverters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
                })
                .build();
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .modulesToInstall(new ParameterNamesModule());
    }
}
