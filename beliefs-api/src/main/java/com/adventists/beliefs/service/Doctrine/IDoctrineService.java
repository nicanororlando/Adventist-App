package com.adventists.beliefs.service.Doctrine;

import com.adventists.beliefs.exceptions.Exceptions;
import com.adventists.beliefs.model.Doctrine;
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
