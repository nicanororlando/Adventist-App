package com.canor.adventist.DataTest;

import com.adventists.biblestudies.model.BibleStudy;
import java.util.Optional;

public class BibleStudyDataTest {

  public static Optional<BibleStudy> createBibleStudy111() {
    return Optional.of(
      new BibleStudy(
        111,
        "bible-study-111",
        "Bible Study 111",
        "image-111",
        "https://imagenbiblestudy111.com"
      )
    );
  }

  public static Optional<BibleStudy> createBibleStudy222() {
    return Optional.of(
      new BibleStudy(
        222,
        "bible-study-222",
        "Bible Study 222",
        "image-222",
        "https://imagenbiblestudy222.com"
      )
    );
  }

  public static Optional<BibleStudy> createInvalidBibleStudy() {
    return Optional.of(
      new BibleStudy(
        111,
        null,
        "Bible Study 111",
        "image-111",
        "https://imagenbiblestudy111.com"
      )
    );
  }

  public static Optional<BibleStudy> createExistentIdBibleStudy() {
    return Optional.of(
      new BibleStudy(
        111,
        "bible-study-111",
        "Bible Study 111",
        "image-111",
        "https://imagenbiblestudy111.com"
      )
    );
  }
}
