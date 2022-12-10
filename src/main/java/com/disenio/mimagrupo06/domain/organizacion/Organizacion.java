package com.disenio.mimagrupo06.domain.organizacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.ValorHCMensualMiembro;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.ValorHCMensualOrganizacion;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.ManejadorTrayectos;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.excel_ETL.DatoDeLaActividad;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.notificadores.ManejadorEvento;
import com.disenio.mimagrupo06.notificadores.MedioNotificacion;
import com.disenio.mimagrupo06.notificadores.Notificacion;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import org.springframework.scheduling.annotation.Scheduled;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
  @OneToMany(cascade = {CascadeType.ALL})
  private Collection<DatoDeLaActividad> datoDeLaActividad;
  @Enumerated(EnumType.ORDINAL)
  private MedioNotificacion mediosNotificacion;
  @Transient
  private OrganizacionService organizacionService;
  @OneToMany(cascade = {CascadeType.ALL})
  @JoinColumn(name = "organizacion_id")
  private List<ValorHCMensualOrganizacion> valoresHCMensualOrganizacion;

  @OneToOne
  private UsuarioOrganizacion usuarioOrganizacion;


  public Organizacion(String razonSocial, TipoDeOrganizacion tipoDeOrganizacion, Collection<Area> sectores,
      Clasificacion clasificacion) {
    this.razonSocial = razonSocial;
    this.tipoDeOrganizacion = tipoDeOrganizacion;
    this.sectores = sectores;
    this.clasificacion = clasificacion;
    this.contactos = new ArrayList<>();
    this.valoresHCMensualOrganizacion = new ArrayList<>();
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
    //Conseguir anio
    int meses;

    //conseguir cantidad de meses de dicho anio
    if(anio != LocalDate.now().getYear()){
      meses = 12;
    }else{
      meses = LocalDate.now().getMonthValue()-1;
    }

    double hcOrg=0.0;

    for(int i =1; i<=meses;i++){
      hcOrg += this.calcularHuellaCarbonoTotalMensual(anio,i);
    }

    return hcOrg;
//    return organizacionService.calcularHuellaCarbonoTotalAnio(anio,this);

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

  private void agregarHCMensual(int anio, int mes, Double hcOrg) {
    ValorHCMensualOrganizacion valorHC = new ValorHCMensualOrganizacion(anio, mes, hcOrg);
    valoresHCMensualOrganizacion.add(valorHC);
  }

  public double calcularHuellaCarbonoTotalMensual(int anio, int mes) {
    double hcOrg;
    if(valoresHCMensualOrganizacion.stream().noneMatch(valorHCMensualOrg -> valorHCMensualOrg.soyMes(anio,mes))) {
      if(anio <= LocalDate.now().getYear()){
        hcOrg = organizacionService.calcularHuellaCarbonoTotalMensual(anio,mes,this);
        agregarHCMensual(anio, mes, hcOrg);
      } else {
        hcOrg = 0;
      }


    }else{
      hcOrg = valoresHCMensualOrganizacion
              .stream()
              .filter(valorHCMensualOrg -> valorHCMensualOrg.soyMes(anio,mes))
              .collect(Collectors.toList())
              .get(0)
              .getHuellaCarbono();
    }
    return hcOrg;
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
