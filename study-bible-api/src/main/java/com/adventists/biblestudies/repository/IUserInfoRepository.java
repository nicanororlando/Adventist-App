package com.adventists.biblestudies.repository;

import com.adventists.biblestudies.model.UserInfo;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IUserInfoRepository extends MongoRepository<UserInfo, String> {
  @Query("{'email': ?0}")
  Optional<UserInfo> findByEmail(String email);
}
