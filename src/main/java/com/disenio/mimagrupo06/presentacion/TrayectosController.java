package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.repositorios.RepoTrayecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TrayectosController {

    @Autowired
    RepoTrayecto repoTrayecto;

    @GetMapping("/trayectos")
    public ResponseEntity obtenerTrayectos(@RequestHeader("Authorization") String idSesion){
        Iterable<Trayecto> listaTrayectos = repoTrayecto.findAll();
        return ResponseEntity.status(201).body(listaTrayectos);
    }
}
