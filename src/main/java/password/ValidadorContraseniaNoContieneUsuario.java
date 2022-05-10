package password;

public class ValidadorContraseniaNoContieneUsuario extends ValidadorContrasenia {

  public ValidadorContraseniaNoContieneUsuario(String mensaje) {
    super(mensaje);
  }

  @Override
  public boolean condicionInvalidez(String usuario, String contrasenia) {
    return contrasenia.contains(usuario);
  }

}