package com.disenio.mimagrupo06.exception;

public class ReadfileException extends RuntimeException {
  public ReadfileException() {
    super("Error al intentar leer un archivo");
  }
}
