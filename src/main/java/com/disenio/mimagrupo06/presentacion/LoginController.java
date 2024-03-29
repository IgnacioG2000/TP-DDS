package com.disenio.mimagrupo06.presentacion;


import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.presentacion.dto.LoginRequest;
import com.disenio.mimagrupo06.presentacion.dto.LoginResponse;
import com.disenio.mimagrupo06.repositorios.RepoMiembro;
import com.disenio.mimagrupo06.repositorios.RepoPersona;
import com.disenio.mimagrupo06.repositorios.RepoUsuario;
import com.disenio.mimagrupo06.repositorios.RepositorioUsuario;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private RepoUsuario repoUsuario;
    @Autowired
    RepoPersona repoPersona;
    @Autowired
    RepoMiembro repoMiembro;

    private final Handlebars handlebars = new Handlebars();

    public LoginController() {
    }


    @GetMapping("/login")
    public ResponseEntity<String> login() throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/Template/login");

        Map<String, Object> model = new HashMap<>();
        //model.put("listamascotas", mascotas);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }

    @GetMapping("/home")
    public ResponseEntity<String> home(@RequestParam("sesion") String idSesion) throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion

        Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

        Usuario usuario = (Usuario) atributosSesion.get("usuario");
        System.out.println("Obteniendo datos de: " + usuario);

        int tipoUsuario = repoUsuario.findByUsuario(usuario.getUsuario()).getTipoUsuario();
        System.out.println("Tipo usuario: " + tipoUsuario);
        Template template = devolverTemplateSegunTipoUsuario(tipoUsuario);

        Map<String, Object> model = new HashMap<>();
        //model.put("listamascotas", mascotas);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }

    private Template devolverTemplateSegunTipoUsuario(int tipoUsuario) throws IOException {

        if(tipoUsuario == 1) {
            return handlebars.compile("/Template/homeMiembro");
        } else if (tipoUsuario == 3) {
            return handlebars.compile("/Template/homeAgenteSectorial");
        } else  {
            return handlebars.compile("/Template/homeOrganizacion");
        }
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {

        //validamos user/pass y buscamos datos de ese usuario para agregar en la sesión

        //Persona persona = repoPersonas.findByUserAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        //System.out.println("Login: " + loginRequest);
        //System.out.println("Login: " + persona);

        // TODO: Ir a la base de datos y validar usaurio y contraseña

        String nombreUsuario = loginRequest.getUsername();
        String contrasenna = loginRequest.getPassword();

        System.out.println(nombreUsuario);
        System.out.println(contrasenna);
        System.out.println(loginRequest);

       String contraseniaHasheada = this.generarHash(contrasenna, this.getSalt());

        Usuario usuario = repoUsuario.findByUsuarioAndContraseniaHasheada(nombreUsuario, contraseniaHasheada);/*
        Persona personaSesion = repoPersona.findByUsuario(usuario);
        List<Miembro> miembrosDeSesion = repoMiembro.findAllByPersona(personaSesion).stream().collect(Collectors.toList());
        System.out.println("cantidad de miembros asociados al usuario ingresado: " + miembrosDeSesion.size());
        List<String> nombresAreasDelMiembro = miembrosDeSesion.stream().map(miembro -> miembro.getArea().getNombre()).collect(Collectors.toList());
*/
        if (usuario != null) {

            SesionManager sesionManager = SesionManager.get();
            System.out.println(sesionManager);
            String idSesion = sesionManager.crearSesion("usuario",usuario);
            return new LoginResponse(idSesion);
        }

//        sesionManager.agregarAtributo("fechaInicio", new Date());
//        sesionManager.agregarAtributo("rol", repoRoles.getByUser(idUser));

        //acá hay que ver el tema de que se controle la cantidad de veces que trata de iniciar sesión un usuario
        throw new Exception("Usuario no valido, por favor reintentar.");
    }



    public static String generarHash(String contrasenia,byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        byte[] hash = digest.digest(contrasenia.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public static byte[] getSalt()  {
        byte[] salt = new byte[]{1, 2, 4, 8, 16, 32, 64, (byte) 128};
        //secureRandom.nextBytes(salt); // proxima semilla
        return salt;
    }

}