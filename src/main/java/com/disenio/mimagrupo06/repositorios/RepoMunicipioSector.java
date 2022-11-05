package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.sector.MunicipioSector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepoMunicipioSector extends CrudRepository<MunicipioSector, Long> {

    @Query(value = "SELECT m.nombre AS MUNICIPIO, SUM(hc.huellaCarbono) AS HC_TOTAL" +
        "  FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
        "                         JOIN MunicipioSector m ON p.id = m.provincia.id" +
        "                         LEFT JOIN ValorHCMensualSector hc ON m.id = hc.sector.id" +
        "  WHERE p.nombre = :nombre" +
        "  GROUP BY m.nombre")
    MunicipioSector findByNombre(String nombre);

    MunicipioSector findByMunicipioCodigo(Long municipioCodigo);
}
