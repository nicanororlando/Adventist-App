package com.adventist.adventistclient.service.BeliefRestClient;

import com.adventist.adventistclient.constants.AdventistRestClientConstants;
import com.adventist.adventistclient.dto.Belief.Belief;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@Service
public class BeliefRestClientService {

  private WebClient webClient;

  public BeliefRestClientService(WebClient webClient) {
    this.webClient = webClient;
  }

  // GET
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

  // GET
  // http://localhost:8080/api/beliefs/{slug}
  public Belief retrieveBeliefBySlug(String slug) {
    try {
      return webClient
        .get()
        // Al pasarle como segundo parametro el id, lo agregara automaticamente al path param.
        .uri(AdventistRestClientConstants.GET_BELIEF_BY_SLUG, slug)
        .retrieve()
        /* En este caso solo va a retornar un objeto, por eso lo convertimos a flujo mono. Nos va 
            a convertir la respuesta anterior a una instancia de tipo 'Belief'. Mono es tipo una promesa
            que nos va a retornar en un futuro lo que le decimos.*/
        .bodyToMono(Belief.class)
        .block();
    } catch (WebClientResponseException e) {
      log.error(
        "Error Response Code is {} and the Response body is {}",
        e.getStatusCode(),
        e.getResponseBodyAsString()
      );
      log.error("WebClientResponseException in retrieveBeliefBySlug", e);
      throw e;
    } catch (Exception e) {
      log.error("Exception in retrieveBeliefBySlug", e);
      throw e;
    }
  }

  // POST
  // http://localhost:8080/api/beliefs/
  public Belief addNewBelief(Belief belief) {
    return webClient
      .post()
      .uri(AdventistRestClientConstants.ADD_NEW_BELIEF)
      .bodyValue(belief)
      .retrieve()
      .bodyToMono(Belief.class)
      .block();
  }

  // DELETE
  // http://localhost:8080/api/beliefs/{id}
  public String deleteBeliefById(Integer id) {
    return webClient
      .delete()
      .uri(AdventistRestClientConstants.GET_BELIEF_BY_ID, id)
      .retrieve()
      .bodyToMono(String.class)
      .block();
  }
}
