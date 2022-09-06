package com.disenio.mimagrupo06.domain.organizacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.notificadores.ManejadorEvento;
import com.disenio.mimagrupo06.notificadores.Notificacion;


import java.util.ArrayList;
import java.util.Collection;


public class Organizacion {
  private String razonSocial;
  private TipoDeOrganizacion tipoDeOrganizacion;
  private Collection<Area> sectores;
  private Collection<Contacto> contactos;
  private Clasificacion clasificacion;
  private CalculadoraHCActividad calculadoraHCActividad;
  private ManejadorEvento manejadorEvento;

  public Organizacion(String razonSocial, TipoDeOrganizacion tipoDeOrganizacion, Collection<Area> sectores,
      Clasificacion clasificacion, CalculadoraHCActividad calculadoraHCActividad, ManejadorEvento manejadorEvento) {
    this.razonSocial = razonSocial;
    this.tipoDeOrganizacion = tipoDeOrganizacion;
    this.sectores = sectores;
    this.clasificacion = clasificacion;
    this.calculadoraHCActividad = calculadoraHCActividad;
    this.manejadorEvento = manejadorEvento;
    this.contactos = new ArrayList<>();
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

  public void agregarArea(Area area) {
    sectores.add(area);
  }

  public double calcularHuellaCarbonoTotalAnio(int anio) {
    double hcAreas = sectores.stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaAnual(anio)).sum();
    double hcActividad = calculadoraHCActividad.calcularHCActividadAnual(Transformador.getInstance().getDatosDeLaActividad(), anio);
    return hcActividad + hcAreas;
  }

  public double calcularHuellaCarbonoTotalMensual(int anio, int mes) {
    double hcAreas = sectores.stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaMensual(anio, mes)).sum();
    double hcActividad = calculadoraHCActividad.calcularHCActividadMensual(Transformador.getInstance().getDatosDeLaActividad(), anio, mes);
    return hcActividad + hcAreas;
  }

  public boolean tieneArea(Area area) {
    return sectores.contains(area);
  }

  public void agregarContacto(String mail, String num) {
    contactos.add(new Contacto(mail, num));
  }

  public Collection<Contacto> getContactos() {
    return contactos;
  }

  public void enviarRecomendacion() {
    Notificacion noti = new Notificacion("Recomendaciones",
        "Link a las recomendaciones: zaraza");
    this.manejadorEvento.notificar(noti,this);
  }
}
