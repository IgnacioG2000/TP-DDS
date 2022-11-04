package com.disenio.mimagrupo06.presentacion;


import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.presentacion.dto.LoginRequest;
import com.disenio.mimagrupo06.presentacion.dto.LoginResponse;
import com.disenio.mimagrupo06.repositorios.RepoMiembro;
import com.disenio.mimagrupo06.repositorios.RepoPersona;
import com.disenio.mimagrupo06.repositorios.RepoUsuario;
import com.disenio.mimagrupo06.repositorios.RepositorioUsuario;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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
    public LoginController() {
    }

    /*
    @GetMapping("/login")
    public ResponseEntity<String> login() throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion
        //Template template = handlebars.compile("/templates/login");

        Map<String, Object> model = new HashMap<>();
        //model.put("listamascotas", mascotas);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }
    */
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

        Usuario usuario = repoUsuario.findByUsuarioAndContraseniaHasheada(nombreUsuario, contraseniaHasheada);
        Persona personaSesion = repoPersona.findByUsuario(usuario);
        List<Miembro> miembrosDeSesion = repoMiembro.findAllByPersona(personaSesion).stream().collect(Collectors.toList());
        System.out.println("cantidad de miembros asociados al usuario ingresado: " + miembrosDeSesion.size());
        List<String> nombresAreasDelMiembro = miembrosDeSesion.stream().map(miembro -> miembro.getArea().getNombre()).collect(Collectors.toList());

        if (usuario != null) {

            SesionManager sesionManager = SesionManager.get();
            System.out.println(sesionManager);
            String idSesion = sesionManager.crearSesion("usuario",usuario);
            return new LoginResponse(idSesion, nombresAreasDelMiembro);
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
        //SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[]{1, 2, 4, 8, 16, 32, 64, (byte) 128};
        //secureRandom.nextBytes(salt); // proxima semilla
        return salt;
    }

}