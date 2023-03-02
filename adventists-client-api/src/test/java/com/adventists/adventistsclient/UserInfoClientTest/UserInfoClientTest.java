package com.adventists.adventistsclient.UserInfoClientTest;

import com.adventists.adventistsclient.constants.AdventistRestClientConstants;
import com.adventists.adventistsclient.model.UserInfo;
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
public class UserInfoClientTest {

  private static final String baseUrl =
    AdventistRestClientConstants.API_BIBLE_STUDIES_BASE_URL;

  @Autowired
  private WebTestClient webTestClient;

  @Test
  @Order(1)
  public void testGetAllUsersInfo() {
    webTestClient
      .get()
      .uri(baseUrl + AdventistRestClientConstants.GET_ALL_USERS_INFO)
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isOk()
      .expectHeader()
      .contentType(MediaType.APPLICATION_JSON)
      .expectBodyList(UserInfo.class);
  }

  @Test
  @Order(2)
  void testAddNewBibleStudy() {
    UserInfo userInfo = UserInfoDataTest.createUserInfo().get();
    webTestClient
      .post()
      .uri(baseUrl + AdventistRestClientConstants.POST_BIBLE_STUDY)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .body(Mono.just(userInfo), UserInfo.class)
      .exchange()
      .expectStatus()
      .isCreated()
      .expectHeader()
      .contentType(MediaType.APPLICATION_JSON)
      .expectBody()
      .jsonPath("$.email")
      .isNotEmpty()
      .jsonPath("$.email")
      .isEqualTo("useremail@gmail.com");
  }
  // @Test
  // @Order(8)
  // void testDeleteBibleStudyById() {
  //   webTestClient
  //     .delete()
  //     .uri(baseUrl + AdventistRestClientConstants.DELETE_BIBLE_STUDY_BY_ID, 111)
  //     .exchange()
  //     .expectStatus()
  //     .isOk();
  // }
}
