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

  private String name;
  private String email;
  private Long phoneNumber;
  private 
}
