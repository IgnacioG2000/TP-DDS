package com.disenio.mimagrupo06.domain.organizacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.excel_ETL.DatoDeLaActividad;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.notificadores.ManejadorEvento;
import com.disenio.mimagrupo06.notificadores.MedioNotificacion;
import com.disenio.mimagrupo06.notificadores.Notificacion;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import org.springframework.scheduling.annotation.Scheduled;


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
  private Collection<DatoDeLaActividad> datoDeLaActividad;
  @Enumerated(EnumType.ORDINAL)
  private MedioNotificacion mediosNotificacion;
  @Transient
  private OrganizacionService organizacionService;

  @OneToOne
  private UsuarioOrganizacion usuarioOrganizacion;


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
    return organizacionService.calcularHuellaCarbonoTotalAnio(anio,this);
  }

  /*
  public void cargarDatosActividad(String path) {
    Transformador.getInstance().cargarDatos(this, path);
  }
  */
  public Collection<DatoDeLaActividad> getDatosDeLaActividad() {
    return datoDeLaActividad;
  }

  public void setDatosDeLaActividad(Collection<DatoDeLaActividad> datoDeLaActividad) {
    this.datoDeLaActividad = datoDeLaActividad;
  }

  public double calcularHuellaCarbonoTotalMensual(int anio, int mes) {
    return organizacionService.calcularHuellaCarbonoTotalMensual(anio,mes,this);
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

  @Scheduled(cron = "0 0/1 0 ? * 6 ")
  public void enviarRecomendacion() {
    Notificacion noti = new Notificacion("Recomendaciones",
        "Link a las recomendaciones: zaraza");
    ManejadorEvento.getInstancia().notificar(noti,this);
    System.out.println("Mando reco");
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

  public OrganizacionService getOrganizacionService() {
    return organizacionService;
  }

  public void setOrganizacionService(OrganizacionService organizacionService) {
    this.organizacionService = organizacionService;
  }

  public UsuarioOrganizacion getUsuarioOrganizacion() {
    return usuarioOrganizacion;
  }

  public void setUsuarioOrganizacion(UsuarioOrganizacion usuarioOrganizacion) {
    this.usuarioOrganizacion = usuarioOrganizacion;
  }
}
