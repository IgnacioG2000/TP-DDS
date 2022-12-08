package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.presentacion.dto.ReporteMunicipioDTO;
import com.disenio.mimagrupo06.presentacion.dto.ReporteOrganizacionDTO;
import com.disenio.mimagrupo06.presentacion.dto.ReporteProvinciaDTO;
import com.disenio.mimagrupo06.repositorios.RepoReportesMunicipio;
import com.disenio.mimagrupo06.repositorios.RepoReportesOrganizacion;
import com.disenio.mimagrupo06.repositorios.RepoReportesProvincia;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReportesController {

  @Autowired
  private RepoReportesProvincia repoReportesProvincia;
  @Autowired
  private RepoReportesMunicipio repoReportesMunicipio;
  @Autowired
  private RepoReportesOrganizacion repoReportesOrganizacion;


  private final Handlebars handlebars = new Handlebars(); //TODO instanciar en constructor

  //REPORTES PROVINCIAS

  @GetMapping("/hc_provincia_back")
  public ResponseEntity<List<ReporteProvinciaDTO>> obtenerHCPorProvincia(){
    List<ReporteProvinciaDTO> result = repoReportesProvincia.findAllHCPorProvincia();
    return ResponseEntity.status(200).body(result);
  }

  @GetMapping(value = "/hc_total_provincia", produces = MediaType.TEXT_HTML_VALUE)
  public ResponseEntity<String> obtenerHCPorProvinciaHand() throws IOException {

    Template template = handlebars.compile("/Template/resultadoReporteHCTotalProvincia");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  @GetMapping("/composicion_hc_provincia_back/{nombre}")
  public ResponseEntity<List<ReporteProvinciaDTO>> obtenerComposicionHCTotalDeUnaProvincia(@PathVariable String nombre){
    List<ReporteProvinciaDTO> result = repoReportesProvincia.findAllComposicionHCTotalDeUnaDeterminadaProvincia(nombre);
    return ResponseEntity.status(200).body(result);
  }

  @GetMapping(value = "/composicion_hc_provincia", produces = MediaType.TEXT_HTML_VALUE)
  public ResponseEntity<String> obtenerComposicionHCTotalDeUnaProvinciaHand(@RequestParam String nombre) throws IOException {

    Template template = handlebars.compile("/Template/resultadoReporteComposicionProvincia");

    Map<String, Object> model = new HashMap<>();
    model.put("nombre", nombre);

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  @GetMapping("/evolucion_hc_provincia_back/{nombre}")
  public ResponseEntity<List<ReporteProvinciaDTO>> obtenerEvolucionHCTotalDeUnaProvincia(@PathVariable String nombre){
    List<ReporteProvinciaDTO> result = repoReportesProvincia.findAllEvolucionHCTotalDeUnaDeterminadaProvincia(nombre);
    return ResponseEntity.status(200).body(result);
  }

  @GetMapping(value = "/evolucion_hc_provincia", produces = MediaType.TEXT_HTML_VALUE)
  public ResponseEntity<String> obtenerEvolucionHCTotalDeUnaProvinciaHand(@RequestParam String nombre) throws IOException {

    Template template = handlebars.compile("/Template/resultadoReporteEvolucionHCProvincia");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  //REPORTES MUNICIPIOS

  @GetMapping("/hc_total_municipio_back")
  public ResponseEntity<List<ReporteMunicipioDTO>> obtenerHCPorMunicipio(){
    List<ReporteMunicipioDTO> result = repoReportesMunicipio.findAllHCPorMunicipio();
    return ResponseEntity.status(200).body(result);
  }

  @GetMapping(value = "/hc_total_municipio", produces = MediaType.TEXT_HTML_VALUE)
  public ResponseEntity<String> obtenerHCPorMunicipioHand() throws IOException {

    Template template = handlebars.compile("/Template/resultadoReporteHCTotalMunicipio");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  @GetMapping("/evolucion_hc_municipio_back/{nombre}")
  public ResponseEntity<List<ReporteMunicipioDTO>> obtenerEvolucionHCTotalDeUnMunicipio(@PathVariable String nombre){
    List<ReporteMunicipioDTO> result = repoReportesMunicipio.findAllEvolucionHCTotalDeUnDeterminadoMunicipio(nombre);
    return ResponseEntity.status(200).body(result);
  }

  @GetMapping(value = "/evolucion_hc_municipio")
  public ResponseEntity<String> obtenerEvolucionHCTotalDeUnMunicipioHand(@RequestParam String nombre) throws IOException {

    Template template = handlebars.compile("/Template/resultadoReporteEvolucionHCMunicipio");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  //REPORTES ORGANIZACIONES

  @GetMapping("/hc_total_tipo_organizacion_back")
  public ResponseEntity<List<ReporteOrganizacionDTO>> obtenerHCTotalPorTipoDeOrganizacion(){
    List<ReporteOrganizacionDTO> result = repoReportesOrganizacion.findAllHCTotalPorTipoDeOrganizacion();
    return ResponseEntity.status(200).body(result);
  }

  @GetMapping(value = "/hc_total_tipo_organizacion")
  public ResponseEntity<String> obtenerHCTotalPorTipoDeOrganizacionHand() throws IOException {

    Template template = handlebars.compile("/Template/resultadoReporteHCTotalPorTipoDeOrganizacion");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

  @GetMapping("/evolucion_hc_organizacion_back/{razon_social}")
  public ResponseEntity<List<ReporteOrganizacionDTO>> obtenerEvolucionHCDeUnaDeterminadaOrganizacion(@PathVariable String razon_social){
    List<ReporteOrganizacionDTO> result = repoReportesOrganizacion.findAllEvolucionHCDeUnaDeterminadaOrganizacion(razon_social);
    return ResponseEntity.status(200).body(result);
  }

  @GetMapping(value = "/evolucion_hc_organizacion")
  public ResponseEntity<String> obtenerEvolucionHCDeUnaDeterminadaOrganizacionHand(@RequestParam String razon_social) throws IOException {

    Template template = handlebars.compile("/Template/resultadoReporteEvolucionHCDeUnaDeterminadaOrganizacion");

    Map<String, Object> model = new HashMap<>();

    String html = template.apply(model);

    return ResponseEntity.status(200).body(html);
  }

}
