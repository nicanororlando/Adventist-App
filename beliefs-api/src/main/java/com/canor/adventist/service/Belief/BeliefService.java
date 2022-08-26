package com.canor.adventist.service.Belief;

import com.canor.adventist.controller.BeliefController;
import com.canor.adventist.exceptions.Exceptions;
import com.canor.adventist.model.Belief;
import com.canor.adventist.repository.IBeliefRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Service
// Crea un constr al cual le pasa una referencia para inicializar el repositorio.
@RequiredArgsConstructor
public class BeliefService implements IBeliefService {

  private final Path rootFolder = Paths.get("uploads");

  @Autowired
  private final IBeliefRepository beliefRepository;

  @Override
  public void save(Belief belief)
    throws ConstraintViolationException, Exceptions {
    Optional<Belief> optionalBeliefTitle = beliefRepository.findByTitle(
      belief.getTitle()
    );
    Optional<Belief> optionalBeliefId = beliefRepository.findById(
      belief.getId()
    );

    if (optionalBeliefTitle.isPresent()) throw new Exceptions(
      Exceptions.TitleAlreadyExists("Belief")
    ); else if (optionalBeliefId.isPresent()) throw new Exceptions(
      Exceptions.IdAlreadyExists("Belief")
    ); else beliefRepository.save(belief);
  }

  @Override
  public void saveFile(Integer id, MultipartFile file) throws Exception {
    Optional<Belief> optionalBelief = beliefRepository.findById(id);
    Belief belief = optionalBelief.get();

    if (optionalBelief.isPresent()) {
      String newImage =
        UUID.randomUUID().toString() +
        "-" +
        file
          .getOriginalFilename()
          .replace(" ", "")
          .replace(":", "")
          .replace("//", "");

      // Elimino el archivo anterior (Si habia uno):
      if (belief.getImage() != null) {
        String previousImage = belief
          .getImage()
          .replace("http://localhost:8080/api/beliefs/file/", "");

        System.out.println("This file will be deleted: ");
        System.out.println(previousImage);
        System.out.println("And replaced by: ");
        System.out.println(newImage);

        Files.delete(this.rootFolder.resolve(previousImage));
      }

      // Guardo local:
      Files.copy(file.getInputStream(), this.rootFolder.resolve(newImage));

      String url = MvcUriComponentsBuilder
        .fromMethodName(BeliefController.class, "getImage", newImage)
        .build()
        .toString();

      // Guardo en la db:
      belief.setImage(url);

      beliefRepository.save(belief);
    } else {
      throw new Exceptions(Exceptions.NotFoundException("Belief", id));
    }
  }

  @Override
  public List<Belief> findAll() {
    return beliefRepository.findAll();
  }

  @Override
  public Optional<Belief> findById(Integer id) {
    return beliefRepository.findById(id);
  }

  @Override
  public void update(Integer id, Belief belief)
    throws ConstraintViolationException, Exceptions {
    Optional<Belief> optionalBelief = beliefRepository.findById(id);
    if (optionalBelief.isPresent()) {
      Belief newBelief = optionalBelief.get();
      newBelief.setTitle(
        belief.getTitle() != null ? belief.getTitle() : newBelief.getTitle()
      );
      newBelief.setDescription(
        belief.getDescription() != null
          ? belief.getDescription()
          : newBelief.getDescription()
      );
      newBelief.setImage(
        belief.getImage() != null ? belief.getImage() : newBelief.getImage()
      );

      beliefRepository.save(newBelief);
    } else {
      throw new Exceptions(Exceptions.NotFoundException("Belief", id));
    }
  }

  @Override
  public void deleteById(Integer id) {
    beliefRepository.deleteById(id);
  }

  @Override
  public Resource uri(String name) throws Exception {
    Path file = rootFolder.resolve(name);
    Resource resource = new UrlResource(file.toUri());
    return resource;
  }
}
