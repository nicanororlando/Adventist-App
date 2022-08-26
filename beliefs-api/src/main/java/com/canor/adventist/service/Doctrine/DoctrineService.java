package com.canor.adventist.service.Doctrine;

import com.canor.adventist.exceptions.Exceptions;
import com.canor.adventist.model.Doctrine;
import com.canor.adventist.repository.IDoctrineRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// Crea un constr al cual le pasa una referencia para inicializar el repositorio.
@RequiredArgsConstructor
public class DoctrineService implements IDoctrineService {

  @Autowired
  private final IDoctrineRepository doctrineRepository;

  @Override
  public void save(Doctrine doctrine)
    throws ConstraintViolationException, Exceptions {
    Optional<Doctrine> optionalDoctrineTitle = doctrineRepository.findByTitle(
      doctrine.getTitle()
    );
    Optional<Doctrine> optionalDoctrineId = doctrineRepository.findById(
      doctrine.getId()
    );

    if (optionalDoctrineTitle.isPresent()) throw new Exceptions(
      Exceptions.TitleAlreadyExists("Doctrine")
    ); else if (optionalDoctrineId.isPresent()) throw new Exceptions(
      Exceptions.IdAlreadyExists("Doctrine")
    ); else doctrineRepository.save(doctrine);
  }

  @Override
  public List<Doctrine> findAll() {
    return doctrineRepository.findAll();
  }

  @Override
  public Optional<Doctrine> findById(Integer id) {
    return doctrineRepository.findById(id);
  }

  @Override
  public void update(Integer id, Doctrine doctrine)
    throws ConstraintViolationException, Exceptions {
    Optional<Doctrine> optionalDoctrine = doctrineRepository.findById(id);
    if (optionalDoctrine.isPresent()) {
      Doctrine newDoctrine = optionalDoctrine.get();
      newDoctrine.setTitle(
        doctrine.getTitle() != null ? doctrine.getTitle() : newDoctrine.getTitle()
      );
      newDoctrine.setDescription(
        doctrine.getDescription() != null
          ? doctrine.getDescription()
          : newDoctrine.getDescription()
      );

      doctrineRepository.save(newDoctrine);
    } else {
      throw new Exceptions(Exceptions.NotFoundException("Doctrine", id));
    }
  }

  @Override
  public void deleteById(Integer id) {
    doctrineRepository.deleteById(id);
  }
}