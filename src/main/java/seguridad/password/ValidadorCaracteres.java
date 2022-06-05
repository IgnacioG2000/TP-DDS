package seguridad.password;

public class ValidadorCaracteres extends ValidadorContrasenia{

  public ValidadorCaracteres(String mensaje) {
    super(mensaje);
  }

  public boolean condicionInvalidez(String usuario, String contrasenia) {
    for (int i = 0; i < contrasenia.length(); i++) {
      if (!caracterEsValido(contrasenia, i)){
        return true;
      }
    }
    return false;
  }

  private boolean caracterEsValido(String contrasenia, int i) {
    return this.caracterValidoAscii(contrasenia.codePointAt(i)) || this.caracterValidoUNICODE((int) contrasenia.charAt(i));
  }

  boolean caracterValidoAscii(int caracter) {
     return caracter >= 32 && caracter <= 126 || caracter >= 128 && caracter <= 255;
  }

  boolean caracterValidoUNICODE(int caracter){
    return caracter >= 160 && caracter <= 9835;
  }
}

