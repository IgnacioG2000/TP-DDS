import org.junit.jupiter.api.Test;
import repositorios.RepositorioUsuario;
import seguridad.verificadorSesion.VerificadorSesion;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class inicioSesionTest {

   @Test

    public void noSePuedeIniciarSesionDespuesDeVariosLoggeosErroneos() throws NoSuchAlgorithmException {


      RepositorioUsuario.getInstance().agregarUsuario("hola", "shdkjsdhajk");

       VerificadorSesion.getInstance().loggearUsuario("hola", "holis");
       VerificadorSesion.getInstance().loggearUsuario("hola", "holis");
       VerificadorSesion.getInstance().loggearUsuario("hola", "holis");
       VerificadorSesion.getInstance().loggearUsuario("hola", "holis");

       assertNull(VerificadorSesion.getInstance().loggearUsuario("hola", "holis"));


   }


   public void sePuedeIniciaresionSiNoSeCometenMuchosErrores() {

   }



}
