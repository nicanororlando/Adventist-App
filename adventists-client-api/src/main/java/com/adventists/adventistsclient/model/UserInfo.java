package com.adventists.adventistsclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

  private String id;
  private String name;
  private Long phoneNumber;
  private String email;
  private boolean wantToStudy;
  private boolean wantMoreInfo;
  private boolean enableSendMsg;
}
