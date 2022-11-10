package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.domain.organizacion.OrganizacionService;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.repositorios.RepoTA;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class CalculadoraController {
  @Autowired
  RepoOrganizacion repoOrganizacion;

  @Autowired
  private RepoTA repoTA;

  @GetMapping("/calculadora/organizacion/hcAnual/{anio}")
  public ResponseEntity calcularHCTotalAnio(@RequestHeader("Authorization") String idSesion, @PathVariable("anio") String anio){
    Map<String, Object> atributosSesion = SesionManager.get().obtenerAtributos(idSesion);

    UsuarioOrganizacion usuarioOrganizacionSesion = (UsuarioOrganizacion) atributosSesion.get("usuario");
    System.out.println("usuario organizacion:" + usuarioOrganizacionSesion);
    Organizacion unaOrganizacion = repoOrganizacion.findByUsuarioOrganizacion(usuarioOrganizacionSesion);
    System.out.println("organizacion" + unaOrganizacion);

    Transformador transformador = new Transformador();
    transformador.setTa(repoTA);
    OrganizacionService organizacionService = new OrganizacionService();
    CalculadoraHCActividad calculadoraHCActividad = new CalculadoraHCActividad();
    calculadoraHCActividad.setTa(repoTA);
    organizacionService.setTransformador(transformador);
    organizacionService.setCalculadoraHCActividad(calculadoraHCActividad);
    organizacionService.cargarDatosActividad("/src/main/java/com/disenio/mimagrupo06/excel_ETL/excelTesteo.xls",unaOrganizacion);
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

    Transformador transformador = new Transformador();
    transformador.setTa(repoTA);
    OrganizacionService organizacionService = new OrganizacionService();
    CalculadoraHCActividad calculadoraHCActividad = new CalculadoraHCActividad();
    calculadoraHCActividad.setTa(repoTA);
    organizacionService.setTransformador(transformador);
    organizacionService.setCalculadoraHCActividad(calculadoraHCActividad);
    organizacionService.cargarDatosActividad("/src/main/java/com/disenio/mimagrupo06/excel_ETL/excelTesteo.xls",unaOrganizacion);
    unaOrganizacion.setOrganizacionService(organizacionService);
    double resultado = unaOrganizacion.calcularHuellaCarbonoTotalMensual(Integer.parseInt(anio), Integer.parseInt(mes));

    System.out.println("Resultado = " + resultado);;

    if (usuarioOrganizacionSesion == null) {
      return ResponseEntity.status(404).build();
    }

    return ResponseEntity.status(200).body(resultado);
  }

}
