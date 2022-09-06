package com.disenio.mimagrupo06.seguridad.verificadorSesion;

import com.disenio.mimagrupo06.repositorios.RepositorioUsuario;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class VerificadorSesion {

    private static final VerificadorSesion INSTANCE = new VerificadorSesion();

    public static VerificadorSesion getInstance() {
        return INSTANCE;
    }

    HashMap<String, IntentosInicioSesion> intentoUsuarios = new HashMap<>();

   public Usuario loggearUsuario(String usuario, String contrasenia) throws NoSuchAlgorithmException {


       if (!intentoUsuarios.containsKey(usuario)){
           intentoUsuarios.put(usuario, new IntentosInicioSesion(usuario));
       }

       IntentosInicioSesion usuarioIntentos = intentoUsuarios.get(usuario);

       if(usuarioIntentos.puedeIntentarLoggearse()) {
           if(RepositorioUsuario.getInstance().contraseniaCoincide(usuario, contrasenia)) {
               //retorno el usuario loggeado
               intentoUsuarios.remove(usuario);
               return new Usuario(usuario, contrasenia);
           }
           else {
               usuarioIntentos.aumentarIntentos();

           }
       }
       return null;
   }
}
