package com.adventists.adventistsclient.service.BibleStudyRestClient;

import com.adventists.adventistsclient.model.BibleStudy;
import com.adventists.adventistsclient.model.UserInfo;
import java.util.List;
import reactor.core.publisher.Mono;

public interface IBibleStudyRestClientService {
  public Mono<List<BibleStudy>> retrieveAllBibleStudies();

  public Mono<BibleStudy> retrieveBibleStudyById(Integer id);

  public Mono<BibleStudy> addNewBibleStudy(BibleStudy bibleStudy);

  public Mono<BibleStudy> updateBibleStudyById(
    Integer id,
    BibleStudy bibleStudy
  );

  public String deleteBibleStudyById(Integer id);

  public Mono<List<UserInfo>> retrieveAllUsersInfo();

  public Mono<UserInfo> addNewUserInfo(UserInfo userInfo);
}
