package com.canor.adventist.exceptions;

public class Exceptions extends Exception {

  public Exceptions(String message) {
    super(message);
  }

  public static String BeliefNotFoundException(Integer id) {
    return "Belief with id '" + id + "' not found";
  }

  public static String BeliefTitleNotFoundException(String title) {
    return "Belief with title '" + title + "' not found";
  }

  public static String BeliefTitleAlreadyExists() {
    return "Belief with given title already exists";
  }

  public static String BeliefIdAlreadyExists() {
    return "Belief with given id already exists";
  }
}
