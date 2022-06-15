package domain.organización;

import excel_ETL.Transformador;

import java.util.Collection;

public class Organizacion {
  private String razonSocial;
  private TipoDeOrganizacion tipoDeOrganizacion;
  private Collection<Area> sectores;
  private Clasificacion clasificacion;
  private Transformador transformador;

  public Organizacion(String razonSocial, TipoDeOrganizacion tipoDeOrganizacion,
                      Collection<Area> sectores, Clasificacion clasificacion, Transformador transformador) {
    this.razonSocial = razonSocial;
    this.tipoDeOrganizacion = tipoDeOrganizacion;
    this.sectores = sectores;
    this.clasificacion = clasificacion;
    this.transformador = transformador;
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public TipoDeOrganizacion getTipoDeOrganizacion() {
    return tipoDeOrganizacion;
  }

  public void setTipoDeOrganizacion(TipoDeOrganizacion tipoDeOrganizacion) {
    this.tipoDeOrganizacion = tipoDeOrganizacion;
  }

  public Collection<Area> getSectores() {
    return sectores;
  }

  public void setSectores(Collection<Area> sectores) {
    this.sectores = sectores;
  }

  public Clasificacion getClasificacion() {
    return clasificacion;
  }

  public void setClasificacion(Clasificacion clasificacion) {
    this.clasificacion = clasificacion;
  }

  public void agregarArea(Area area){
    sectores.add(area);
  }
/*
  public boolean tieneArea(Area area){
    return sectores.contains(area);
  }
*/
}
