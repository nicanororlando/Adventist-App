package com.adventists.adventistsclient.service.BeliefRestClient;

import com.adventists.adventistsclient.model.Belief.Belief;
import java.util.List;
import reactor.core.publisher.Mono;

public interface IBeliefRestClientService {
  public Mono<List<Belief>> retrieveAllBeliefs();

  public Mono<Belief> retrieveBeliefById(Integer id);

  public Mono<Belief> retrieveBeliefBySlug(String slug);

  public Mono<Belief> addNewBelief(Belief belief);

  public Mono<Belief> updateBeliefById(Integer id, Belief belief);

  public String deleteBeliefById(Integer id);
}
