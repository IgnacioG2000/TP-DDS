package com.disenio.mimagrupo06.seguridad.password;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.exception.ReadfileException;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ValidadorContraseniaComun extends ValidadorContrasenia {

  public ValidadorContraseniaComun() {
    this.mensajeError = "La contrasenia pertenece a las 10000 más usadas. Por favor," +
        " ingrese otra contraseña";
  }

  @Override
   public boolean  condicionInvalidez(String usuario, String contrasenia) {
    Path path = Paths.get("src/main/resources/10k-most-common.txt");
    Stream<String> stream;
    try {
      stream = Files.lines(path);
      return stream.anyMatch(palabraComun -> palabraComun.equals(contrasenia));
    } catch (IOException e) {
      throw new ReadfileException();
    }
  }


}
