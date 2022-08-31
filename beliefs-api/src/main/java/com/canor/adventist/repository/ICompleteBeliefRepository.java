package com.canor.adventist.repository;

import com.canor.adventist.model.CompleteBelief.CompleteBelief;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompleteBeliefRepository
  extends MongoRepository<CompleteBelief, Integer> {
  @Query("{'title': ?0}")
  Optional<CompleteBelief> findByTitle(String title);
}
