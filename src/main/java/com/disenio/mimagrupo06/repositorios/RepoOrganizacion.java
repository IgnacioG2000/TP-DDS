package com.disenio.mimagrupo06.repositorios;




import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.organizacion.Contacto;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RepoOrganizacion {
  private Collection<Organizacion> organizaciones;

  @PersistenceContext
  private EntityManager entityManager;

  private static final RepoOrganizacion INSTANCE = new RepoOrganizacion();

  public static RepoOrganizacion getInstance() {
    return INSTANCE;
  }

  public Collection<Organizacion> getOrganizaciones() {
    return organizaciones;
  }

  public Organizacion encontrarOrganizacion(Area area){
    return organizaciones.stream().filter(organizacion->organizacion.tieneArea(area)).findFirst().get();
  }

  public Collection<Contacto> listadoContactosOrganizaciones(){
    Collection<Contacto> lista = new ArrayList<>();
    organizaciones.forEach(o -> lista.addAll(o.getContactos()));
    return lista;
  }

  public Collection<Area> listadoAreasOrganizaciones(){
    Collection<Area> lista = new ArrayList<>();
    organizaciones.forEach(o -> lista.addAll(o.getSectores()));
    return lista;
  }

  public List<String> obtenerHCPorProvincia(){

    String query = " SELECT o.tipoDeOrganizacion, o.clasificacion, COALESCE(SUM(hc.huellaCarbono), 0) AS HC_TOTAL" +
                   " FROM Organizacion o LEFT JOIN ValorHCMensualOrganizacion hc ON o.id = hc.id" +
                   " GROUP BY o.tipoDeOrganizacion, o.clasificacion";

    return entityManager.createQuery(query, String.class)
        .getResultStream()
        .collect(Collectors.toList());
  }

  public List<String> obtenerEvolucionHCDeUnaDeterminadaOrganizacion(String razonSocial){

    String query = " SELECT o.razonSocial, hc.anio, SUM(hc.huellaCarbono) AS HC_TOTAL" +
        " FROM Organizacion o LEFT JOIN ValorHCMensualOrganizacion hc ON o.id = hc.id" +
        " WHERE o.razonSocial = :razonSocial" +
        " GROUP BY o.id, o.razonSocial, hc.anio";

    return entityManager.createQuery(query, String.class)
        .setParameter("razonSocial", razonSocial)
        .getResultStream()
        .collect(Collectors.toList());
  }

}
