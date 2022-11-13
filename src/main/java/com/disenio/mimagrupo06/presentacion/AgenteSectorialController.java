package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.domain.organizacion.OrganizacionService;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.repositorios.RepoTA;
import com.disenio.mimagrupo06.seguridad.roles.AgenteSectorial;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

public class AgenteSectorialController {
  @Autowired
  RepoOrganizacion repoOrganizacion;

  @Autowired
  private RepoTA repoTA;

  @GetMapping("/calculadora/agenteSectorial/hcAnual/{anio}")
  public ResponseEntity calcularHCTotalAnio(@RequestHeader("Authorization") String idSesion, @PathVariable("anio") String anio){
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    AgenteSectorial agenteSectorial = (AgenteSectorial) atributosSesion.get("usuario");
    System.out.println("usuario del agente sectorial:" + agenteSectorial);

    if (agenteSectorial == null) {
      return ResponseEntity.status(404).build();
    }

    double resultado = agenteSectorial.calcularHuellaCarbonoPorSectorAnual(Integer.parseInt(anio));

    System.out.println("Resultado = " + resultado);;

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

    double resultado = agenteSectorial.calcularHuellaCarbonoPorSectorMensual(Integer.parseInt(anio), Integer.parseInt(mes));

    System.out.println("Resultado = " + resultado);;

    return ResponseEntity.status(200).body(resultado);
  }
}
