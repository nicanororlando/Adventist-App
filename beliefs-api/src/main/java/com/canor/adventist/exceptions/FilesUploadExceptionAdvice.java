package com.canor.adventist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FilesUploadExceptionAdvice {

  // Le indicamos que este metodo se va a invocar cuando se detecte una excepcion de este tipo
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<String> maxSizeException(
    MaxUploadSizeExceededException ex
  ) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body("Verifica el tamaño de los archivos.");
  }
}
