package com.disenio.mimagrupo06.domain.organizacion;

import com.disenio.mimagrupo06.domain.sector.Sector;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.EspacioDeTrabajo;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;


import java.util.Collection;
import java.util.stream.Collectors;

public class Area {
  private String nombre;
  private Collection<Miembro> miembros;
  private EspacioDeTrabajo espacioDeTrabajo;
  private Collection<Trayecto> trayectosRegistados;

  public Area(String nombre, Collection<Miembro> miembros, EspacioDeTrabajo espacioDeTrabajo, Collection<Trayecto> trayectosRegistados) {
    this.nombre = nombre;
    this.miembros = miembros;
    this.espacioDeTrabajo = espacioDeTrabajo;
    this.trayectosRegistados = trayectosRegistados;
  }

  public String getNombre() {
    return nombre;
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
}
