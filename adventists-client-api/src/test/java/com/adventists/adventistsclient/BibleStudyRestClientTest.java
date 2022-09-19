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

import com.adventists.adventistsclient.model.BibleStudy;
import com.adventists.adventistsclient.service.BeliefRestClient.BeliefRestClientService;
import com.adventists.adventistsclient.service.BibleStudyRestClient.BibleStudyRestClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class BibleStudyRestClientTest {

  public static MockWebServer mockBackEnd;

  private WebClient.RequestBodyUriSpec requestBodyUriSpec;
  private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
  private WebClient.RequestHeadersSpec requestHeadersSpec;
  private WebClient.RequestBodySpec requestBodySpec;
  private WebClient.ResponseSpec responseSpec;
  private WebClient webClientMock;

  // REPASAR CONCEPTO DE @AUTOWIRED
  private BibleStudyRestClientService bibleStudyRestClientService = new BibleStudyRestClientService();

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
  void retrieveAllBibleStudies() {
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
  void retrieveBibleStudyBySlug() {
    when(webClientMock.get()).thenReturn(requestHeadersUriSpec);
    when(requestHeadersUriSpec.uri("/", 1)).thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    when(
      responseSpec
        .bodyToMono(ArgumentMatchers.<Class<String>>notNull())
        .thenReturn(Mono.just("resp"))
    );
    Mono<BibleStudy> bibleStudyMono = bibleStudyRestClientService.retrieveBibleStudyById(
      1
    );
    // StepVerifier
    //   .create(beliefMono)
    //   .expectNextMatches(belief -> belief.getTitle().equals("Holy scriptures"))
    //   .verifyComplete();
  }

  @Test
  void addNewBibleStudy() {
    // BibleStudy bibleStudy = DataTest.createBelief111().get();

    when(webClientMock.post()).thenReturn(requestBodyUriSpec);
    when(requestBodyUriSpec.uri(ArgumentMatchers.<String>notNull()))
      .thenReturn(requestBodySpec);
    when(requestBodySpec.accept(any())).thenReturn(requestBodySpec);
    when(requestBodySpec.body(any())).thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    when(responseSpec.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
      .thenReturn(Mono.just("resp"));
    // assertDoesNotThrow(() -> beliefRestClientService.addNewBelief(bibleStudy));
  }
}
