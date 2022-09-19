package com.adventist.adventistclient.dto.Belief;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Belief {

  private Integer id;
  private String slug;
  private String title;
  private List<String> description;
  private String image;
  private List<BeliefDetails> moreDetails;
}
