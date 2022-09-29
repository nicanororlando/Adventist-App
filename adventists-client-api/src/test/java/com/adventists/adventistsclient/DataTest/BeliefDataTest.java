package com.adventists.adventistsclient.DataTest;

import com.adventists.adventistsclient.model.Belief.Belief;
import com.adventists.adventistsclient.model.Belief.BeliefDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BeliefDataTest {

  static List<String> description = new ArrayList<>(
    Arrays.asList("This", "is", "description", "1")
  );

  static List<BeliefDetails> details = new ArrayList<>(
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

  public static Optional<Belief> createBelief111() {
    return Optional.of(
      new Belief(
        111,
        "belief-111",
        "Belief 111",
        description,
        "https://imagenbelief111.com",
        details
      )
    );
  }

  public static Optional<Belief> createInvalidBelief() {
    return Optional.of(
      new Belief(
        111,
        "",
        "Belief 111",
        description,
        "https://imagenbelief111.com",
        details
      )
    );
  }

  public static Optional<Belief> createExistentIdBelief() {
    return Optional.of(
      new Belief(
        1,
        "does'nt-matter",
        "Does'nt Matter",
        description,
        "https://imagenbelief111.com",
        details
      )
    );
  }

  public static Optional<Belief> createExistentSlugBelief() {
    return Optional.of(
      new Belief(
        12345,
        "god-the-father",
        "God the father",
        description,
        "https://imagenbelief111.com",
        details
      )
    );
  }
}
