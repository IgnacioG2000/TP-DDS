package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.domain.organizacion.OrganizacionService;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.repositorios.RepoTA;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class CalculadoraController {
  @Autowired
  RepoOrganizacion repoOrganizacion;

  @Autowired
  private RepoTA repoTA;

  private final Handlebars handlebars = new Handlebars();

  public CalculadoraController() {
  }


  @GetMapping("/calculadora/organizacion/hcAnual")
  public ResponseEntity<String> calculadoraAnual() throws IOException {
    //validar accion en capa modelo según roles o usuario asociados al idSesion
    Template template = handlebars.compile("/templates/calculadoraHCAnualOrg");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  @GetMapping("/calculadora/organizacion/hcMensual")
  public ResponseEntity<String> calculadoraMensual() throws IOException {
    //validar accion en capa modelo según roles o usuario asociados al idSesion
    Template template = handlebars.compile("/templates/calculadoraHCMensualOrg");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  @GetMapping("calculadora/resultado")
  public ResponseEntity resultado() throws IOException {
      Template template = handlebars.compile("/templates/resultadoCalculadoraHC");

      Map<String, Object> model = new HashMap<>();


      String html = template.apply(model);

      return ResponseEntity.status(200).body(html);
  }

  @GetMapping("/calculadora/organizacion/hcAnual/{anio}")
  @ResponseBody
  public ResponseEntity calcularHCTotalAnio(@RequestHeader("Authorization") String idSesion, @PathVariable("anio") String anio){
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);
    System.out.println("ESTOY ACAAAAA");
    UsuarioOrganizacion usuarioOrganizacionSesion = (UsuarioOrganizacion) atributosSesion.get("usuario");
    System.out.println("usuario organizacion:" + usuarioOrganizacionSesion);
    Organizacion unaOrganizacion = repoOrganizacion.findByUsuarioOrganizacion(usuarioOrganizacionSesion);
    System.out.println("organizacion" + unaOrganizacion);

    OrganizacionService organizacionService = new OrganizacionService();
    CalculadoraHCActividad calculadoraHCActividad = new CalculadoraHCActividad();
    calculadoraHCActividad.setTa(repoTA);
    organizacionService.setCalculadoraHCActividad(calculadoraHCActividad);
    unaOrganizacion.setOrganizacionService(organizacionService);
    double resultado = unaOrganizacion.calcularHuellaCarbonoTotalAnio(Integer.parseInt(anio));

    System.out.println("Resultado = " + resultado);;

    if (usuarioOrganizacionSesion == null) {
      return ResponseEntity.status(404).build();
    }

    return ResponseEntity.status(200).body(resultado);
  }

  @GetMapping("/calculadora/organizacion/hcMensual/{anio}/{mes}")
  public ResponseEntity calcularHCTotalMes(@RequestHeader("Authorization") String idSesion,
                                           @PathVariable("anio") String anio,  @PathVariable("mes") String mes){
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    UsuarioOrganizacion usuarioOrganizacionSesion = (UsuarioOrganizacion) atributosSesion.get("usuario");
    System.out.println("usuario organizacion:" + usuarioOrganizacionSesion);
    Organizacion unaOrganizacion = repoOrganizacion.findByUsuarioOrganizacion(usuarioOrganizacionSesion);
    System.out.println("organizacion" + unaOrganizacion);


    OrganizacionService organizacionService = new OrganizacionService();
    unaOrganizacion.setOrganizacionService(organizacionService);
    CalculadoraHCActividad calculadoraHCActividad = new CalculadoraHCActividad();
    calculadoraHCActividad.setTa(repoTA);
    organizacionService.setCalculadoraHCActividad(calculadoraHCActividad);
    double resultado = unaOrganizacion.calcularHuellaCarbonoTotalMensual(Integer.parseInt(anio), Integer.parseInt(mes));

    System.out.println("Resultado = " + resultado);;

    if (usuarioOrganizacionSesion == null) {
      return ResponseEntity.status(404).build();
    }

    return ResponseEntity.status(200).body(resultado);
  }

}
