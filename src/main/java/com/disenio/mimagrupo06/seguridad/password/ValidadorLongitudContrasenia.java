package com.disenio.mimagrupo06.seguridad.password;

public class ValidadorLongitudContrasenia extends ValidadorContrasenia {

  public ValidadorLongitudContrasenia() {
    this.mensajeError = " porque la contrasenia es demasiado corta. ";
  }

  @Override
  public boolean condicionInvalidez(String usuario, String contrasenia) {
    return contrasenia.length() < 8;
  }
  
}
