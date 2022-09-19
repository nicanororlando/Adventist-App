package com.adventist.adventistclient.config;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsGlobalConfiguration implements WebFluxConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry corsRegistry) {
    corsRegistry
      .addMapping("/**")
      .allowedOrigins("http://localhost:8000")
      .allowedMethods("GET", "POST");
  }
}
// @Bean
// CorsWebFilter corsWebFilter() {
//     CorsConfiguration corsConfig = new CorsConfiguration();
//     corsConfig.setAllowedOrigins(Arrays.asList("http://allowed-origin.com"));
//     corsConfig.setMaxAge(8000L);
//     corsConfig.addAllowedMethod("PUT");
//     corsConfig.addAllowedHeader("Baeldung-Allowed");
//     UrlBasedCorsConfigurationSource source =
//       new UrlBasedCorsConfigurationSource();
//     source.registerCorsConfiguration("/**", corsConfig);
//     return new CorsWebFilter(source);
// }
