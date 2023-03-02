package com.adventists.biblestudies.service.UserInfo;

import com.adventists.biblestudies.exceptions.Exceptions;
import com.adventists.biblestudies.model.UserInfo;
import com.adventists.biblestudies.repository.IUserInfoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements IUserInfoService {

  @Autowired
  private IUserInfoRepository userInfoRepository;

  @Override
  public List<UserInfo> findAll() {
    return userInfoRepository.findAll();
  }

  @Override
  public UserInfo save(UserInfo userInfo) throws Exceptions {
    Optional<UserInfo> optionalUserInfo = userInfoRepository.findByEmail(
      userInfo.getEmail()
    );
    if (optionalUserInfo.isPresent()) throw new Exceptions(
      Exceptions.EmailAlreadyExists()
    ); else return userInfoRepository.save(userInfo);
  }
}
