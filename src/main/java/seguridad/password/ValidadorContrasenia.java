package seguridad.password;
import exception.ContraseniaInvalidaException;

public abstract class ValidadorContrasenia {

  protected String mensajeError;

  void validar(String usuario, String contrasenia) {
    if (this.condicionInvalidez(usuario, contrasenia)) {
      throw new ContraseniaInvalidaException("La contrasenia es invalida porque " + this.mensajeError);
    }
  }

  public abstract boolean condicionInvalidez(String usuario, String contrasenia);

}
