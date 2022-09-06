package com.disenio.mimagrupo06.seguridad.password;

import java.util.Arrays;
import java.util.List;

public class ValidadorDeMetricas {

  private final List<ValidadorContrasenia> validadores;

  public ValidadorDeMetricas() {
    this.validadores = Arrays.asList(new ValidadorLongitudContrasenia(), new ValidadorContraseniaComun(),
        new ValidadorContraseniaNoContieneUsuario(), new ValidadorCaracteres());
  }

  public String validarTodo(String usuario, String contrasenia) {

    CorrectorDeEspacio validadorEspacio = new CorrectorDeEspacio();

    //primero sacamos la contrasenia sin espacios
    String contraseniaCompactada =  validadorEspacio.contraseniaSinEspacios(contrasenia);

    //luego la validamos con los validadores necesarios
    validadores.forEach(validador -> validador.validar(usuario, contraseniaCompactada));

    //se devuelve la contraseniaCompactada para el hasheado de la misma
    return contraseniaCompactada;
  }

}
