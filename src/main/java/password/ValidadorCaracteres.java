package password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCaracteres extends ValidadorContrasenia{

  public ValidadorCaracteres(String mensaje) {
    super(mensaje);
  }

  boolean condicionInvalidez(String usuario, String contrasenia) {
    for (int i = 0; i < contrasenia.length(); i++) {
      if (!caracterValidoAscii(contrasenia.codePointAt(i)) || !caracterValidoUNICODE((int) contrasenia.charAt(i))){
        return false;
      }
    }

    return true;
  }


   boolean caracterValidoAscii(int caracter) {
    if(caracter>=32 &&caracter <=126 || caracter>=128 && caracter<=255){
      return true;
    }else{
      return false;
    }
  }

  boolean caracterValidoUNICODE(int caracter){
    if(caracter>=160 && caracter<=9835){
      return true;
    }
    return false;
  }

}

