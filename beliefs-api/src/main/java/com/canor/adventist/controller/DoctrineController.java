package com.canor.adventist.controller;

import com.canor.adventist.exceptions.Exceptions;
import com.canor.adventist.model.Doctrine;
import com.canor.adventist.service.Doctrine.IDoctrineService;
import java.util.List;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctrines")
@RequiredArgsConstructor
public class DoctrineController {

  private final IDoctrineService doctrineService;

  @GetMapping("")
  public ResponseEntity<List<Doctrine>> findAll() {
    List<Doctrine> Doctrines = doctrineService.findAll();
    return ResponseEntity
      .status(Doctrines.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND)
      .body(Doctrines);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Integer id) {
    try {
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(doctrineService.findById(id).get());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PostMapping("")
  public ResponseEntity<?> save(@RequestBody Doctrine doctrine) {
    try {
      doctrineService.save(doctrine);
      return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(doctrineService.findById(doctrine.getId()));
    } catch (ConstraintViolationException e) {
      return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(e.getMessage());
    } catch (Exceptions e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> update(
    @PathVariable("id") Integer id,
    @RequestBody Doctrine doctrine
  ) {
    try {
      doctrineService.update(id, doctrine);
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(doctrineService.findById(id));
    } catch (Exceptions e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (ConstraintViolationException e) {
      return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Integer id) {
    try {
      doctrineService.deleteById(id);
      return ResponseEntity
        .status(HttpStatus.OK)
        .body("Succesfully deleted by id " + id);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
