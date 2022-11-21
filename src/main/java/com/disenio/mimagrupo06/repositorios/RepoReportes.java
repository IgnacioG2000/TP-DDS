package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.presentacion.dto.ReporteProvinciaDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoReportes extends CrudRepository<ReporteProvinciaDTO, Long> {
  ReporteProvinciaDTO findByProvincia(String provincia);

  @Query(value = "SELECT NEW com.disenio.mimagrupo06.presentacion.dto.ReporteProvinciaDTO(p.nombre, SUM(hc.huellaCarbono))" +
                " FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
                "                        JOIN MunicipioSector m ON p.id = m.provincia.id" +
                "                        LEFT JOIN ValorHCMensualSector hc ON p.id = hc.sector.id" +
                "  GROUP BY p.nombre")
  List<ReporteProvinciaDTO> findAllHCPorSectorTerritorial();

  @Query(value = "SELECT NEW com.disenio.mimagrupo06.presentacion.dto.ReporteProvinciaDTO(p.nombre, m.nombre, SUM(hc.huellaCarbono))" +
                "  FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
                "                         JOIN MunicipioSector m ON p.id = m.provincia.id" +
                "                         LEFT JOIN ValorHCMensualSector hc ON p.id = hc.sector.id" +
                "  WHERE p.nombre = :nombre" +
                "  GROUP BY p.nombre, m.nombre")
  List<ReporteProvinciaDTO> findAllComposicionHCTotalDeUnaDeterminadaProvincia(String nombre);

  @Query(value = " SELECT NEW com.disenio.mimagrupo06.presentacion.dto.ReporteProvinciaDTO(p.nombre, hc.anio, SUM(hc.huellaCarbono))" +
                  " FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
                  "                        JOIN MunicipioSector m ON p.id = m.provincia.id" +
                  "                        LEFT JOIN ValorHCMensualSector hc ON p.id = hc.sector.id" +
                  " WHERE p.nombre = :nombre" +
                  " GROUP BY p.nombre, hc.anio")
  List<ReporteProvinciaDTO> findAllEvolucionHCTotalDeUnaDeterminadaProvincia(String nombre);
}
