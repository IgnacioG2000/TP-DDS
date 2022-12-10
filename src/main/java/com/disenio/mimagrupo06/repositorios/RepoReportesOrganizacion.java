package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.presentacion.dto.ReporteMunicipioDTO;
import com.disenio.mimagrupo06.presentacion.dto.ReporteOrganizacionDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoReportesOrganizacion extends CrudRepository<ReporteOrganizacionDTO, Long> {

  @Query(value = " SELECT NEW com.disenio.mimagrupo06.presentacion.dto.ReporteOrganizacionDTO(o.tipoDeOrganizacion, o.clasificacion, SUM(hc.huellaCarbono))" +
                 " FROM Organizacion o LEFT JOIN ValorHCMensualOrganizacion hc ON o.id = hc.id" +
                 " GROUP BY o.tipoDeOrganizacion, o.clasificacion")
  List<ReporteOrganizacionDTO> findAllHCTotalPorTipoDeOrganizacion();

  @Query(value = " SELECT NEW com.disenio.mimagrupo06.presentacion.dto.ReporteOrganizacionDTO(o.razonSocial, hc.anio, SUM(hc.huellaCarbono))" +
                 " FROM Organizacion o LEFT JOIN ValorHCMensualOrganizacion hc ON o.id = hc.organizacion.id" +
                 " WHERE o.razonSocial LIKE :razonSocial" +
                 " GROUP BY o.razonSocial, hc.anio" +
                 " ORDER BY hc.anio")
  List<ReporteOrganizacionDTO> findAllEvolucionHCDeUnaDeterminadaOrganizacion(String razonSocial);
}
