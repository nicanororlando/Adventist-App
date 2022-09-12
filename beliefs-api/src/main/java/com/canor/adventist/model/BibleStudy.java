package com.canor.adventist.model;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "bible-study")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BibleStudy {

  @Id
  private Integer id;

  @NotEmpty(message = "Title cannot be null")
  private String title;

  @NotEmpty(message = "Description cannot be null")
  private String description;

  @NotEmpty(message = "Image cannot be null")
  private String image;

  private String link;
}
