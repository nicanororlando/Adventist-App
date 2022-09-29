package com.adventists.beliefsapi.model.Belief;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "belief")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Belief {

  @Id
  private Integer id;

  private String slug;

  @NotEmpty(message = "Title cannot be null")
  private String title;

  @NotEmpty(message = "Description cannot be null")
  private List<String> description;

  private String image;

  private List<BeliefDetails> moreDetails;
}
