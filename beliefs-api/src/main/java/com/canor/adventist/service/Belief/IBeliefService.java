package com.canor.adventist.service.Belief;

import com.canor.adventist.exceptions.Exceptions;
import com.canor.adventist.model.Belief;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IBeliefService {
  public void save(Belief belief)
    throws ConstraintViolationException, Exceptions;

  public void saveFile(Integer id, MultipartFile file) throws Exception;

  public List<Belief> findAll();

  public Optional<Belief> findById(Integer id);

  public void update(Integer id, Belief belief)
    throws ConstraintViolationException, Exceptions;

  public void deleteById(Integer id);

  public Resource uri(String name) throws Exception;
}
