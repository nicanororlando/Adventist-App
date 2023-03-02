package com.adventists.biblestudies.service.UserInfo;

import com.adventists.biblestudies.exceptions.Exceptions;
import com.adventists.biblestudies.model.UserInfo;
import java.util.List;

public interface IUserInfoService {
  public List<UserInfo> findAll();

  public UserInfo save(UserInfo userInfo) throws Exceptions;
}
