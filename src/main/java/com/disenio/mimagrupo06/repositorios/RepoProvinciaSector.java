package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.sector.ProvinciaSector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoProvinciaSector extends CrudRepository<ProvinciaSector, Long> {
    ProvinciaSector findByProvinciaCodigo(Long provinciaCodigo);

    @Query(value = "SELECT p.nombre AS PROVINCIA, COUNT(m.nombre) AS HC_TOTAL" + //TODO
        "  FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
        "                         JOIN MunicipioSector m ON p.id = m.provincia.id" +
        "  WHERE p.nombre = :nombre" +
        "  GROUP BY 1")
    List<ProvinciaSector> findAllByNombre(String nombre);

}