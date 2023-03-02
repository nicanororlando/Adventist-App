package com.adventists.adventistsclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BibleStudy {

  private Integer id;
  private String title;
  private String description;
  private String image;
  private String link;
}
