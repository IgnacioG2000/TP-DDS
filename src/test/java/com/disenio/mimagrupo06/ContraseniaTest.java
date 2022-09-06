package com.disenio.mimagrupo06;

import com.disenio.mimagrupo06.seguridad.password.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ContraseniaTest {

  @Test
  public void contaseniaConMasDeUnEspacio(){
    CorrectorDeEspacio validadorDeEspacio = new CorrectorDeEspacio();
    String contraConEspacios = "    hola      como      andas     ";
    assertEquals(" hola como andas ", validadorDeEspacio.contraseniaSinEspacios(contraConEspacios));
  }

  @Test
  public void contaseniaConMasDe8CaracteresOIgual(){
    ValidadorLongitudContrasenia validadorLongitud = new ValidadorLongitudContrasenia();
    String usuario = "pepeElGallo";
    String contrasenia = "tengo_mas_de_8_caracteres";
    assertFalse(validadorLongitud.condicionInvalidez(usuario, contrasenia));
  }

  @Test
  public void contaseniaConMenosDe8Caracteres(){
    ValidadorLongitudContrasenia validadorLongitud = new ValidadorLongitudContrasenia();
    String usuario = "pepitoElPolluelo";
    String contrasenia = "chiqui";
    assertTrue(validadorLongitud.condicionInvalidez(usuario, contrasenia));
  }

  @Test
  public void laContraseniaContieneAlUsuario() {
    ValidadorContraseniaNoContieneUsuario contraNoEnUsuario = new ValidadorContraseniaNoContieneUsuario();
    String usuario = "Nacho Garcia";
    String contraEnUsuario = "Nacho";
    assertFalse(contraNoEnUsuario.condicionInvalidez(usuario, contraEnUsuario));
  }

  @Test
  public void laContraseniaNoCoincideContraConUsuario() {
    ValidadorContraseniaNoContieneUsuario contraNoEnUsuario = new ValidadorContraseniaNoContieneUsuario();
    String usuario = "Nacho Garcia";
    String contraEnUsuario = "Juan";
    assertFalse(contraNoEnUsuario.condicionInvalidez(usuario, contraEnUsuario));
  }

  @Test
  public void laContraseniaEsComun() {
    ValidadorContraseniaComun validadorComun = new ValidadorContraseniaComun();
    assertTrue(validadorComun.condicionInvalidez("usuario", "123456"));
  }

  @Test
  public void contaseniaConCaracteresValidos(){
    String usuario = "Beto";
    ValidadorCaracteres validadorCaracteres = new ValidadorCaracteres();
    String contraConCaracteresASCII = "estoNoEsUnaContrasenia";
    assertFalse(validadorCaracteres.condicionInvalidez(usuario, contraConCaracteresASCII));
  }

  @Test
  public void contaseniaEsCompletamenteValida(){
    String usuario = "Beto";
    ValidadorDeMetricas validadorDeMetricas = new ValidadorDeMetricas();
    String contrasenia = "ConTra    Muy   Bu3na";
    assertEquals("ConTra Muy Bu3na",validadorDeMetricas.validarTodo(usuario, contrasenia));
  }
}
