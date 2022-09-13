package com.adventist.adventistclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.adventist.adventistclient.dto.Belief.Belief;
import com.adventist.adventistclient.service.BeliefRestClientService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class BeliefRestClientTest {

  private static final String baseUrl = "http://localhost:8080/api";

  private WebClient webClient = WebClient.create(baseUrl);

  // Creo una instancia pasandole como argumento 'webClient'
  private BeliefRestClientService bService = new BeliefRestClientService(
    webClient
  );

  @Test
  void retrieveAllBeliefs() {
    List<Belief> beliefs = bService.retrieveAllBeliefs();

    assertTrue(beliefs.size() > 0);
  }

  @Test
  void retrieveBeliefBySlug() {
    String slug = "god-the-father";

    Belief belief = bService.retrieveBeliefBySlug(slug);

    assertEquals("God the Father", belief.getTitle());
  }

  @Test
  void retrieveBeliefBySlugNotFound() {
    String slug = "god-the-fater";

    // Cualquier error que recibamos de la instancia del servicio va a ser del tipo del 1° param:
    Assertions.assertThrows(
      WebClientResponseException.class,
      () -> bService.retrieveBeliefBySlug(slug)
    );
  }
}
