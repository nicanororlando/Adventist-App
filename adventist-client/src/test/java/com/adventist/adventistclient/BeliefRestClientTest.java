package com.adventist.adventistclient;

// JUnit y Mockito.
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.adventist.adventistclient.constants.AdventistRestClientConstants;
import com.adventist.adventistclient.dto.Belief.Belief;
import com.adventist.adventistclient.service.BeliefRestClient.BeliefRestClientService;
import com.adventist.adventistclient.service.BeliefRestClient.IBeliefRestClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BeliefRestClientTest {

  public static MockWebServer mockBackEnd;

  private WebClient.RequestBodyUriSpec requestBodyUriSpec;
  private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
  private WebClient.RequestHeadersSpec requestHeadersSpec;
  private WebClient.RequestBodySpec requestBodySpec;
  private WebClient.ResponseSpec responseSpec;
  private WebClient webClientMock;

  // REPASAR CONCEPTO DE @AUTOWIRED
  private BeliefRestClientService beliefRestClientService = new BeliefRestClientService();

  // @InjectMocks
  // private BeliefRestClientService beliefRestClientService;

  ObjectMapper objectMapper;

  @BeforeEach
  // Ejecuta antes de cada test.
  void config() {
    objectMapper = new ObjectMapper();
  }

  @BeforeEach
  void mockWebClient() {
    requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
    requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
    requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
    requestBodySpec = mock(WebClient.RequestBodySpec.class);
    responseSpec = mock(WebClient.ResponseSpec.class);
    webClientMock = mock(WebClient.class);
  }

  @BeforeAll
  static void setUp() throws IOException {
    mockBackEnd = new MockWebServer();
    mockBackEnd.start();
  }

  @AfterAll
  static void tearDown() throws IOException {
    mockBackEnd.shutdown();
  }

  @Test
  void retrieveAllBeliefs() {
    when(webClientMock.get()).thenReturn(requestHeadersUriSpec);
    when(requestHeadersUriSpec.uri(ArgumentMatchers.<String>notNull()))
      .thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    when(responseSpec.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
      .thenReturn(Mono.just("resp"));
    //   Mono<List<Belief>> beliefList = beliefRestClientService.retrieveAllBeliefs();
    //   assertTrue(beliefList.block().size() > 0);
  }

  @Test
  void retrieveBeliefBySlug() {
    when(webClientMock.get()).thenReturn(requestHeadersUriSpec);
    when(requestHeadersUriSpec.uri("/holy-scriptures"))
      .thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    when(
      responseSpec
        .bodyToMono(ArgumentMatchers.<Class<String>>notNull())
        .thenReturn(Mono.just("resp"))
    );
    Mono<Belief> beliefMono = beliefRestClientService.retrieveBeliefBySlug(
      "holy-scriptures"
    );
    // StepVerifier
    //   .create(beliefMono)
    //   .expectNextMatches(belief -> belief.getTitle().equals("Holy scriptures"))
    //   .verifyComplete();
  }

  @Test
  void addNewBelief() {
    // Belief belief = DataTest.createBelief111().get();

    when(webClientMock.post()).thenReturn(requestBodyUriSpec);
    when(requestBodyUriSpec.uri(ArgumentMatchers.<String>notNull()))
      .thenReturn(requestBodySpec);
    when(requestBodySpec.accept(any())).thenReturn(requestBodySpec);
    when(requestBodySpec.body(any())).thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    when(responseSpec.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
      .thenReturn(Mono.just("resp"));
    // assertDoesNotThrow(() -> beliefRestClientService.addNewBelief(belief));
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
}
