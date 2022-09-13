package com.adventist.adventistclient.controller;

import com.adventist.adventistclient.dto.Belief.Belief;
import com.adventist.adventistclient.service.BeliefRestClient.BeliefRestClientService;
import com.adventist.adventistclient.service.BeliefRestClient.IBeliefRestClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/beliefs")
public class BeliefRestClientController {

  private static final String baseUrl = "http://localhost:8080/api";

  private WebClient webClient = WebClient.create(baseUrl);

  // Creo una instancia pasandole como argumento 'webClient'
  private BeliefRestClientService beliefRestClientService = new BeliefRestClientService(
    webClient
  );

  @GetMapping
  private List<Belief> retrieveAllBeliefs() {
    return beliefRestClientService.retrieveAllBeliefs();
  }
}
