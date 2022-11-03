package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.presentacion.dto.TrayectoDTO;
import com.disenio.mimagrupo06.repositorios.*;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
  public ResponseEntity registrarTrayecto(@RequestHeader("Authorization") String idSesion, TrayectoDTO trayectoDTO) throws IOException {
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    Usuario usuarioSesion = (Usuario) atributosSesion.get("usuario");
    Persona personaSesion = repoPersona.findByUsuario(usuarioSesion);
    Miembro miembroSesion = repoMiembro.findByPersona(personaSesion);

    System.out.println("Obteniendo datos de: " + miembroSesion);

    if (miembroSesion == null) {
      return ResponseEntity.status(404).build();
    }
    //Espacio partida = new Espacio(trayectoDTO.getLatitudPartida(), trayectoDTO.getLongitudPartida(), trayectoDTO.getProvinciaPartida(), trayectoDTO.getMunicipioPartida(),
        //trayectoDTO.getLocalidadPartida(), trayectoDTO.getDireccionPartida(), trayectoDTO.getNumeroPartida(), trayectoDTO.getCodigoPostalPartida());
    Trayecto trayecto = new Trayecto();
    trayecto.setDiasUtilizados(trayectoDTO.getDiasUtilizados());
    trayecto.setFechaFin(trayectoDTO.getFechaFin());
    trayecto.setFechaInicio(trayectoDTO.getFechaInicio());
    trayecto.setTramos(trayectoDTO.getTramos());
    //System.out.println("Obteniendo datos de trayecto.");
    //System.out.println("trayecto dias utilizados: " + );
    //TODO COMPLETAR EL TRAYECTO CON EL ESPACIO DE PARTIDA Y LELGADA
    repoTrayecto.save(trayecto);
    miembroSesion.getArea().agregarVinculacion(trayecto);
    repoMiembro.save(miembroSesion);
    return ResponseEntity.status(201).body(trayecto);
  }
}
