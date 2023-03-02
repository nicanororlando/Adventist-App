package com.adventists.beliefsapi.service.Belief;

import com.adventists.beliefsapi.controller.BeliefController;
import com.adventists.beliefsapi.exceptions.Exceptions;
import com.adventists.beliefsapi.model.Belief.Belief;
import com.adventists.beliefsapi.repository.IBeliefRepository;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Service
public class BeliefService implements IBeliefService {

  private final Path rootFolder = Path.of(
    "H:/Proyectos/Adventist-App/beliefs-api/uploads"
  );

  @Autowired
  private IBeliefRepository beliefRepository;

  @Override
  public Belief save(Belief belief)
    throws ConstraintViolationException, Exceptions {
    Optional<Belief> optionalBeliefTitle = beliefRepository.findBySlug(
      belief.getSlug()
    );
    Optional<Belief> optionalBeliefId = beliefRepository.findById(
      belief.getId()
    );

    if (optionalBeliefTitle.isPresent()) throw new Exceptions(
      Exceptions.SlugAlreadyExists("Belief")
    ); else if (optionalBeliefId.isPresent()) throw new Exceptions(
      Exceptions.IdAlreadyExists("Belief")
    ); else return beliefRepository.save(belief);
  }

  @Override
  public void saveFile(Integer id, MultipartFile file) throws Exception {
    Optional<Belief> optionalBelief = beliefRepository.findById(id);
    Belief belief = optionalBelief.get();

    if (optionalBelief.isPresent()) {
      String previousImage = belief
        .getImage()
        .replace("http://localhost:8080/api/beliefs/file/", "");

      String newImage =
        UUID.randomUUID().toString() +
        "-" +
        file
          .getOriginalFilename()
          .replace(" ", "")
          .replace(":", "")
          .replace("//", "");

      // Elimino el archivo anterior (Si habia uno):
      if (Files.exists(rootFolder.resolve(previousImage))) {
        System.out.println("This file will be deleted: ");
        System.out.println(previousImage);
        System.out.println("And replaced by: ");
        System.out.println(newImage);

        Files.delete(this.rootFolder.resolve(previousImage));
      }

      System.out.println(
        "ROOT FOLDEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEER:"
      );
      System.out.println(rootFolder);

      // Guardo local:
      Files.copy(file.getInputStream(), rootFolder.resolve(newImage));

      String url = MvcUriComponentsBuilder
        .fromMethodName(BeliefController.class, "getImage", newImage)
        .build()
        .toString();

      System.out.println(url);

      // Guardo en la db:
      belief.setImage(url);

      beliefRepository.save(belief);
    } else {
      throw new Exceptions(Exceptions.NotFoundException("Belief", id));
    }
  }

  @Override
  public List<Belief> findAll() {
    List<Belief> list = beliefRepository.findAll();
    list.sort(Comparator.comparing(Belief::getId));

    return list;
  }

  @Override
  public Optional<Belief> findById(Integer id) {
    return beliefRepository.findById(id);
  }

  @Override
  public Optional<Belief> findBySlug(String slug) {
    return beliefRepository.findBySlug(slug);
  }

  @Override
  public Belief update(Integer id, Belief belief)
    throws ConstraintViolationException, Exceptions {
    Optional<Belief> optionalBelief = beliefRepository.findById(id);
    if (optionalBelief.isPresent()) {
      Belief newBelief = optionalBelief.get();
      newBelief.setSlug(
        belief.getSlug() != null ? belief.getSlug() : newBelief.getSlug()
      );
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
      newBelief.setMoreDetails(
        belief.getMoreDetails() != null
          ? belief.getMoreDetails()
          : newBelief.getMoreDetails()
      );

      return beliefRepository.save(newBelief);
    } else {
      throw new Exceptions(Exceptions.NotFoundException("Belief", id));
    }
  }

  @Override
  public void deleteById(Integer id) {
    beliefRepository.deleteById(id);
  }

  @Override
  public Resource uri(String filename) throws Exception {
    try {
      Path file = rootFolder.resolve(filename);
      Resource resource = new UrlResource(file.toUri());
      int i = 0;
      while (!resource.exists() || !resource.isReadable()) {
        Thread.sleep(100);
        if (++i == 20) {
          break;
        }
      }
      return resource;
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    } catch (InterruptedException e) {
      throw new RuntimeException("Could not read the file!");
    }
  }
}
