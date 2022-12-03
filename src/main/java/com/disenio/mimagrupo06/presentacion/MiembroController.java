package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.presentacion.dto.TrayectoNuevoDTO;
import com.disenio.mimagrupo06.repositorios.*;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
  /*@PostMapping("/trayectos/nuevos")
  public ResponseEntity registrarTrayecto(@RequestHeader("Authorization") String idSesion, @RequestBody TrayectoNuevoDTO trayectoDTO) throws IOException {

    Miembro miembroSesion = this.encontrarMiembro(idSesion, trayectoDTO.getNombreArea());
    if (miembroSesion == null) {
      return ResponseEntity.status(404).build();
    }

    Trayecto trayecto = new Trayecto();
    //List<Espacio> espacios = repoEspacio.findAll();
    //Espacio partida = espacios.stream().filter(espacio -> espacio.equals(trayectoDTO.getEspacioPartida())).collect(Collectors.toList()).get(0);
    //Espacio llegada = espacios.stream().filter(espacio -> espacio.equals(trayectoDTO.getEspacioLlegada())).collect(Collectors.toList()).get(0);
    Espacio espacioLlegada = repoEspacio.findByLatitudAndLongitudAndProvinciaAndMunicipioAndLocalidadAndDireccionAndNumeroAndCodigoPostal(trayectoDTO.getEspacioLlegada().getLatitud(),trayectoDTO.getEspacioLlegada().getLongitud(), trayectoDTO.getEspacioLlegada().getProvincia(), trayectoDTO.getEspacioLlegada().getMunicipio(),
        trayectoDTO.getEspacioLlegada().getLocalidad(), trayectoDTO.getEspacioLlegada().getDireccion(),trayectoDTO.getEspacioLlegada().getNumero(), trayectoDTO.getEspacioLlegada().getCodigoPostal());
    Espacio espacioPartida = repoEspacio.findByLatitudAndLongitudAndProvinciaAndMunicipioAndLocalidadAndDireccionAndNumeroAndCodigoPostal(trayectoDTO.getEspacioPartida().getLatitud(),trayectoDTO.getEspacioPartida().getLongitud(), trayectoDTO.getEspacioPartida().getProvincia(), trayectoDTO.getEspacioPartida().getMunicipio(),
        trayectoDTO.getEspacioPartida().getLocalidad(), trayectoDTO.getEspacioPartida().getDireccion(),trayectoDTO.getEspacioPartida().getNumero(), trayectoDTO.getEspacioPartida().getCodigoPostal());

    trayecto.setPartida(espacioPartida);
    trayecto.setLlegada(espacioLlegada);
    //int discriminantePartida = partida.discriminante();

    trayecto.setDiasUtilizados(trayectoDTO.getDiasUtilizados());
    trayecto.setFechaFin(trayectoDTO.getFechaFin());
    trayecto.setFechaInicio(trayectoDTO.getFechaInicio());
    trayecto.setTramos(trayectoDTO.getTramos());
    repoTrayecto.save(trayecto);
    miembroSesion.getArea().agregarVinculacion(trayecto);
    repoMiembro.save(miembroSesion);
    return ResponseEntity.status(201).body(trayecto);
  }*/
  public Miembro encontrarMiembro(String idSesion, String nombreArea) {

    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
    Usuario usuarioSesion = (Usuario) atributosSesion.get("usuario");
    Persona personaSesion = repoPersona.findByUsuario(usuarioSesion);
    List<Miembro> miembrosDeSesion = repoMiembro.findAllByPersona(personaSesion);

    List<Miembro> miembrosDeSesionConArea = miembrosDeSesion.stream()
        .filter(miembro -> mismaArea(miembro,nombreArea))
        .collect(Collectors.toList());

    return  miembrosDeSesionConArea.get(0);
  }

  private boolean mismaArea(Miembro miembro, String nombreArea){
    return miembro.getArea().getNombre().equals(nombreArea);
  }


}
