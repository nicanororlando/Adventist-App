package com.adventist.adventistclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.adventist.adventistclient.dto.Belief.Belief;
import com.adventist.adventistclient.service.BeliefRestClient.BeliefRestClientService;
import com.adventist.adventistclient.service.BeliefRestClient.IBeliefRestClientService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BeliefRestClientTest {

  @Autowired
  private IBeliefRestClientService beliefRestClientService;

  // @Test
  // void retrieveAllBeliefs() {
  //   Mono<List<Belief>> beliefs = beliefRestClientService.retrieveAllBeliefs();

  //   StepVerifier.create(beliefs)
  //   .expectNext(arg0)
  // }

  @Test
  void retrieveBeliefBySlug() {
    String slug = "god-the-father";

    Belief belief = beliefRestClientService.retrieveBeliefBySlug(slug);

    assertEquals("God the Father", belief.getTitle());
  }

  @Test
  void retrieveBeliefBySlugNotFound() {
    String slug = "god-the-fater";

    // Cualquier error que recibamos de la instancia del servicio va a ser del tipo del 1° param:
    Assertions.assertThrows(
      WebClientResponseException.class,
      () -> beliefRestClientService.retrieveBeliefBySlug(slug)
    );
  }

  @Test
  void addNewBelief() {
    Belief beliefAdded = beliefRestClientService.addNewBelief(
      DataTest.createBelief111().get()
    );
    System.out.println("beliefAdded: " + beliefAdded);

    assertTrue(beliefAdded.getId() == 111);
  }

  @Test
  void addNewBelief_BadRequest() {
    Assertions.assertThrows(
      WebClientResponseException.class,
      () ->
        beliefRestClientService.addNewBelief(
          DataTest.createInvalidBelief().get()
        )
    );
  }

  @Test
  void addNewBelief_ExistentId() {
    Assertions.assertThrows(
      WebClientResponseException.class,
      () ->
        beliefRestClientService.addNewBelief(
          DataTest.createExistentIdBelief().get()
        )
    );
  }

  @Test
  void addNewBelief_ExistentSlug() {
    Assertions.assertThrows(
      WebClientResponseException.class,
      () ->
        beliefRestClientService.addNewBelief(
          DataTest.createExistentSlugBelief().get()
        )
    );
  }

  @Test
  void deleteBeliefById() {
    // Belief belief = bService.addNewBelief(DataTest.createBelief111().get());
    Belief belief = DataTest.createBelief111().get();

    String response = beliefRestClientService.deleteBeliefById(belief.getId());
    String expectedMessage = "Succesfully deleted by id " + belief.getId();

    assertEquals(expectedMessage, response);
  }
}
