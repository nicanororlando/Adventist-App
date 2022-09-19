package com.adventists.biblestudies.model;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "info-user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoUser {

  @Id
  private String id;

  @NotEmpty
  private String name;

  @NotEmpty
  private Long phoneNumber;

  @NotEmpty
  private String email;

  private boolean wantToStudy;
  private boolean wantMoreInfo;
  private boolean enableSendMsg;
}
