package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.EspacioDeTrabajo;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Hogar;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Parada;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.presentacion.dto.EspacioDeTrabajoDTO;
import com.disenio.mimagrupo06.presentacion.dto.HogarDTO;
import com.disenio.mimagrupo06.presentacion.dto.ParadaDTO;
import com.disenio.mimagrupo06.repositorios.RepoEspacio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class EspacioController {
    @Autowired
    RepoEspacio repoEspacio;


    @PostMapping("/registrarHogar")
    public ResponseEntity chequeoEspacio(@RequestHeader("Authorization") String idSesion, @RequestBody HogarDTO espacioDTO) {
        Miembro miembroEncontrado = SesionManager.get().encontrarMiembro(idSesion, espacioDTO.getNombreArea());
        Area area = miembroEncontrado.getArea();
        HashSet<Espacio> setEspaciosRegistradosEnArea = new HashSet<Espacio>();
        area.getTrayectosRegistados().forEach(trayecto -> trayecto.getTramos().forEach(tramo -> setEspaciosRegistradosEnArea.addAll(Arrays.asList(tramo.getPartida(), tramo.getLlegada()))));
        Espacio espacio;
        if (setEspaciosRegistradosEnArea.stream().anyMatch(espa -> espa.equals(espacioDTO.getHogar()))) {//esta linea no se si funciona: comparo Espacio con EspacioDeTrabajo
            espacio = setEspaciosRegistradosEnArea.stream().filter(espa -> espa.equals(espacioDTO.getHogar())).collect(Collectors.toList()).get(0);
        } else {
            espacio = new Hogar(espacioDTO.getHogar().getLatitud(), espacioDTO.getHogar().getLongitud(), espacioDTO.getHogar().getProvincia(), espacioDTO.getHogar().getMunicipio(), espacioDTO.getHogar().getLocalidad(),
                    espacioDTO.getHogar().getDireccion(), espacioDTO.getHogar().getNumero(), espacioDTO.getHogar().getCodigoPostal(), null, espacioDTO.getHogar().getPiso(), espacioDTO.getHogar().getDepartamento(), espacioDTO.getHogar().getTipoDeHogar());
            repoEspacio.save(espacio);
        }
        return ResponseEntity.status(201).body(espacio);
    }


    @PostMapping("/registrarParada")
    public ResponseEntity chequeoParada(@RequestHeader("Authorization") String idSesion, @RequestBody ParadaDTO paradaDTO) {
        Miembro miembroEncontrado = SesionManager.get().encontrarMiembro(idSesion, paradaDTO.getNombreArea());
        Area area = miembroEncontrado.getArea();
        HashSet<Espacio> setEspaciosRegistradosEnArea = new HashSet<Espacio>();
        area.getTrayectosRegistados().forEach(trayecto -> trayecto.getTramos().forEach(tramo -> setEspaciosRegistradosEnArea.addAll(Arrays.asList(tramo.getPartida(), tramo.getLlegada()))));
        Espacio espacio;
        if (setEspaciosRegistradosEnArea.stream().anyMatch(espa -> espa.equals(paradaDTO.getParada()))) {//esta linea no se si funciona: comparo Espacio con EspacioDeTrabajo
            espacio = setEspaciosRegistradosEnArea.stream().filter(espa -> espa.equals(paradaDTO.getParada())).collect(Collectors.toList()).get(0);
        } else {
            espacio = new Parada(paradaDTO.getParada().getLatitud(), paradaDTO.getParada().getLongitud(), paradaDTO.getParada().getProvincia(), paradaDTO.getParada().getMunicipio(), paradaDTO.getParada().getLocalidad(),
                    paradaDTO.getParada().getDireccion(), paradaDTO.getParada().getNumero(), paradaDTO.getParada().getCodigoPostal());
            repoEspacio.save(espacio);
        }
        return ResponseEntity.status(201).body(espacio);
    }

    @PostMapping("/registrarEspacioDeTrabajo")
    public ResponseEntity chequeoEspacioDeTrabajo(@RequestHeader("Authorization") String idSesion, @RequestBody EspacioDeTrabajoDTO espacioDTO) {
        Miembro miembroEncontrado = SesionManager.get().encontrarMiembro(idSesion, espacioDTO.getNombreArea());
        Area area = miembroEncontrado.getArea();
        HashSet<Espacio> setEspaciosRegistradosEnArea = new HashSet<Espacio>();
        area.getTrayectosRegistados().forEach(trayecto -> trayecto.getTramos().forEach(tramo -> setEspaciosRegistradosEnArea.addAll(Arrays.asList(tramo.getPartida(), tramo.getLlegada()))));
        Espacio espacio;
        if (setEspaciosRegistradosEnArea.stream().anyMatch(espa -> espa.equals(espacioDTO.getEspacioDeTrabajo()))) {//esta linea no se si funciona: comparo Espacio con EspacioDeTrabajo
            espacio = setEspaciosRegistradosEnArea.stream().filter(espa -> espa.equals(espacioDTO.getEspacioDeTrabajo())).collect(Collectors.toList()).get(0);
        } else {
            espacio = new EspacioDeTrabajo(espacioDTO.getEspacioDeTrabajo().getLatitud(), espacioDTO.getEspacioDeTrabajo().getLongitud(), espacioDTO.getEspacioDeTrabajo().getProvincia(), espacioDTO.getEspacioDeTrabajo().getMunicipio(), espacioDTO.getEspacioDeTrabajo().getLocalidad(),
                    espacioDTO.getEspacioDeTrabajo().getDireccion(), espacioDTO.getEspacioDeTrabajo().getNumero(), espacioDTO.getEspacioDeTrabajo().getCodigoPostal(), espacioDTO.getEspacioDeTrabajo().getPiso(), espacioDTO.getEspacioDeTrabajo().getUnidad());
            repoEspacio.save(espacio);
        }
        return ResponseEntity.status(201).body(espacio);
    }
}