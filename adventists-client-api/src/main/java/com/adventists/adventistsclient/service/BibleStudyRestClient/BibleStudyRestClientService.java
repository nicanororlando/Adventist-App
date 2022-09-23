package com.adventists.adventistsclient.service.BibleStudyRestClient;

import com.adventists.adventistsclient.constants.AdventistRestClientConstants;
import com.adventists.adventistsclient.model.BibleStudy;
import com.adventists.adventistsclient.model.UserInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BibleStudyRestClientService
  implements IBibleStudyRestClientService {

  private static final String baseUrl =
    AdventistRestClientConstants.API_BIBLE_STUDIES_BASE_URL;

  private WebClient webClient = WebClient.create(baseUrl);

  // GET
  // http://localhost:8080/api/bible-studies
  @Override
  public Mono<List<BibleStudy>> retrieveAllBibleStudies() {
    return webClient
      .get()
      .uri(AdventistRestClientConstants.GET_ALL_BIBLE_STUDIES)
      .retrieve()
      .bodyToFlux(BibleStudy.class)
      .collectList();
  }

  // GET
  // http://localhost:8080/api/bible-studies/slug/{slug}
  @Override
  public Mono<BibleStudy> retrieveBibleStudyById(Integer id) {
    try {
      return webClient
        .get()
        .uri(AdventistRestClientConstants.GET_BIBLE_STUDY_BY_ID, id)
        .retrieve()
        .bodyToMono(BibleStudy.class);
    } catch (WebClientResponseException e) {
      log.error(
        "Error Response Code is {} and the Response body is {}",
        e.getStatusCode(),
        e.getResponseBodyAsString()
      );
      log.error("WebClientResponseException in retrieveBibleStudyById", e);
      throw e;
    } catch (Exception e) {
      log.error("Exception in retrieveBibleStudyById", e);
      throw e;
    }
  }

  // POST
  // http://localhost:8080/api/bible-studies
  @Override
  public Mono<BibleStudy> addNewBibleStudy(BibleStudy bibleStudy) {
    return webClient
      .post()
      .uri(AdventistRestClientConstants.POST_BIBLE_STUDY)
      .bodyValue(bibleStudy)
      .retrieve()
      .bodyToMono(BibleStudy.class);
  }

  // Patch
  // http://localhost:8080/api/bible-studies/id/{id}
  @Override
  public Mono<BibleStudy> updateBibleStudyById(
    Integer id,
    BibleStudy bibleStudy
  ) {
    return webClient
      .patch()
      .uri(AdventistRestClientConstants.PATCH_BIBLE_STUDY_BY_SLUG, id)
      .bodyValue(bibleStudy)
      .retrieve()
      .bodyToMono(BibleStudy.class);
  }

  // DELETE
  // http://localhost:8080/api/bible-studies/{id}
  @Override
  public String deleteBibleStudyById(Integer id) {
    return webClient
      .delete()
      .uri(AdventistRestClientConstants.DELETE_BIBLE_STUDY_BY_ID, id)
      .retrieve()
      .bodyToMono(String.class)
      .block();
  }

  // GET
  // http://localhost:8080/api/bible-studies/users
  @Override
  public Mono<List<UserInfo>> retrieveAllUsersInfo() {
    return webClient
      .get()
      .uri(AdventistRestClientConstants.GET_ALL_USERS_INFO)
      .retrieve()
      .bodyToFlux(UserInfo.class)
      .collectList();
  }

  // POST
  // http://localhost:8080/api/bible-studies/users
  @Override
  public Mono<UserInfo> addNewUserInfo(UserInfo userInfo) {
    return webClient
      .post()
      .uri(AdventistRestClientConstants.POST_USER_INFO)
      .bodyValue(userInfo)
      .retrieve()
      .onStatus(
        HttpStatus::isError,
        clientResponse ->
          clientResponse
            .bodyToMono(MesaggeResponse.class)
            .map(
              error ->
                new ResponseStatusException(
                  clientResponse.statusCode(),
                  error.getMessage()
                )
            )
      )
      .bodyToMono(UserInfo.class);
  }

  @Data
  private static class MesaggeResponse {

    private String message;
  }
}
