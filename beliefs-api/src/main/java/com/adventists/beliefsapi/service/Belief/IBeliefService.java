package com.adventists.beliefsapi.service.Belief;

import com.adventists.beliefsapi.exceptions.Exceptions;
import com.adventists.beliefsapi.model.Belief.Belief;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IBeliefService {
  public Belief save(Belief belief)
    throws ConstraintViolationException, Exceptions;

  public void saveFile(Integer id, MultipartFile file) throws Exception;

  public List<Belief> findAll();

  public Optional<Belief> findById(Integer id);

  public Optional<Belief> findBySlug(String slug);

  public Belief update(Integer id, Belief belief)
    throws ConstraintViolationException, Exceptions;

  public void deleteById(Integer id);

  public Resource uri(String name) throws Exception;
}
