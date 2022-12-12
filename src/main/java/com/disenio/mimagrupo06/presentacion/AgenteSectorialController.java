package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.domain.organizacion.OrganizacionService;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.repositorios.*;
import com.disenio.mimagrupo06.seguridad.roles.AgenteSectorial;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AgenteSectorialController {
  @Autowired
  RepoOrganizacion repoOrganizacion;

  @Autowired
  private RepoTA repoTA;

  @Autowired
  private RepoArea ra;

  @Autowired
  private RepoUsuario ru;

  private Handlebars handlebars = new Handlebars();
  public AgenteSectorialController(){

  }
  @GetMapping("/calculadora/agenteSectorial/hcAnual")
  public ResponseEntity<String> calculadoraAnual() throws IOException {
    //validar accion en capa modelo según roles o usuario asociados al idSesion
    Template template = handlebars.compile("/Template/calculadoraHCAnualAgente");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  @GetMapping("/calculadora/agenteSectorial/hcMensual")
  public ResponseEntity<String> calculadoraMensual() throws IOException {
    //validar accion en capa modelo según roles o usuario asociados al idSesion
    Template template = handlebars.compile("/Template/calculadoraHCMensualAgente");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  @GetMapping("/calculadora/agenteSectorial/hcAnual/{anio}")
  public ResponseEntity calcularHCTotalAnio(@RequestHeader("Authorization") String idSesion, @PathVariable("anio") String anio){
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    AgenteSectorial agenteSectorial = (AgenteSectorial) atributosSesion.get("usuario");
    System.out.println("usuario del agente sectorial:" + agenteSectorial);

    if (agenteSectorial == null) {
      return ResponseEntity.status(404).build();
    }

    agenteSectorial.setRa(ra);

    double resultado = agenteSectorial.calcularHuellaCarbonoPorSectorAnual(Integer.parseInt(anio));

    System.out.println("Resultado = " + resultado);;
    ru.save(agenteSectorial);
    return ResponseEntity.status(200).body(resultado);
  }

  @GetMapping("/calculadora/agenteSectorial/hcMensual/{anio}/{mes}")
  public ResponseEntity calcularHCTotalMes(@RequestHeader("Authorization") String idSesion,
                                           @PathVariable("anio") String anio,  @PathVariable("mes") String mes){
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    AgenteSectorial agenteSectorial = (AgenteSectorial) atributosSesion.get("usuario");
    System.out.println("usuario del agente sectorial:" + agenteSectorial);

    if (agenteSectorial == null) {
      return ResponseEntity.status(404).build();
    }

    agenteSectorial.setRa(ra);


    double resultado = agenteSectorial.calcularHuellaCarbonoPorSectorMensual(Integer.parseInt(anio), Integer.parseInt(mes));

    System.out.println("Resultado = " + resultado);;
    ru.save(agenteSectorial);
    return ResponseEntity.status(200).body(resultado);
  }

  @GetMapping("/recomendaciones")
  public ResponseEntity<String> mostrarRecomendaciones() throws IOException {
    //validar accion en capa modelo según roles o usuario asociados al idSesion
    Template template = handlebars.compile("/Template/recomendaciones");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  @GetMapping("/recomendaciones/comingSoon")
  public ResponseEntity<String> mostrarRecomendacion() throws IOException {
    //validar accion en capa modelo según roles o usuario asociados al idSesion
    Template template = handlebars.compile("/Template/comingSoon");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }
}
