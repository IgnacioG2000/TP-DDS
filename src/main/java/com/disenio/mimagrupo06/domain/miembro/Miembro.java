package com.disenio.mimagrupo06.domain.miembro;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.ValorHCMensual;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.*;
import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Miembro {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  private Persona persona;
  @Transient
  private Area area;
  @OneToMany
  @JoinColumn(name = "miembro_id")
  private List<ValorHCMensual> valorHCMensuales;

  public Miembro(Persona persona) {
    this.persona = persona;
    this.valorHCMensuales = new ArrayList<>();
  }

  public Miembro() {

  }

  public Persona getPersona() {
    return persona;
  }

  public void setPersona(Persona persona) {
    this.persona = persona;
  }

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }
/*
  public void cargarTrayecto(Trayecto trayecto){
    area.agregarVinculacion(trayecto);
  }
*/
  public double calcularHuellaCarbonoMiembroMensual(int anio, int mes){
    double hcMiembro;
    if(valorHCMensuales.stream().noneMatch(valorHCMensual -> valorHCMensual.soyMes(anio,mes))) {
      Collection<Trayecto> listaTrayectosDelMiembro = area.getTrayectosDelMiembro(this);
      hcMiembro = ManejadorTrayectos.getInstance().calcularHCMensual(listaTrayectosDelMiembro, anio, mes);
      agregarHCMensual(anio, mes, hcMiembro);

    }else{
      hcMiembro = valorHCMensuales
          .stream()
          .filter(valorHCMensual -> valorHCMensual.soyMes(anio,mes))
          .collect(Collectors.toList())
          .get(0)
          .getHuellaCarbono();
    }
    return hcMiembro;
  }

  private void agregarHCMensual(int anio, int mes, Double hcMiembro) {
    ValorHCMensual valorHC = new ValorHCMensual(anio, mes, hcMiembro);
    valorHCMensuales.add(valorHC);
  }


  public double calcularHuellaCarbonoMiembroAnual(int anio){
    Collection<Trayecto> listaTrayectosDelMiembro = area.getTrayectosDelMiembro(this);
    double hcMiembro = ManejadorTrayectos.getInstance().calcularHCAnual(listaTrayectosDelMiembro, anio);

    return hcMiembro;
  }

  public double impactoMiembroEnOrganizacionAnual(int anual){
    Organizacion miOrg =  RepoOrganizacion.getInstance().encontrarOrganizacion(area);
    double hcMiOrg = miOrg.calcularHuellaCarbonoTotalAnio(anual);
    return this.calcularHuellaCarbonoMiembroAnual(anual) / hcMiOrg * 100;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<ValorHCMensual> getValorHCMensuales() {
    return valorHCMensuales;
  }

  public void setValorHCMensuales(List<ValorHCMensual> valorHCMensuales) {
    this.valorHCMensuales = valorHCMensuales;
  }

}
