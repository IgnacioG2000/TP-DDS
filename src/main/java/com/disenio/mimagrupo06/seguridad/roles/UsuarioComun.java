package com.disenio.mimagrupo06.seguridad.roles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.security.NoSuchAlgorithmException;

@Entity
@DiscriminatorValue("1")
public class UsuarioComun extends Usuario{

  public UsuarioComun(String usuario, String contrasenia) throws NoSuchAlgorithmException {
    super(usuario, contrasenia);
  }

  public UsuarioComun() {

  }
}
