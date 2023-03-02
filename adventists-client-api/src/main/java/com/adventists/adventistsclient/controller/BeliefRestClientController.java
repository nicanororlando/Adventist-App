package com.adventists.adventistsclient.controller;

import com.adventists.adventistsclient.model.Belief.Belief;
import com.adventists.adventistsclient.service.BeliefRestClient.IBeliefRestClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/beliefs")
public class BeliefRestClientController {

  @Autowired
  private IBeliefRestClientService beliefRestClientService;

  @GetMapping
  public ResponseEntity<Mono<List<Belief>>> retrieveAllBeliefs() {
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(beliefRestClientService.retrieveAllBeliefs());
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Mono<Belief>> retrieveBeliefBySlug(
    @PathVariable Integer id
  ) {
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(beliefRestClientService.retrieveBeliefById(id));
  }

  @GetMapping("/slug/{slug}")
  public ResponseEntity<Mono<Belief>> retrieveBeliefBySlug(
    @PathVariable String slug
  ) {
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(beliefRestClientService.retrieveBeliefBySlug(slug));
  }

  @PostMapping
  public ResponseEntity<Mono<Belief>> addNewBelief(@RequestBody Belief belief) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .contentType(MediaType.APPLICATION_JSON)
      .body(beliefRestClientService.addNewBelief(belief));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Mono<Belief>> updateBeliefById(
    @PathVariable Integer id,
    @RequestBody Belief belief
  ) {
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(beliefRestClientService.updateBeliefById(id, belief));
  }
}
