package com.adventists.biblestudies.service.StudyBible;

import com.adventists.biblestudies.exceptions.Exceptions;
import com.adventists.biblestudies.model.BibleStudy;
import com.adventists.biblestudies.repository.IBibleStudyRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// Crea un constr al cual le pasa una referencia para inicializar el repositorio.
@RequiredArgsConstructor
public class BibleStudyService implements IBibleStudyService {

  @Autowired
  private final IBibleStudyRepository bibleStudyRepository;

  @Override
  public void save(BibleStudy bibleStudy)
    throws ConstraintViolationException, Exceptions {
    Optional<BibleStudy> optionalStudyBibleTitle = bibleStudyRepository.findByTitle(
      bibleStudy.getTitle()
    );
    Optional<BibleStudy> optionalStudyBibleId = bibleStudyRepository.findById(
      bibleStudy.getId()
    );

    if (optionalStudyBibleTitle.isPresent()) throw new Exceptions(
      Exceptions.SlugAlreadyExists("BibleStudy")
    ); else if (optionalStudyBibleId.isPresent()) throw new Exceptions(
      Exceptions.IdAlreadyExists("BibleStudy")
    ); else bibleStudyRepository.save(bibleStudy);
  }

  @Override
  public List<BibleStudy> findAll() {
    return bibleStudyRepository.findAll();
  }

  @Override
  public Optional<BibleStudy> findById(Integer id) {
    return bibleStudyRepository.findById(id);
  }

  @Override
  public void update(Integer id, BibleStudy bibleStudy)
    throws ConstraintViolationException, Exceptions {
    Optional<BibleStudy> optionalStudyBible = bibleStudyRepository.findById(id);
    if (optionalStudyBible.isPresent()) {
      BibleStudy newStudyBible = optionalStudyBible.get();
      newStudyBible.setTitle(
        bibleStudy.getTitle() != null
          ? bibleStudy.getTitle()
          : newStudyBible.getTitle()
      );
      newStudyBible.setDescription(
        bibleStudy.getDescription() != null
          ? bibleStudy.getDescription()
          : newStudyBible.getDescription()
      );

      bibleStudyRepository.save(newStudyBible);
    } else {
      throw new Exceptions(Exceptions.NotFoundException("BibleStudy", id));
    }
  }

  @Override
  public void deleteById(Integer id) {
    bibleStudyRepository.deleteById(id);
  }
}
