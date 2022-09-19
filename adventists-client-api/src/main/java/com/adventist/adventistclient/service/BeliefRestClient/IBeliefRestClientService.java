package com.adventist.adventistclient.service.BeliefRestClient;

import com.adventist.adventistclient.dto.Belief.Belief;
import java.util.List;
import reactor.core.publisher.Mono;

public interface IBeliefRestClientService {
  public Mono<List<Belief>> retrieveAllBeliefs();

  public Mono<Belief> retrieveBeliefBySlug(String slug);

  public Mono<Belief> addNewBelief(Belief belief);

  public Mono<Belief> updateBeliefById(Integer id, Belief belief);

  public String deleteBeliefById(Integer id);
}
