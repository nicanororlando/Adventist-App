package com.canor.adventist.model.CompleteBelief;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "complete-belief")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteBelief {

  @Id
  private Integer id;

  @NotEmpty(message = "Title cannot be null")
  private String title;

  @NotEmpty(message = "Description cannot be null")
  private List<String> description;

  private String image;

  private List<BeliefList> data;
}
