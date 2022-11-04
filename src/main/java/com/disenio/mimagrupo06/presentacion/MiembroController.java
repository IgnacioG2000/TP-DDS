package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Hogar;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.presentacion.dto.TrayectoDTO;
import com.disenio.mimagrupo06.repositorios.*;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class MiembroController {
  @Autowired
  RepoArea repoArea;
  @Autowired
  RepoPersona repoPersona;
  @Autowired
  RepoTrayecto repoTrayecto;
  @Autowired
  RepoEspacio repoEspacio;
  @Autowired
  RepoMiembro repoMiembro;
/*
  @GetMapping("/trayectos/nuevos")
  public ResponseEntity<Collection<Trayecto>> trayectosPendientes(@RequestHeader("Authorization") String idSesion) throws IOException {
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    Usuario usuarioSesion = (Usuario) atributosSesion.get("usuario");
    Persona personaSesion = repoPersona.findByUsuario(usuarioSesion);

    if (usuarioSesion == null) {
      return ResponseEntity.status(404).build();
    }

    Collection<Area> areas = repoOrganizacion.findById(organizacionSesion.getId()).get().getSectores();
    List<Collection<Trayecto>> trayectosPendientes = areas.stream().map(area-> area.getTrayectosPendientes()).collect(Collectors.toList());
    Collection<Trayecto> trayectosPendientesLista=new ArrayList<>();
    for (int i = 0;i<= trayectosPendientes.size();i++){
      Collection<Trayecto> trayectos= trayectosPendientes.get(i);
      trayectosPendientesLista.addAll(trayectos);
    }

    return ResponseEntity.status(200).body(trayectosPendientesLista);
  }
*/
  @PostMapping("/trayectos/nuevos")
  public ResponseEntity registrarTrayecto(@RequestHeader("Authorization") String idSesion, @RequestBody TrayectoDTO trayectoDTO) throws IOException {

    Miembro miembroSesion = SesionManager.get().encontrarMiembro(idSesion, trayectoDTO.getNombreArea());
    if (miembroSesion == null) {
      return ResponseEntity.status(404).build();
    }

    Trayecto trayecto = new Trayecto();
    List<Espacio> espacios = repoEspacio.findAll();
    Espacio partida = espacios.stream().filter(espacio -> espacio.equals(trayectoDTO.getEspacioPartida())).collect(Collectors.toList()).get(0);
    Espacio llegada = espacios.stream().filter(espacio -> espacio.equals(trayectoDTO.getEspacioLlegada())).collect(Collectors.toList()).get(0);
    trayecto.setPartida(partida);
    trayecto.setLlegada(llegada);

    trayecto.setDiasUtilizados(trayectoDTO.getDiasUtilizados());
    trayecto.setFechaFin(trayectoDTO.getFechaFin());
    trayecto.setFechaInicio(trayectoDTO.getFechaInicio());
    trayecto.setTramos(trayectoDTO.getTramos());
    repoTrayecto.save(trayecto);
    miembroSesion.getArea().agregarVinculacion(trayecto);
    repoMiembro.save(miembroSesion);
    return ResponseEntity.status(201).body(trayecto);
  }



}
