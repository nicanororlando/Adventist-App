package com.canor.adventist.repository;

import com.canor.adventist.model.BibleStudy;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IBibleStudyRepository
  extends MongoRepository<BibleStudy, Integer> {
  @Query("{'title': ?0}")
  Optional<BibleStudy> findByTitle(String title);
}
