package com.adventists.biblestudies.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "user-info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

  @Id
  private String id;

  @NotEmpty
  private String name;

  @NotNull
  private Long phoneNumber;

  @NotEmpty
  private String email;

  private boolean wantToStudy;
  private boolean wantMoreInfo;
  private boolean enableSendMsg;
}
