package com.canor.adventist.repository;

import com.canor.adventist.model.Belief;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBeliefRepository extends MongoRepository<Belief, Integer> {
  @Query("{'title': ?0}")
  Optional<Belief> findByTitle(String title);
}
