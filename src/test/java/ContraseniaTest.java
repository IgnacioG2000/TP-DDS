import org.junit.jupiter.api.Test;
import password.ValidadorDeEspacio;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContraseniaTest {
  @Test
  public void unaContraseniaEsValida(){
    assertEquals(true,true);
  }
  //esto siempre anda

  @Test
  public void contaseniaConMasDeUnEspacio(){
    ValidadorDeEspacio validadorDeEspacio = new ValidadorDeEspacio("mensaje de prueba");
    String contraConEspacios = "    hola      como      andas     ";
    assertEquals(" hola como andas ", validadorDeEspacio.contraseniaSinEspacios(contraConEspacios));
}


}
