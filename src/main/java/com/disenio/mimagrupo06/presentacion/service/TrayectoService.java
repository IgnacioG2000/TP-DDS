package com.disenio.mimagrupo06.presentacion.service;

import com.disenio.mimagrupo06.presentacion.dto.TrayectoExistenteDTO;
import com.disenio.mimagrupo06.presentacion.dto.TrayectoNuevoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

public interface TrayectoService {
  public String registrarTrayecto(String idSesion) throws IOException;
  public void registrarTrayectoNuevo(String idSesion,TrayectoNuevoDTO trayectoNuevoDTO);
  public void registrarTrayectoExistente(String idSesion,TrayectoExistenteDTO trayectoExistenteDTO);
}
