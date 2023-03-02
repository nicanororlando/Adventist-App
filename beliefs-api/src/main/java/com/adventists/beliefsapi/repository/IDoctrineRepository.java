package com.adventists.beliefsapi.repository;

import com.adventists.beliefsapi.model.Doctrine;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IDoctrineRepository
  extends MongoRepository<Doctrine, Integer> {
  @Query("{'title': ?0}")
  Optional<Doctrine> findByTitle(String title);
}
