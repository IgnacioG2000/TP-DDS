package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.excel_ETL.DatoDeLaActividad;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.repositorios.RepoTA;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import com.disenio.mimagrupo06.service.ExcelService;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin

public class ExcelController {

  @Autowired
  private RepoOrganizacion repoOrganizacion;

  @Autowired
  private ExcelService excelService;

  @Autowired
  private RepoTA repoTA;

  private final Handlebars handlebars = new Handlebars();

  public ExcelController() {
  }
  @GetMapping("/cargar/mediciones")
  public ResponseEntity<String> mediciones() throws IOException {
    //validar accion en capa modelo según roles o usuario asociados al idSesion
    Template template = handlebars.compile("/Template/registrarMediciones");

    Map<String, Object> model = new HashMap<>();
    //model.put("listamascotas", mascotas);

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

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


    Collection<DatoDeLaActividad> datoDeLaActividad;

    if(file.isEmpty()){
      return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
    }
    try {
      excelService.saveFile(file);
      transformador.cargarDatos(unaOrganizacion, excelService.obtenerPath(file));
      repoOrganizacion.save(unaOrganizacion);
      excelService.deleteFile(file);
    } catch (IOException e){
      e.printStackTrace();
    }

    return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);
  }



}