package com.disenio.mimagrupo06.domain.organizacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.notificadores.ManejadorEvento;
import com.disenio.mimagrupo06.notificadores.Notificacion;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Organizacion {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String razonSocial;
  @Enumerated(EnumType.ORDINAL)
  private TipoDeOrganizacion tipoDeOrganizacion;
  @OneToMany
  @JoinColumn(name = "organizacion_id")
  private Collection<Area> sectores;
  @OneToMany
  @JoinColumn(name = "organizacion_id")
  private Collection<Contacto> contactos;
  @Enumerated(EnumType.ORDINAL)
  private Clasificacion clasificacion;
  //TODO
  @Transient
  private ManejadorEvento manejadorEvento;

  public Organizacion(String razonSocial, TipoDeOrganizacion tipoDeOrganizacion, Collection<Area> sectores,
      Clasificacion clasificacion, ManejadorEvento manejadorEvento) {
    this.razonSocial = razonSocial;
    this.tipoDeOrganizacion = tipoDeOrganizacion;
    this.sectores = sectores;
    this.clasificacion = clasificacion;
    this.manejadorEvento = manejadorEvento;
    this.contactos = new ArrayList<>();
  }

  public Organizacion() {

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
    double hcActividad = CalculadoraHCActividad.getCalculadoraHCActividad().calcularHCActividadAnual(Transformador.getInstance().getDatosDeLaActividad(), anio);
    return hcActividad + hcAreas;
  }

  public double calcularHuellaCarbonoTotalMensual(int anio, int mes) {
    double hcAreas = sectores.stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaMensual(anio, mes)).sum();
    double hcActividad = CalculadoraHCActividad.getCalculadoraHCActividad().calcularHCActividadMensual(Transformador.getInstance().getDatosDeLaActividad(), anio, mes);
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setContactos(Collection<Contacto> contactos) {
    this.contactos = contactos;
  }

  public ManejadorEvento getManejadorEvento() {
    return manejadorEvento;
  }

  public void setManejadorEvento(ManejadorEvento manejadorEvento) {
    this.manejadorEvento = manejadorEvento;
  }
}
