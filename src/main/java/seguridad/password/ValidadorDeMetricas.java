package seguridad.password;

import java.util.Arrays;
import java.util.List;

public class ValidadorDeMetricas{

  private final List<ValidadorContrasenia> validadores;

  public ValidadorDeMetricas() {
    this.validadores = Arrays.asList(new ValidadorLongitudContrasenia("La contraseña es demasiado corta. Por favor, ingrese una contraseña" +
        " de más de 8 caracteres."), new ValidadorContraseniaComun("La contrasenia pertenece a las 10000 más usadas. Por favor," +
        " ingrese otra contraseña"), new ValidadorContraseniaNoContieneUsuario("La contraseña contiene al usuario. Por favor," +
        " ingrese otra contraseña."), new ValidadorCaracteres("La contrasenia no es un caracter valido ASCII o UNICODE"));
  }


  public String validarTodo(String usuario, String contrasenia) {

    ValidadorDeEspacio validadorEspacio = new ValidadorDeEspacio("");

    //primero sacamos la contrasenia sin espacios
    String contraseniaCompactada =  validadorEspacio.contraseniaSinEspacios(contrasenia);

    //luego la validamos con los validadores necesarios
    validadores.forEach(validador -> validador.validar(usuario, contraseniaCompactada));

    //se devuelve la contraseniaCompactada para el hasheado de la misma
    return contraseniaCompactada;
  }

}
