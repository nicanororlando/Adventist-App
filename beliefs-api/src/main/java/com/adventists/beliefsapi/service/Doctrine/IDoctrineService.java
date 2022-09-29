package com.adventists.beliefsapi.service.Doctrine;

import com.adventists.beliefsapi.exceptions.Exceptions;
import com.adventists.beliefsapi.model.Doctrine;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;

public interface IDoctrineService {
  public void save(Doctrine doctrine)
    throws ConstraintViolationException, Exceptions;

  public List<Doctrine> findAll();

  public Optional<Doctrine> findById(Integer id);

  public void update(Integer id, Doctrine doctrine)
    throws ConstraintViolationException, Exceptions;

  public void deleteById(Integer id);
}
