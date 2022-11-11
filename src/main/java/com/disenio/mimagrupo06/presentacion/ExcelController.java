package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.repositorios.RepoTA;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import com.disenio.mimagrupo06.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller

public class ExcelController {

  @Autowired
  private RepoOrganizacion repoOrganizacion;

  @Autowired
  private ExcelService excelService;

  @Autowired
  private RepoTA repoTA;

  @GetMapping("/cargar/mediciones")
  public String index(){ return "registrarMediciones"; }

  @PostMapping("/upload")
  public ResponseEntity<?> uploadFile(@RequestHeader("Authorization") String idSesion, @RequestParam("file")MultipartFile file){
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    UsuarioOrganizacion usuarioOrganizacionSesion = (UsuarioOrganizacion) atributosSesion.get("usuario");
    System.out.println("usuario organizacion:" + usuarioOrganizacionSesion);
    Organizacion unaOrganizacion = repoOrganizacion.findByUsuarioOrganizacion(usuarioOrganizacionSesion);
    System.out.println("organizacion" + unaOrganizacion);

    Transformador transformador = new Transformador();
    transformador.setTa(repoTA);

    if (usuarioOrganizacionSesion == null) {
      return ResponseEntity.status(404).build();
    }



    if(file.isEmpty()){
      return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
    }
    try {
      excelService.saveFile(file);
      transformador.cargarDatos(unaOrganizacion, excelService.obtenerPath(file));
      excelService.deleteFile(file);
    } catch (IOException e){
      e.printStackTrace();
    }

    return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);
  }



}