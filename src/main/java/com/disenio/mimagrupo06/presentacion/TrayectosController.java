package com.disenio.mimagrupo06.presentacion;

import antlr.ASTNULLType;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.repositorios.RepoEspacio;
import com.disenio.mimagrupo06.repositorios.RepoTramo;
import com.disenio.mimagrupo06.repositorios.RepoTrayecto;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class TrayectosController {

    @Autowired
    RepoTrayecto repoTrayecto;

    @Autowired
    RepoTramo repoTramo;

    @Autowired
    RepoEspacio repoEspacio;

    private final Handlebars handlebars = new Handlebars();
    @GetMapping("/trayectos")
    public ResponseEntity obtenerTrayectos(){
        List<Trayecto> listaTrayectos = repoTrayecto.findAll();
        System.out.println(listaTrayectos);

        return ResponseEntity.status(201).body(listaTrayectos);
    }

    @GetMapping("/espacios")
    public ResponseEntity obtenerTramos(){
        List<Trayecto> listaTrayectos = repoTrayecto.findAll();
        System.out.println(listaTrayectos);

        return ResponseEntity.status(201).body(listaTrayectos);
    }

    @GetMapping("/registrarTrayecto")
    public ResponseEntity<String> registrarTrayecto() throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/Template/eleccionCreacionTrayecto");

        Map<String, Object> model = new HashMap<>();
        //model.put("listamascotas", mascotas);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }


    @GetMapping("/registrarTrayectoNuevo")
    public ResponseEntity<String> registrarTrayectoNuevo() throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/Template/registrarTrayectoNuevo");

        List<Tramo> tramos = repoTramo.findAll();
        List<Espacio> espacios = repoEspacio.findAll();
        Map<String, Object> model = new HashMap<>();
        model.put("tramos", tramos);
        model.put("espacios", espacios);
        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }

    @GetMapping("/registrarTrayectoExistente")
    public ResponseEntity<String> registrarTrayectoExistente() throws IOException {
        //validar accion en capa modelo según roles o usuario asociados al idSesion
        Template template = handlebars.compile("/Template/registrarTrayectoExistente");
        List<Trayecto> listaTrayectos = repoTrayecto.findAll();
        Map<String, Object> model = new HashMap<>();
        model.put("Trayectos", listaTrayectos);

        String html = template.apply(model);

        return ResponseEntity.status(200).body(html);
    }

    //aca necesitamos a los chicos del back xdxd
    @PostMapping("/registrarTrayectoExistente")
    public void recibirTrayectoExistente() throws IOException {
        System.out.println("Estoy recibiendo un trayecto existente");
    }

    @PostMapping("/registrarTrayectoNuevo")
    public void recibirTrayectoNuevo() throws IOException {

        System.out.println("estoy recibiendo un trayecto nuevo");

    }
}
