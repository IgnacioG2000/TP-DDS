package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.repositorios.RepoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class OrganizacionController {
  //@Autowired
  private RepoOrganizacion repoOrganizacion;
  @Autowired
  private RepoUsuario repoUsuario;

  @GetMapping("/trayectosPendientes")
  public ResponseEntity<Collection<Trayecto>> trayectosPendientes(@RequestHeader("Authorization") String idSesion) throws IOException {
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    Organizacion organizacionSesion = (Organizacion) atributosSesion.get("usuario");
    System.out.println("Obteniendo datos de: " + organizacionSesion);

    if (organizacionSesion == null) {
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
/*
  @GetMapping("/ace")
  public ResponseEntity<Collection<Trayecto>> trayectosPendientes(@RequestHeader("Authorization") String idSesion) throws IOException {

    return ResponseEntity.status(200).body(trayectosPendientesLista);
  }*/

}
