package com.adventists.biblestudies.service.StudyBible;

import com.adventists.biblestudies.exceptions.Exceptions;
import com.adventists.biblestudies.model.BibleStudy;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;

public interface IBibleStudyService {
  public void save(BibleStudy studyBible)
    throws ConstraintViolationException, Exceptions;

  public List<BibleStudy> findAll();

  public Optional<BibleStudy> findById(Integer id);

  public void update(Integer id, BibleStudy studyBible)
    throws ConstraintViolationException, Exceptions;

  public void deleteById(Integer id);
}
