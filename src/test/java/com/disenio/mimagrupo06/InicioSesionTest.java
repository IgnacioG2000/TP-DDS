package com.disenio.mimagrupo06;

import com.disenio.mimagrupo06.repositorios.RepositorioUsuario;
import com.disenio.mimagrupo06.seguridad.verificadorSesion.VerificadorSesion;
import org.junit.jupiter.api.Test;


import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class InicioSesionTest {

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
