package password;
import exception.ContraseniaInvalidaException;

public abstract class ValidadorContrasenia {
  private String mensaje;

  private long id;

  public ValidadorContrasenia(String mensaje) {
    this.mensaje = mensaje;
  }

  public ValidadorContrasenia() {}

  void validar(String usuario, String contrasenia) {
    if (this.condicionInvalidez(usuario, contrasenia)) {
      throw new ContraseniaInvalidaException(this.mensaje);
    }
  }

  abstract boolean condicionInvalidez(String usuario, String contrasenia);

}
