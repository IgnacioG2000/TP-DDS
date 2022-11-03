package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.presentacion.dto.TrayectoDTO;
import com.disenio.mimagrupo06.repositorios.*;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@RestController
@CrossOrigin
public class MiembroController {
  @Autowired
  RepoArea repoArea;
  @Autowired
  RepoPersona repoPersona;
  @Autowired
  RepoMiembro repoMiembro;
  @Autowired
  RepoTrayecto repoTrayecto;

  @PostMapping("/trayectos/nuevos")
  public ResponseEntity registrarTrayecto(@RequestHeader("Authorization") String idSesion,@RequestBody TrayectoDTO trayectoDTO) throws IOException {
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    Usuario usuarioSesion = (Usuario) atributosSesion.get("usuario");
    Persona personaSesion = repoPersona.findByUsuario(usuarioSesion);
    Miembro miembroSesion = repoMiembro.findByPersona(personaSesion);

    System.out.println("Obteniendo datos de: " + miembroSesion);
    System.out.println("TRAYECTO: " + trayectoDTO.getDireccionLlegada() + trayectoDTO.getDireccionPartida()
            + trayectoDTO.getTramos() + trayectoDTO.getFechaFin() + trayectoDTO.getFechaInicio());

    if (miembroSesion == null) {
      return ResponseEntity.status(404).build();
    }
   // Espacio partida = new Espacio(trayectoDTO.getLatitudPartida(), trayectoDTO.getLongitudPartida(), trayectoDTO.getProvinciaPartida(), trayectoDTO.getMunicipioPartida(),
     //       trayectoDTO.getLocalidadPartida(), trayectoDTO.getDireccionPartida(), trayectoDTO.getNumeroPartida(), trayectoDTO.getCodigoPostalPartida());
    Trayecto trayecto = new Trayecto();
    trayecto.setDiasUtilizados(trayectoDTO.getDiasUtilizados());
    trayecto.setFechaFin(trayectoDTO.getFechaFin());
    trayecto.setFechaInicio(trayectoDTO.getFechaInicio());
    trayecto.setTramos(trayectoDTO.getTramos());
    System.out.println("Obteniendo datos de trayecto.");
    System.out.println("trayecto dias utilizados: " +trayectoDTO.getDiasUtilizados() );
    //TODO COMPLETAR EL TRAYECTO CON EL ESPACIO DE PARTIDA Y LELGADA
    repoTrayecto.save(trayecto);
    miembroSesion.getArea().agregarVinculacion(trayecto);
    repoMiembro.save(miembroSesion);
    return ResponseEntity.status(201).body(trayecto);
  }


  @PostMapping("/trayectos/nuevos2")
  public ResponseEntity registrarTrayecto2(@RequestHeader("Authorization") String idSesion, @RequestBody String fechaFin) throws IOException {
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    Usuario usuarioSesion = (Usuario) atributosSesion.get("usuario");
    Persona personaSesion = repoPersona.findByUsuario(usuarioSesion);
    Miembro miembroSesion = repoMiembro.findByPersona(personaSesion);

    System.out.println("Obteniendo datos de: " + miembroSesion);

    System.out.println("fechas " + fechaFin );

    if (miembroSesion == null) {
      return ResponseEntity.status(404).build();
    }
    //Espacio partida = new Espacio(trayectoDTO.getLatitudPartida(), trayectoDTO.getLongitudPartida(), trayectoDTO.getProvinciaPartida(), trayectoDTO.getMunicipioPartida(),
    //trayectoDTO.getLocalidadPartida(), trayectoDTO.getDireccionPartida(), trayectoDTO.getNumeroPartida(), trayectoDTO.getCodigoPostalPartida());
    Trayecto trayecto = new Trayecto();
    //trayecto.setDiasUtilizados(Integer.parseInt(diasUtilizados));
    trayecto.setFechaFin(LocalDate.parse(fechaFin));
    //trayecto.setFechaInicio(LocalDate.parse(fechaInicio));
    //trayecto.setTramos(trayectoDTO.getTramos());
    //System.out.println("Obteniendo datos de trayecto.");
    //System.out.println("trayecto dias utilizados: " + );
    //TODO COMPLETAR EL TRAYECTO CON EL ESPACIO DE PARTIDA Y LELGADA
    repoTrayecto.save(trayecto);
    miembroSesion.getArea().agregarVinculacion(trayecto);
    repoMiembro.save(miembroSesion);
    return ResponseEntity.status(201).body(trayecto);
  }
}
