package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.sector.MunicipioSector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RepoMunicipioSector extends CrudRepository<MunicipioSector, Long> {

    MunicipioSector findByMunicipioCodigo(Long municipioCodigo);

    @Query(value = "SELECT m.nombre AS MUNICIPIO, COALESCE(SUM(hc.huellaCarbono), 0) AS HC_TOTAL" +
        "  FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
        "                         JOIN MunicipioSector m ON p.id = m.provincia.id" +
        "                         LEFT JOIN ValorHCMensualSector hc ON m.id = hc.sector.id" +
        "  GROUP BY m.nombre")
    List<MunicipioSector> findAllHCPorSectorTerritorial();

}
