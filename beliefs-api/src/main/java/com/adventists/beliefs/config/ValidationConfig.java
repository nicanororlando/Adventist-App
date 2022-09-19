package com.adventists.beliefs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

  /* Esto se va a ejecutar antes de llamar a la base de datos, y va a validar que
   * los campos no tengan valores nulos. Si tienen valor nulo va a generar una excepcion.
   */
  @Bean
  ValidatingMongoEventListener validatingMongoEventListener() {
    return new ValidatingMongoEventListener(validatorFactoryBean());
  }

  @Bean
  LocalValidatorFactoryBean validatorFactoryBean() {
    return new LocalValidatorFactoryBean();
  }
}
