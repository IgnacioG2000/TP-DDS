package com.disenio.mimagrupo06.domain.organizacion;

import com.disenio.mimagrupo06.domain.sector.Sector;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.EspacioDeTrabajo;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
public class Area {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nombre;

  @OneToMany
  @JoinColumn(name = "area_id")
  private Collection<Miembro> miembros;

  @OneToMany
  @JoinColumn(name = "area_pend_id")
  private Collection<Miembro> miembrosPend;

  @ManyToOne
  private EspacioDeTrabajo espacioDeTrabajo;
  @OneToMany
  @JoinColumn(name = "area_id")
  private Collection<Trayecto> trayectosRegistados;
  @OneToMany
  @JoinColumn(name = "area_id")
  private Collection<Trayecto> trayectosPendientes;

  public Area(String nombre, Collection<Miembro> miembros, EspacioDeTrabajo espacioDeTrabajo) {
    this.nombre = nombre;
    this.miembros = miembros;
    this.miembrosPend = new ArrayList<>();
    this.espacioDeTrabajo = espacioDeTrabajo;
    this.trayectosRegistados = new ArrayList<>();
    this.trayectosPendientes = new ArrayList<>();
  }

  public Area(){

  }

  public String getNombre() {
    return nombre;
  }

  public Collection<Trayecto> getTrayectosPendientes() {
    return trayectosPendientes;
  }

  public void setTrayectosPendientes(Collection<Trayecto> trayectosPendientes) {
    this.trayectosPendientes = trayectosPendientes;
  }

  public void aceptarVinculacion(Trayecto trayectoAceptado){
    this.trayectosPendientes.remove(trayectoAceptado);
    this.trayectosRegistados.add(trayectoAceptado);
  }

  public void agregarVinculacion(Trayecto trayectoVinculado){
    this.trayectosPendientes.add(trayectoVinculado);
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Collection<Miembro> getMiembros() {
    return miembros;
  }

  public void setMiembros(Collection<Miembro> miembros) {
    this.miembros = miembros;
}

  public EspacioDeTrabajo getEspacioDeTrabajo() {
    return espacioDeTrabajo;
  }

  public void setEspacioDeTrabajo(EspacioDeTrabajo espacioDeTrabajo) {
    this.espacioDeTrabajo = espacioDeTrabajo;
  }

  public void registrarMiembro(Miembro miembro){
    miembros.add(miembro);
  }

  public Collection<Trayecto> getTrayectosRegistados() {
    return trayectosRegistados;
  }

  public void setTrayectosRegistados(Collection<Trayecto> trayectos) {
    this.trayectosRegistados = trayectos;
  }


  public double calcularHuellaCarbonoTotalAreaAnual(int anio){
    double hcAnual = miembros.stream().mapToDouble(miembro->miembro.calcularHuellaCarbonoMiembroAnual(anio)).sum();
    return hcAnual;
  }

  public double calcularHuellaCarbonoTotalAreaMensual(int anio, int mes) {
    double hcMensual = miembros.stream().mapToDouble(miembro->miembro.calcularHuellaCarbonoMiembroMensual(anio,mes)).sum();
    return hcMensual;
  }
  public double calcularHuellaCarbonoPromedioMiembroMensual(int anio, int mes){
    return this.calcularHuellaCarbonoTotalAreaMensual(anio, mes) / miembros.size();
  }

  public double calcularHuellaCarbonoPromedioMiembroAnual(int anio){
    return this.calcularHuellaCarbonoTotalAreaAnual(anio) / miembros.size();
  }

  public Collection<Trayecto> getTrayectosDelMiembro(Miembro miembro) {
    Collection<Trayecto> trayectosMiembro = trayectosRegistados.stream().filter(trayecto -> trayecto.perteneceMiembro(miembro)).collect(Collectors.toList());
    return trayectosMiembro;
  }

  public boolean perteneceSector(Sector sector) {
    return espacioDeTrabajo.getProvincia().equals(sector.getNombre())
        || espacioDeTrabajo.getMunicipio().equals(sector.getNombre()) && espacioDeTrabajo.getProvincia().equals(sector.nombreProvincia());
  }

  public Collection<Miembro> getMiembrosPend() {
    return miembrosPend;
  }

  public void solicitudMiembro(Miembro miembro){
    this.miembrosPend.add(miembro);
  }
  public void vincularTrabajador(Miembro miembro){
    this.miembrosPend.remove(miembro);
    registrarMiembro(miembro);
  }
  public void rechazarTrabajador(Miembro miembro){
    this.miembrosPend.remove(miembro);
  }

  public void setMiembrosPend(Collection<Miembro> miembrosPend) {
    this.miembrosPend = miembrosPend;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
