package seguridad.password;

public class ValidadorDeEspacio {

  public String contraseniaSinEspacios(String contrasenia) {
      //aca se usaria la logica con la cual les sacaron los espacios a las contrasenias
      return contrasenia.replaceAll("\\s{2,}", " ");
    }
}
