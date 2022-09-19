package com.adventists.biblestudies.controller;

import com.adventists.biblestudies.exceptions.Exceptions;
import com.adventists.biblestudies.model.BibleStudy;
import com.adventists.biblestudies.service.StudyBible.IBibleStudyService;
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
@RequestMapping("/api/bible-studies")
@RequiredArgsConstructor
public class BibleStudyController {

  private final IBibleStudyService bibleStudyService;

  @GetMapping
  public ResponseEntity<List<BibleStudy>> findAll() {
    List<BibleStudy> Doctrines = bibleStudyService.findAll();
    return ResponseEntity
      .status(Doctrines.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND)
      .body(Doctrines);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Integer id) {
    try {
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(bibleStudyService.findById(id).get());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody BibleStudy bibleStudy) {
    try {
      bibleStudyService.save(bibleStudy);
      return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(bibleStudyService.findById(bibleStudy.getId()));
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
    @RequestBody BibleStudy bibleStudy
  ) {
    try {
      bibleStudyService.update(id, bibleStudy);
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(bibleStudyService.findById(id));
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
      bibleStudyService.deleteById(id);
      return ResponseEntity
        .status(HttpStatus.OK)
        .body("Succesfully deleted by id " + id);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
