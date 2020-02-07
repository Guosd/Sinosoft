package com.sinosoft.nimg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Guosd
 */
@SpringBootApplication
public class NimgApplication {
    public static void main(String[] args) {
        SpringApplication.run(NimgApplication.class, args);
    }
}
