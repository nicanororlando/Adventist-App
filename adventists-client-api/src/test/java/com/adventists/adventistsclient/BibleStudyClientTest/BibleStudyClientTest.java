package com.adventists.adventistsclient.BibleStudyClientTest;

import com.adventists.adventistsclient.constants.AdventistRestClientConstants;
import com.adventists.adventistsclient.model.BibleStudy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BibleStudyClientTest {

  private static final String baseUrl =
    AdventistRestClientConstants.API_BIBLE_STUDIES_BASE_URL;

  @Autowired
  private WebTestClient webTestClient;

  @Test
  @Order(1)
  public void testGetAllBibleStudies() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_ALL_BIBLE_STUDIES)
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isOk()
      .expectHeader()
      .contentType(MediaType.APPLICATION_JSON)
      .expectBodyList(BibleStudy.class);
  }

  @Test
  @Order(2)
  void testRetrieveBibleStudyById() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_BIBLE_STUDY_BY_ID, 1)
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
  void testRetrieveBibleStudyById_NOT_FOUND() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_BIBLE_STUDY_BY_ID, 111)
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
  @Order(4)
  void testAddNewBibleStudy() {
    BibleStudy BibleStudy = BibleStudyDataTest.createBibleStudy111().get();
    webTestClient
      .post()
      .uri(baseUrl + AdventistRestClientConstants.POST_BIBLE_STUDY)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .body(Mono.just(BibleStudy), BibleStudy.class)
      .exchange()
      .expectStatus()
      .isCreated()
      .expectHeader()
      .contentType(MediaType.APPLICATION_JSON)
      .expectBody()
      .jsonPath("$.title")
      .isNotEmpty()
      .jsonPath("$.title")
      .isEqualTo("bible-study-111");
  }

  @Test
  @Order(5)
  void testRetrieveBibleStudyByIdCreated() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_BIBLE_STUDY_BY_ID, 111)
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
  @Order(6)
  void testPatchBibleStudy() {
    BibleStudy BibleStudy = BibleStudyDataTest.createBibleStudy111().get();
    BibleStudy.setTitle("Title has been changed");
    webTestClient
      .patch()
      .uri(
        baseUrl + AdventistRestClientConstants.PATCH_BIBLE_STUDY_BY_ID,
        BibleStudy.getId()
      )
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .body(Mono.just(BibleStudy), BibleStudy.class)
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
  @Order(7)
  void testRetrieveBibleStudyByIdPatched() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_BIBLE_STUDY_BY_ID, 111)
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
  @Order(8)
  void testDeleteBibleStudyById() {
    webTestClient
      .delete()
      .uri(baseUrl + AdventistRestClientConstants.DELETE_BIBLE_STUDY_BY_ID, 111)
      .exchange()
      .expectStatus()
      .isOk();
  }
}
