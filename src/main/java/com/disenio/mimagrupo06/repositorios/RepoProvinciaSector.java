package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.sector.ProvinciaSector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoProvinciaSector extends CrudRepository<ProvinciaSector, Long> {
    ProvinciaSector findByProvinciaCodigo(Long provinciaCodigo);

    List<ProvinciaSector> findAll();

    @Query(value = "SELECT p.nombre AS PROVINCIA, COALESCE(SUM(hc.huellaCarbono), 0) AS HC_TOTAL" +
        "  FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
        "                         JOIN MunicipioSector m ON p.id = m.provincia.id" +
        "                         LEFT JOIN ValorHCMensualSector hc ON p.id = hc.sector.id" +
        "  GROUP BY p.nombre")
    List<ProvinciaSector> findAllHCPorSectorTerritorial();

    @Query(value = "SELECT p.nombre AS PROVINCIA, m.nombre AS MUNICIPIO, COALESCE(SUM(hc.huellaCarbono), 0) AS HC_TOTAL" +
        "  FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
        "                         JOIN MunicipioSector m ON p.id = m.provincia.id" +
        "                         LEFT JOIN ValorHCMensualSector hc ON p.id = hc.sector.id" +
        "  WHERE p.nombre = :nombre" +
        "  GROUP BY p.nombre, m.nombre")
    List<ProvinciaSector> findAllComposicionHCTotalDeUnaDeterminadaProvincia(String nombre);

    @Query(value = " SELECT p.nombre AS PROVINCIA, hc.anio AS AÑO, SUM(hc.huellaCarbono) AS HC_TOTAL" +
                   " FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
                   "                        JOIN MunicipioSector m ON p.id = m.provincia.id" +
                   "                        LEFT JOIN ValorHCMensualSector hc ON p.id = hc.sector.id" +
                   " WHERE p.nombre = :nombre" +
                   " GROUP BY p.nombre, hc.anio")
    List<ProvinciaSector> findAllEvolucionHCTotalDeUnaDeterminadaProvincia(String nombre);
}