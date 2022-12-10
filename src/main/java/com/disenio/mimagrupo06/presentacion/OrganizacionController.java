package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.repositorios.RepoArea;
import com.disenio.mimagrupo06.repositorios.RepoMiembro;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.repositorios.RepoUsuario;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@CrossOrigin
public class OrganizacionController {
  @Autowired
  private RepoOrganizacion repoOrganizacion;
  @Autowired
  private RepoUsuario repoUsuario;
  @Autowired
  private RepoMiembro repoMiembro;
  @Autowired
  private RepoArea repoArea;
  private final Handlebars handlebars = new Handlebars();

  public OrganizacionController(){}


  @GetMapping("/gestionar/vinculaciones")
  public ResponseEntity<String> miembrosPendientes(@RequestParam("Authorization") String idSesion) throws IOException{
    Template template = handlebars.compile("/Template/aceptarVinculacionTrabajador");

      Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

      UsuarioOrganizacion usuarioOrganizacionSesion = (UsuarioOrganizacion) atributosSesion.get("usuario");
      System.out.println("usuario organizacion:" + usuarioOrganizacionSesion);
      Organizacion unaOrganizacion = repoOrganizacion.findByUsuarioOrganizacion(usuarioOrganizacionSesion);
      System.out.println("organizacion" + unaOrganizacion);

      Collection<Area> areas = repoOrganizacion.findById(unaOrganizacion.getId()).get().getSectores();

      System.out.println("cant areas" + areas.size());

      List<Collection<Miembro>> miembrosPendientes = areas.stream().map(area-> area.getMiembrosPend()).collect(toList());

      System.out.println("cant mP" + miembrosPendientes.size());

      List<Miembro> miembrosPendientesLista = new ArrayList<>();

      for (int i = 0; i < miembrosPendientes.size(); i++){
        miembrosPendientesLista.addAll(miembrosPendientes.get(i));
      }

    System.out.println("cant mPa" + miembrosPendientesLista.size());



    Map<String, Object> model = new HashMap<>();
    model.put("MiembrosPendientes", miembrosPendientesLista);
    String html = template.apply(model);


    return ResponseEntity.status(200).body(html);


  }


  @PostMapping("/vincular/{id}")
  public ResponseEntity<?> vincular(@RequestHeader("Authorization") String idSesion, @PathVariable("id") String idMiembro){
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    UsuarioOrganizacion usuarioOrganizacionSesion = (UsuarioOrganizacion) atributosSesion.get("usuario");
    System.out.println("usuario organizacion:" + usuarioOrganizacionSesion);
    Organizacion unaOrganizacion = repoOrganizacion.findByUsuarioOrganizacion(usuarioOrganizacionSesion);
    System.out.println("organizacion" + unaOrganizacion);
    Collection<Area> areas = repoOrganizacion.findById(unaOrganizacion.getId()).get().getSectores();

    long id = Long.parseLong(idMiembro);

    Miembro miembroAgestionar = repoMiembro.findById(id).get();

    Area areaDelMiembro = areas.stream().filter(area -> area.getMiembrosPend().contains(miembroAgestionar)).findFirst().get();
    areaDelMiembro.vincularTrabajador(miembroAgestionar);
    repoArea.save(areaDelMiembro);
    miembroAgestionar.setAreaPendiente(null);
    miembroAgestionar.setArea(areaDelMiembro);
    repoMiembro.save(miembroAgestionar);


    return new ResponseEntity<Object>("Trabajador vinculado correctamente", HttpStatus.OK);
  }

  @PostMapping("/rechazar/{id}")
  public ResponseEntity<?> rechazar(@RequestHeader("Authorization") String idSesion, @PathVariable("id") String idMiembro){
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    UsuarioOrganizacion usuarioOrganizacionSesion = (UsuarioOrganizacion) atributosSesion.get("usuario");
    System.out.println("usuario organizacion:" + usuarioOrganizacionSesion);
    Organizacion unaOrganizacion = repoOrganizacion.findByUsuarioOrganizacion(usuarioOrganizacionSesion);
    System.out.println("organizacion" + unaOrganizacion);
    Collection<Area> areas = repoOrganizacion.findById(unaOrganizacion.getId()).get().getSectores();

    long id = Long.parseLong(idMiembro);

    Miembro miembroAgestionar = repoMiembro.findById(id).get();

    Area areaDelMiembro = areas.stream().filter(area -> area.getMiembrosPend().contains(miembroAgestionar)).findFirst().get();
    areaDelMiembro.rechazarTrabajador(miembroAgestionar);
    repoArea.save(areaDelMiembro);
    miembroAgestionar.setAreaPendiente(null);
    repoMiembro.save(miembroAgestionar);


    return new ResponseEntity<Object>("Trabajador rechazado correctamente", HttpStatus.OK);
  }


  @GetMapping("/trayectosPendientes")
  public ResponseEntity<Collection<Trayecto>> trayectosPendientes(@RequestHeader("Authorization") String idSesion) throws IOException {
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    Organizacion organizacionSesion = (Organizacion) atributosSesion.get("usuario");
    System.out.println("Obteniendo datos de: " + organizacionSesion);

    if (organizacionSesion == null) {
      return ResponseEntity.status(404).build();
    }

    Collection<Area> areas = repoOrganizacion.findById(organizacionSesion.getId()).get().getSectores();
    List<Collection<Trayecto>> trayectosPendientes = areas.stream().map(area-> area.getTrayectosPendientes()).collect(toList());
    Collection<Trayecto> trayectosPendientesLista=new ArrayList<>();
    for (int i = 0;i<= trayectosPendientes.size();i++){
      Collection<Trayecto> trayectos= trayectosPendientes.get(i);
      trayectosPendientesLista.addAll(trayectos);
    }

    return ResponseEntity.status(200).body(trayectosPendientesLista);
  }
/*
  @GetMapping("/ace")
  public ResponseEntity<Collection<Trayecto>> trayectosPendientes(@RequestHeader("Authorization") String idSesion) throws IOException {

    return ResponseEntity.status(200).body(trayectosPendientesLista);
  }*/

}
