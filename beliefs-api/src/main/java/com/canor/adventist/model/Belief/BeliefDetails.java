package com.canor.adventist.model.Belief;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeliefDetails {

  private String title;

  private List<String> description;

  private String image;
}
