package com.canor.adventist.repository;

import com.canor.adventist.model.Doctrine;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IDoctrineRepository
  extends MongoRepository<Doctrine, Integer> {
  @Query("{'title': ?0}")
  Optional<Doctrine> findByTitle(String title);
}
