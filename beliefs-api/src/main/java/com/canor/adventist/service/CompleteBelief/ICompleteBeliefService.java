package com.canor.adventist.service.CompleteBelief;

import com.canor.adventist.exceptions.Exceptions;
import com.canor.adventist.model.CompleteBelief.CompleteBelief;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ICompleteBeliefService {
  public void save(CompleteBelief completeBelief)
    throws ConstraintViolationException, Exceptions;

  public void saveFile(Integer id, MultipartFile file) throws Exception;

  public List<CompleteBelief> findAll();

  public Optional<CompleteBelief> findById(Integer id);

  public Optional<CompleteBelief> findByTitle(String title);

  public void update(Integer id, CompleteBelief completeBelief)
    throws ConstraintViolationException, Exceptions;

  public void deleteById(Integer id);

  public Resource uri(String name) throws Exception;
}
