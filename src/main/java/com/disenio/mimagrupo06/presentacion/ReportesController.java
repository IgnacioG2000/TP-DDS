package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.presentacion.dto.ReporteProvinciaDTO;
import com.disenio.mimagrupo06.repositorios.RepoReportesProvincia;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Controller
public class ReportesController {

  @Autowired
  private RepoReportesProvincia repoReportesProvincia;

  private final Handlebars handlebars = new Handlebars(); //TODO instanciar en constructor

  @GetMapping(value = "/hc_provincia", produces = MediaType.TEXT_HTML_VALUE)
  public ResponseEntity<String> obtenerHCPorProvincia() throws IOException {
    Template template = handlebars.compile("/Template/login");

    List<ReporteProvinciaDTO> result = repoReportesProvincia.findAllHCPorProvincia();
    return ResponseEntity.status(200).body(result);
  }

  @GetMapping("/composicion_hc_provincia/{nombre}")
  public ResponseEntity<List<ReporteProvinciaDTO>> obtenerComposicionHCTotalDeUnaProvincia(@PathVariable String nombre){
    List<ReporteProvinciaDTO> result = repoReportesProvincia.findAllComposicionHCTotalDeUnaDeterminadaProvincia(nombre);
    return ResponseEntity.status(200).body(result);
  }

  @GetMapping("/evolucion_hc_provincia/{nombre}")
  public ResponseEntity<List<ReporteProvinciaDTO>> obtenerEvolucionHCTotalDeUnaProvincia(@PathVariable String nombre){
    List<ReporteProvinciaDTO> result = repoReportesProvincia.findAllEvolucionHCTotalDeUnaDeterminadaProvincia(nombre);
    return ResponseEntity.status(200).body(result);
  }
}
