package com.canor.adventist.exceptions;

public class Exceptions extends Exception {

  public Exceptions(String message) {
    super(message);
  }

  public static String NotFoundException(String model, Integer id) {
    return model + " with id '" + id + "' not found";
  }

  public static String TitleNotFoundException(String model, String title) {
    return model + " with title '" + title + "' not found";
  }

  public static String TitleAlreadyExists(String model) {
    return model + " with given title already exists";
  }

  public static String IdAlreadyExists(String model) {
    return model + " with given id already exists";
  }
}
