package de.haw_hamburg.mensamatch.adapter.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Configuration
@EnableScheduling
public class ApiConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .build();
    }

    @Bean
    public MappingJackson2HttpMessageConverter converter(ObjectMapper objectMapper) {
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    @Component
    @WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
    public class RequestCachingFilter extends OncePerRequestFilter {

        private final static Logger LOGGER = LoggerFactory.getLogger(ApiConfiguration.class);

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {
            LOGGER.info("REQUEST DATA: " + request.getRequestURI());
            filterChain.doFilter(request, response);
        }
    }
}
