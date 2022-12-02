package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.presentacion.dto.ReporteMunicipioDTO;
import com.disenio.mimagrupo06.presentacion.dto.ReporteProvinciaDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoReportesMunicipio extends CrudRepository<ReporteMunicipioDTO, Long> {

  @Query(value = "SELECT NEW com.disenio.mimagrupo06.presentacion.dto.ReporteMunicipioDTO(m.nombre, SUM(hc.huellaCarbono))" +
      "  FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
      "                         JOIN MunicipioSector m ON p.id = m.provincia.id" +
      "                         LEFT JOIN ValorHCMensualSector hc ON m.id = hc.sector.id" +
      "  GROUP BY m.nombre")
  List<ReporteMunicipioDTO> findAllHCPorMunicipio();

  @Query(value = " SELECT NEW com.disenio.mimagrupo06.presentacion.dto.ReporteMunicipioDTO(m.nombre, hc.anio, SUM(hc.huellaCarbono))" +
      " FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
      "                        JOIN MunicipioSector m ON p.id = m.provincia.id" +
      "                        LEFT JOIN ValorHCMensualSector hc ON m.id = hc.sector.id" +
      " WHERE m.nombre = :nombre" +
      " GROUP BY m.nombre, hc.anio")
  List<ReporteMunicipioDTO> findAllEvolucionHCTotalDeUnDeterminadoMunicipio(String nombre);
}
