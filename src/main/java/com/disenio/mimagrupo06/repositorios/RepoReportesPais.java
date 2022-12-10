package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.presentacion.dto.ReportePaisDTO;
import com.disenio.mimagrupo06.presentacion.dto.ReporteProvinciaDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoReportesPais extends CrudRepository<ReportePaisDTO, Long> {

  @Query(value = "SELECT NEW com.disenio.mimagrupo06.presentacion.dto.ReportePaisDTO(ps.nombre, p.nombre, SUM(hc.huellaCarbono))" +
      "  FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
      "                         LEFT JOIN ValorHCMensualSector hc ON p.id = hc.sector.id" +
      "  WHERE ps.nombre = :nombre" +
      "  GROUP BY ps.nombre, p.nombre")
  List<ReportePaisDTO> findAllComposicionHCTotalDeUnDeterminadPais(String nombre);
}
