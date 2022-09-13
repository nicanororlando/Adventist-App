package com.adventist.adventistclient.service;

import com.adventist.adventistclient.constants.AdventistRestClientConstants;
import com.adventist.adventistclient.dto.Belief.Belief;
import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;

public class BeliefRestClientService {

  private WebClient webClient;

  public BeliefRestClientService(WebClient webClient) {
    this.webClient = webClient;
  }

  // http://localhost:8080/api/beliefs
  public List<Belief> retrieveAllBeliefs() {
    return webClient
      .get()
      .uri(AdventistRestClientConstants.GET_ALL_BELIEFS)
      // Para hacer la llamada al endpoint. Retorna un objeto de respuesta, pero con multiples valores.
      .retrieve()
      // Transformamos la respuesta a flux. Recibe como param el tipo de retorno.
      .bodyToFlux(Belief.class)
      // Guardamos los valores como lista.
      .collectList()
      // Para que la instancia 'webClient' se comporte de forma sincrona:
      .block();
  }

  // http://localhost:8080/api/beliefs/"slug"
  public Belief retrieveBeliefBySlug(String slug) {
    return webClient
      .get()
      // Al pasarle como segundo parametro el id, lo agregara automaticamente al path param.
      .uri(AdventistRestClientConstants.GET_BELIEF_BY_SLUG, slug)
      .retrieve()
      // En este caso solo va a retornar un objeto, por eso lo convertimos a flujo mono.
      .bodyToMono(Belief.class)
      .block();
  }
}
