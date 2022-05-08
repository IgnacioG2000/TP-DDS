package password;

import domain.roles.Usuario;

import java.util.Arrays;
import java.util.List;

public class ValidadorDeMetricas{

  private final List<ValidadorContrasenia> validadores;

  public ValidadorDeMetricas() {
    this.validadores = Arrays.asList(new ValidadorContraseniaComun("La contrasenia "
        + "pertenece a las 10000 más usadas. Por favor, ingrese otra contraseña"), new ValidadorLongitudContrasenia("La "
        + "contraseña es demasiado corta. Por favor, ingrese una contraseña de más de 8 caracteres."), new ValidadorContraseniaNoContieneUsuario(
 "La contraseña contiene al usuario. Por favor, ingrese otra contraseña."), new ValidadorCaracteres("La contrasenia no es un caracter" +
        "valido ASCII o UNICODE"));
  }


  public String validar(String usuario, String contrasenia) {

    ValidadorDeEspacio validadorEspacio = new ValidadorDeEspacio("");

    //String contraseniaCompactada =  validadorEspacio(contrasenia);
    String contraseniaCompactada = "";
    validadores.forEach(validador -> validador.validar(usuario, contraseniaCompactada));

    //hasheado de contrasenia una vez validada
    return contraseniaCompactada;
  }

}
