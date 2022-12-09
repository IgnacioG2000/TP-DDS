package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.sector.PaisSector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoPaisSector extends CrudRepository<PaisSector, Long> {
  @Query(value="SELECT p.nombre, p.id FROM PaisSector p WHERE p.id = ?1")
  List<PaisSector> findAllById(Long id);

  @Query(value = " SELECT ps.nombre AS PAIS, p.nombre AS PROVINCIA, COALESCE(SUM(hc.huellaCarbono), 0) AS HC_TOTAL" +
                 " FROM ProvinciaSector p JOIN PaisSector ps ON p.pais.id = ps.id" +
                 "                        LEFT JOIN ValorHCMensualSector hc ON p.id = hc.sector.id" +
                 " GROUP BY ps.nombre, p.nombre")
  List<PaisSector> findAllComposicionHCTotalANivelPais();

}
