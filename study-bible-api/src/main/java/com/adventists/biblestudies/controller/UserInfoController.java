package com.adventists.biblestudies.controller;

import com.adventists.biblestudies.exceptions.Exceptions;
import com.adventists.biblestudies.model.UserInfo;
import com.adventists.biblestudies.service.UserInfo.IUserInfoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bible-studies/users")
public class UserInfoController {

  @Autowired
  private IUserInfoService userInfoService;

  @GetMapping
  public ResponseEntity<?> findAll() {
    List<UserInfo> allUsersInfo = userInfoService.findAll();
    return ResponseEntity
      .status(allUsersInfo.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND)
      .body(allUsersInfo);
  }

  @PostMapping
  public ResponseEntity<?> saveUser(@RequestBody UserInfo userInfo) {
    Map<String, Object> response = new HashMap<>();

    try {
      userInfoService.save(userInfo);
      response.put("message", "Data sent successfully");
      response.put("userInfo", userInfo);

      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (ConstraintViolationException e) {
      response.put("message", e.getMessage());

      return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(response);
    } catch (Exceptions e) {
      response.put("message", e.getMessage());

      return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
  }
}
