package com.adventists.adventistsclient.controller;

import com.adventists.adventistsclient.model.BibleStudy;
import com.adventists.adventistsclient.model.UserInfo;
import com.adventists.adventistsclient.service.BibleStudyRestClient.IBibleStudyRestClientService;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bible-studies")
public class BibleStudyRestClientController {

  @Autowired
  private IBibleStudyRestClientService bibleStudyRestClientService;

  @GetMapping
  public ResponseEntity<Mono<List<BibleStudy>>> retrieveAllBibleStudies() {
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(bibleStudyRestClientService.retrieveAllBibleStudies());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Mono<BibleStudy>> retrieveBibleStudyById(
    @PathVariable Integer id
  ) {
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(bibleStudyRestClientService.retrieveBibleStudyById(id));
  }

  @PostMapping
  public ResponseEntity<Mono<BibleStudy>> addNewBibleStudy(
    @RequestBody BibleStudy bibleStudy
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .contentType(MediaType.APPLICATION_JSON)
      .body(bibleStudyRestClientService.addNewBibleStudy(bibleStudy));
  }

  @PatchMapping
  public ResponseEntity<Mono<BibleStudy>> updateBeliefById(
    @PathVariable Integer id,
    @RequestBody BibleStudy bibleStudy
  ) {
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(bibleStudyRestClientService.updateBibleStudyById(id, bibleStudy));
  }

  @GetMapping("/users")
  public ResponseEntity<Mono<List<UserInfo>>> retrieveAllUsersInfo() {
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(bibleStudyRestClientService.retrieveAllUsersInfo());
  }

  @PostMapping("/users")
  public ResponseEntity<?> addNewUserInfo(@RequestBody UserInfo userInfo) {
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(bibleStudyRestClientService.addNewUserInfo(userInfo));
  }
}
