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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeliefRestClientTest {

  private static final String baseUrl =
    AdventistRestClientConstants.API_BELIEFS_BASE_URL;

  @Autowired
  private WebTestClient webTestClient;

  // private WebClient.RequestBodyUriSpec requestBodyUriSpec;
  // private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
  // private WebClient.RequestHeadersSpec requestHeadersSpec;
  // private WebClient.RequestBodySpec requestBodySpec;
  // private WebClient.ResponseSpec responseSpec;
  // private WebClient webClientMock;

  // @BeforeEach
  // void mockWebClient() {
  //   requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
  //   requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
  //   requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
  //   requestBodySpec = mock(WebClient.RequestBodySpec.class);
  //   responseSpec = mock(WebClient.ResponseSpec.class);
  //   webClientMock = mock(WebClient.class);
  // }

  // @Test
  // void testRetrieveAllBeliefs1() {
  //   when(webClientMock.get()).thenReturn(requestHeadersUriSpec);
  //   when(requestHeadersUriSpec.uri(ArgumentMatchers.<String>notNull()))
  //     .thenReturn(requestHeadersSpec);
  //   when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
  //   when(responseSpec.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
  //     .thenReturn(Mono.just("resp"));
  // }

  // @Test
  // void testAddNewBelief1() {
  //   when(webClientMock.post()).thenReturn(requestBodyUriSpec);
  //   when(requestBodyUriSpec.uri(ArgumentMatchers.<String>notNull()))
  //     .thenReturn(requestBodySpec);
  //   when(requestBodySpec.accept(any())).thenReturn(requestBodySpec);
  //   when(requestBodySpec.body(any())).thenReturn(requestHeadersSpec);
  //   when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
  //   when(responseSpec.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
  //     .thenReturn(Mono.just("resp"));
  // }

  @Test
  @Order(1)
  void testRetrieveAllBeliefs() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_ALL_BELIEFS)
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isOk()
      .expectHeader()
      .contentType(MediaType.APPLICATION_JSON)
      .expectBodyList(Belief.class);
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
  @Order(2)
  void testRetrieveBeliefById_NOT_FOUND() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_BELIEF_BY_ID, 111)
      .exchange()
      .expectStatus()
      .is4xxClientError()
      .expectBody()
      .consumeWith(
        response ->
          Assertions.assertThat(response.getResponseBody()).isNotNull()
      );
  }

  @Test
  @Order(3)
  void testAddNewBelief() {
    Belief belief = DataTest.createBelief111().get();
    webTestClient
      .post()
      .uri(baseUrl + AdventistRestClientConstants.POST_BELIEF)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .body(Mono.just(belief), Belief.class)
      .exchange()
      .expectStatus()
      .isCreated()
      .expectHeader()
      .contentType(MediaType.APPLICATION_JSON)
      .expectBody()
      .jsonPath("$.slug")
      .isNotEmpty()
      .jsonPath("$.slug")
      .isEqualTo("belief-111");
  }

  @Test
  @Order(4)
  void testRetrieveBeliefByIdCreated() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_BELIEF_BY_ID, 111)
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
  @Order(5)
  void testPatchBelief() {
    Belief belief = DataTest.createBelief111().get();
    belief.setTitle("Title has been changed");
    webTestClient
      .patch()
      .uri(
        baseUrl + AdventistRestClientConstants.PATCH_BELIEF_BY_ID,
        belief.getId()
      )
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .body(Mono.just(belief), Belief.class)
      .exchange()
      .expectStatus()
      .isOk()
      .expectHeader()
      .contentType(MediaType.APPLICATION_JSON)
      .expectBody()
      .jsonPath("$.title")
      .isEqualTo("Title has been changed");
  }

  @Test
  @Order(6)
  void testRetrieveBeliefByIdPatched() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_BELIEF_BY_ID, 111)
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
  @Order(7)
  void testDeleteBeliefById() {
    webTestClient
      .delete()
      .uri(baseUrl + AdventistRestClientConstants.DELETE_BELIEF_BY_ID, 111)
      .exchange()
      .expectStatus()
      .isOk();
  }
}
