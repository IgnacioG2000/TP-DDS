package com.disenio.mimagrupo06.presentacion;

import com.disenio.mimagrupo06.presentacion.dto.ReporteProvincia;
import com.disenio.mimagrupo06.repositorios.RepoReportes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ReportesController {

  @Autowired
  private RepoReportes repoReportes;

  @GetMapping("/hc_provincia")
  public ResponseEntity<List<ReporteProvincia>> obtenerHCPorProvincia(){
    List<ReporteProvincia> result = repoReportes.findAllHCPorSectorTerritorial();
    return ResponseEntity.status(200).body(result);
  }

}
