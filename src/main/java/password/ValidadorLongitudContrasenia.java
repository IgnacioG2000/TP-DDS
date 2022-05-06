package password;

import java.util.regex.*;

public class ValidadorLongitudContrasenia extends ValidadorContrasenia {

  public ValidadorLongitudContrasenia(String mensaje) {
    super(mensaje);
  }

  boolean condicionInvalidez(String usuario, String contrasenia) {
    return contrasenia.length() < 8;
  }
  
}
