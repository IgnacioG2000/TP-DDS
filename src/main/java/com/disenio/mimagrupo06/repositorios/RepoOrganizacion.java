package com.disenio.mimagrupo06.repositorios;




import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.organizacion.Contacto;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepoOrganizacion {
  private Collection<Organizacion> organizaciones;

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

}
