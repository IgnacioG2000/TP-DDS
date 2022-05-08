package password;

public class ValidadorDeEspacio extends ValidadorContrasenia {

  public ValidadorDeEspacio(String mensaje) {
    super(mensaje);
  }

  boolean condicionInvalidez(String usuario, String contrasenia) {

    return contrasenia.contains(usuario);
  }
}
