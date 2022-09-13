package com.canor.adventist.test;

import com.canor.adventist.model.Belief.Belief;
import com.canor.adventist.model.Belief.BeliefDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DataTest {

  public static Optional<Belief> createBelief111() {
    List<String> description1 = new ArrayList<>(
      Arrays.asList("This", "is", "description", "1")
    );

    List<BeliefDetails> details1 = new ArrayList<>(
      Arrays.asList(
        new BeliefDetails(
          "First title",
          Arrays.asList("p1", "p2", "p3"),
          "firstImageDetail.com"
        ),
        new BeliefDetails(
          "Second title",
          Arrays.asList("p1", "p2", "p3"),
          "secondImageDetail.com"
        ),
        new BeliefDetails(
          "Third title",
          Arrays.asList("p1", "p2", "p3"),
          "thirdImageDetail.com"
        ),
        new BeliefDetails(
          "Fourth title",
          Arrays.asList("p1", "p2", "p3"),
          "fourthImageDetail.com"
        )
      )
    );

    return Optional.of(
      new Belief(
        111,
        "belief-111",
        "Belief 111",
        description1,
        "https://imagenbelief111.com",
        details1
      )
    );
  }

  public static Optional<Belief> createBelief222() {
    List<String> description2 = Arrays.asList(
      "This",
      "is",
      "description",
      "222"
    );

    List<BeliefDetails> details2 = Arrays.asList(
      new BeliefDetails(
        "First title",
        Arrays.asList("p1", "p2", "p3"),
        "firstImageDetail.com"
      ),
      new BeliefDetails(
        "Second title",
        Arrays.asList("p1", "p2", "p3"),
        "secondImageDetail.com"
      ),
      new BeliefDetails(
        "Third title",
        Arrays.asList("p1", "p2", "p3"),
        "thirdImageDetail.com"
      ),
      new BeliefDetails(
        "Fourth title",
        Arrays.asList("p1", "p2", "p3"),
        "fourthImageDetail.com"
      )
    );

    return Optional.of(
      new Belief(
        222,
        "belief-222",
        "Belief 222",
        description2,
        "https://imagenbelief222.com",
        details2
      )
    );
  }
}
