package com.adventists.adventistsclient;

// JUnit y Mockito.
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.adventists.adventistsclient.constants.AdventistRestClientConstants;
import com.adventists.adventistsclient.model.Belief.Belief;
import com.adventists.adventistsclient.service.BeliefRestClient.BeliefRestClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.mockwebserver.MockWebServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeliefRestClientTest {

  @Autowired
  private WebTestClient webTestClient;

  public static MockWebServer mockBackEnd;

  private WebClient.RequestBodyUriSpec requestBodyUriSpec;
  private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
  private WebClient.RequestHeadersSpec requestHeadersSpec;
  private WebClient.RequestBodySpec requestBodySpec;
  private WebClient.ResponseSpec responseSpec;
  private WebClient webClientMock;

  private static final String baseUrl =
    AdventistRestClientConstants.API_BELIEFS_BASE_URL;

  // REPASAR CONCEPTO DE @AUTOWIRED
  private BeliefRestClientService beliefRestClientService = new BeliefRestClientService();

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
  @Order(1)
  void testRetrieveAllBeliefs() {
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
  @Order(2)
  void testRetrieveBeliefById() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_BELIEF_BY_ID, 1)
      .exchange()
      .expectStatus()
      .isOk()
      .expectBody()
      .consumeWith(
        response ->
          Assertions.assertThat(response.getResponseBody()).isNotNull()
      );
  }

  @Test
  @Order(3)
  void testAddNewBelief() {
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
}
