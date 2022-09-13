package com.adventist.adventistclient.controller;

import com.adventist.adventistclient.dto.Belief.Belief;
import com.adventist.adventistclient.service.BeliefRestClient.BeliefRestClientService;
import com.adventist.adventistclient.service.BeliefRestClient.IBeliefRestClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/beliefs")
public class BeliefRestClientController {

  @Autowired
  private IBeliefRestClientService beliefRestClientService;

  @GetMapping
  private ResponseEntity<Mono<List<Belief>>> retrieveAllBeliefs() {
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      // Aca me subscribo al flujo y listo los clientes.
      .body(beliefRestClientService.retrieveAllBeliefs());
  }
}
