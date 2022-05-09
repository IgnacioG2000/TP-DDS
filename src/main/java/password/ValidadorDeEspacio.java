package password;

public class ValidadorDeEspacio extends ValidadorContrasenia {

  public ValidadorDeEspacio(String mensaje) {
    super(mensaje);
  }

  boolean condicionInvalidez(String usuario, String contrasenia) {
    int contadorEspaciosSeguidos = 0;
    for(int i = 0; i < contrasenia.length(); i++) {
      if(esEspacio(contrasenia, i)) {
        contadorEspaciosSeguidos++;

      }
     if(contadorEspaciosSeguidos >= 2) {
        return true;
      }
      contadorEspaciosSeguidos = 0;

    }
      return false;
  }

  private boolean esEspacio(String contrasenia, int i) {
    return contrasenia.codePointAt(i) == 32;
    //El 32 en DECIMAL es un espacio
  }
}
