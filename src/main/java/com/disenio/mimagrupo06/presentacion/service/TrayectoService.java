package com.disenio.mimagrupo06.presentacion.service;

import com.disenio.mimagrupo06.presentacion.dto.TrayectoExistenteDTO;
import com.disenio.mimagrupo06.presentacion.dto.TrayectoNuevoDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface TrayectoService {
  public void registrarTrayectoNuevo(String idSesion,TrayectoNuevoDTO trayectoNuevoDTO);
  public void registrarTrayectoExistente(String idSesion,TrayectoExistenteDTO trayectoExistenteDTO);
}
