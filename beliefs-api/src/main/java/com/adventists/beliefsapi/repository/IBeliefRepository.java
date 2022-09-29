package com.adventists.beliefsapi.repository;

import com.adventists.beliefsapi.model.Belief.Belief;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBeliefRepository extends MongoRepository<Belief, Integer> {
  @Query("{'slug': ?0}")
  Optional<Belief> findBySlug(String slug);
}
