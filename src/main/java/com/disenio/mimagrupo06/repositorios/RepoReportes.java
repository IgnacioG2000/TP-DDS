package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.presentacion.dto.ReporteProvincia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoReportes extends CrudRepository<ReporteProvincia, Long> {
  ReporteProvincia findByProvincia(String provincia);

  @Query(value = "SELECT NEW com.disenio.mimagrupo06.presentacion.dto.ReporteProvincia(p.nombre, SUM(hc.huellaCarbono))" +
                " FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
                "                        JOIN MunicipioSector m ON p.id = m.provincia.id" +
                "                        LEFT JOIN ValorHCMensualSector hc ON p.id = hc.sector.id" +
                "  GROUP BY p.nombre")
  List<ReporteProvincia> findAllHCPorSectorTerritorial();
}
