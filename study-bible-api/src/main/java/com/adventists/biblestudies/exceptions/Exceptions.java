package com.adventists.biblestudies.exceptions;

public class Exceptions extends Exception {

  public Exceptions(String message) {
    super(message);
  }

  public static String NotFoundException(String model, Integer id) {
    return model + " with id '" + id + "' not found";
  }

  public static String SlugNotFoundException(String model, String title) {
    return model + " with slug '" + title + "' not found";
  }

  public static String SlugAlreadyExists(String model) {
    return model + " with given slug already exists";
  }

  public static String IdAlreadyExists(String model) {
    return model + " with given id already exists";
  }
}
