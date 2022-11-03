package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.presentacion.dto.TrayectoDTO;
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
  @PostMapping("/trayectos/nuevos")
  public ResponseEntity registrarTrayecto(@RequestHeader("Authorization") String idSesion, TrayectoDTO trayectoDTO) throws IOException {
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    Miembro miembroSesion = (Miembro) atributosSesion.get("usuario");
    System.out.println("Obteniendo datos de: " + miembroSesion);

    if (miembroSesion == null) {
      return ResponseEntity.status(404).build();
    }
    //Espacio partida = new Espacio(trayectoDTO.getLatitudPartida(), trayectoDTO.getLongitudPartida(), trayectoDTO.getProvinciaPartida(), trayectoDTO.getMunicipioPartida(),
    //    trayectoDTO.getLocalidadPartida(), trayectoDTO.getDireccionPartida(), trayectoDTO.getNumeroPartida(), trayectoDTO.getCodigoPostalPartida());
    Trayecto trayecto = new Trayecto();
    trayecto.setDiasUtilizados(trayectoDTO.getDiasUtilizados());
    trayecto.setFechaFin(trayectoDTO.getFechaFin());
    trayecto.setFechaInicio(trayectoDTO.getFechaInicio());
    trayecto.setTramos(trayectoDTO.getTramos());

    return ResponseEntity.status(201).body(trayecto);
  }
}
