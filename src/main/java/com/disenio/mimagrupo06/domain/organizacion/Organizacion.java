package com.disenio.mimagrupo06.domain.organizacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.excel_ETL.DatosDeLaActividad;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.notificadores.ManejadorEvento;
import com.disenio.mimagrupo06.notificadores.MedioNotificacion;
import com.disenio.mimagrupo06.notificadores.Notificacion;
import com.disenio.mimagrupo06.notificadores.Notificador;


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
  @OneToMany
  private Collection<DatosDeLaActividad> datosDeLaActividad;
  @Enumerated(EnumType.ORDINAL)
  private MedioNotificacion mediosNotificacion;

  public Organizacion(String razonSocial, TipoDeOrganizacion tipoDeOrganizacion, Collection<Area> sectores,
      Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipoDeOrganizacion = tipoDeOrganizacion;
    this.sectores = sectores;
    this.clasificacion = clasificacion;
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
    double hcActividad = CalculadoraHCActividad.getCalculadoraHCActividad().calcularHCActividadAnual(datosDeLaActividad, anio);
    return hcActividad + hcAreas;
  }

  public void cargarDatosActividad(String path) {
    Transformador.getInstance().cargarDatos(this, path);
  }

  public Collection<DatosDeLaActividad> getDatosDeLaActividad() {
    return datosDeLaActividad;
  }

  public void setDatosDeLaActividad(Collection<DatosDeLaActividad> datosDeLaActividad) {
    this.datosDeLaActividad = datosDeLaActividad;
  }

  public double calcularHuellaCarbonoTotalMensual(int anio, int mes) {
    double hcAreas = sectores.stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaMensual(anio, mes)).sum();
    double hcActividad = CalculadoraHCActividad.getCalculadoraHCActividad().calcularHCActividadMensual(datosDeLaActividad, anio, mes);
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
    ManejadorEvento.getInstancia().notificar(noti,this);
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

  public MedioNotificacion getMediosNotificacion() {
    return mediosNotificacion;
  }

  public void setMediosNotificacion(MedioNotificacion mediosNotificacion) {
    this.mediosNotificacion = mediosNotificacion;
  }

}
