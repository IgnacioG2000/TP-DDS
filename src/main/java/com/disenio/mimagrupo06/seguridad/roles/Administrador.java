package com.disenio.mimagrupo06.seguridad.roles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.security.NoSuchAlgorithmException;

@Entity
@DiscriminatorValue("2")
public class Administrador extends Usuario {

  public Administrador(String usuario, String contrasenia) throws NoSuchAlgorithmException {
    super(usuario, contrasenia);
    setTipoUsuario(2);
  }

  public Administrador() {

  }
}
