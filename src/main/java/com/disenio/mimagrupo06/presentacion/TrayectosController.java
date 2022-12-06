package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.*;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte.*;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.presentacion.dto.*;
import com.disenio.mimagrupo06.presentacion.service.TrayectoService;
import com.disenio.mimagrupo06.repositorios.*;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class TrayectosController {
    @Autowired
    RepoArea repoArea;
    @Autowired
    RepoPersona repoPersona;
    @Autowired
    RepoTrayecto repoTrayecto;
    @Autowired
    RepoTramos repoTramos;
    @Autowired
    RepoEspacio repoEspacio;
    @Autowired
    RepoMiembro repoMiembro;
    @Autowired
    RepoMedioTransporte repoMedioTransporte;
    @Autowired
    TrayectoService trayectoService;

    private final Handlebars handlebars = new Handlebars();

    @GetMapping("/trayectos")
    public ResponseEntity obtenerTrayectos(@RequestHeader("Authorization") String idSesion){
        Iterable<Trayecto> listaTrayectos = repoTrayecto.findAll();
        return ResponseEntity.status(201).body(listaTrayectos);
    }

    @GetMapping("/registrarTrayecto")
    public ResponseEntity<String> registrarTrayecto(@RequestHeader("Authorization") String idSesion) throws IOException {
        String html = this.trayectoService.registrarTrayecto(idSesion);
        return ResponseEntity.status(200).body(html);
    }

    @GetMapping("/registrarTrayectoNuevo")
    public ResponseEntity<String> registrarTrayectoNuevo() throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/Template/registrarTrayectoNuevo");

        Map<String, Object> model = new HashMap<>();
        //model.put("listamascotas", mascotas);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }

    @GetMapping("/registrarTrayectoExistente")
    public ResponseEntity<String> registrarTrayectoExistente() throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/Template/registrarTrayectoExistente");

        Map<String, Object> model = new HashMap<>();
        //model.put("listamascotas", mascotas);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }


    @PostMapping("/registrarTrayectoExistente")
    public void recibirTrayectoExistente(@RequestHeader("Authorization") String idSesion,@RequestBody TrayectoExistenteDTO trayectoExistenteDTO) throws IOException {
        this.trayectoService.registrarTrayectoExistente(idSesion,trayectoExistenteDTO);
    }
//Queda pasamos porque en realidad acá tendríamos que tener lo de responseEntity para enviar como salió el request, pero despues lo vemos
    @PostMapping("/registrarTrayectoNuevo")
    public void recibirTrayectoNuevo(@RequestHeader("Authorization") String idSesion,@RequestBody TrayectoNuevoDTO trayectoNuevoDTO) throws IOException {
        this.trayectoService.registrarTrayectoNuevo(idSesion,trayectoNuevoDTO);
    }

    private boolean mismaArea(Miembro miembro, String nombreArea){
        return miembro.getArea().getNombre().equals(nombreArea);
    }
}
