package com.disenio.mimagrupo06.seguridad.password;

public class ValidadorContraseniaNoContieneUsuario extends ValidadorContrasenia {

  public ValidadorContraseniaNoContieneUsuario() {
  this.mensajeError = " la contrasenia contiene al usuario";
  }

  @Override
  public boolean condicionInvalidez(String usuario, String contrasenia) {
    return contrasenia.contains(usuario);
  }

}