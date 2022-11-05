package com.disenio.mimagrupo06.domain.miembro;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.ValorHCMensualMiembro;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.*;
import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;


import javax.persistence.*;
import java.time.LocalDate;
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
  @ManyToOne
  private Area area;
  @OneToMany
  @JoinColumn(name = "miembro_id")
  private List<ValorHCMensualMiembro> valorHCMensualMiembros;

  public Miembro(Persona persona) {
    this.persona = persona;
    this.valorHCMensualMiembros = new ArrayList<>();
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
    if(valorHCMensualMiembros.stream().noneMatch(valorHCMensualMiembro -> valorHCMensualMiembro.soyMes(anio,mes))) {
      Collection<Trayecto> listaTrayectosDelMiembro = area.getTrayectosDelMiembro(this);
      hcMiembro = ManejadorTrayectos.getInstance().calcularHCMensual(listaTrayectosDelMiembro, anio, mes);
      agregarHCMensual(anio, mes, hcMiembro);

    }else{
      hcMiembro = valorHCMensualMiembros
          .stream()
          .filter(valorHCMensualMiembro -> valorHCMensualMiembro.soyMes(anio,mes))
          .collect(Collectors.toList())
          .get(0)
          .getHuellaCarbono();
    }
    return hcMiembro;
  }

  private void agregarHCMensual(int anio, int mes, Double hcMiembro) {
    ValorHCMensualMiembro valorHC = new ValorHCMensualMiembro(anio, mes, hcMiembro);
    valorHCMensualMiembros.add(valorHC);
  }


  public double calcularHuellaCarbonoMiembroAnual(int anio){

    //Conseguir anio
    int meses;

    //conseguir cantidad de meses de dicho anio
    if(anio != LocalDate.now().getYear()){
      meses = 12;
    }else{
      meses = LocalDate.now().getMonthValue()-1;
    }

    double hcMiembro=0.0;

    for(int i =1; i<=meses;i++){
      hcMiembro += this.calcularHuellaCarbonoMiembroMensual(anio,i);
    }

    return hcMiembro;
  }

  public double impactoMiembroEnOrganizacionAnual(int anual){
    Organizacion miOrg =  RepoOrganizacion.getInstance().encontrarOrganizacion(area);
    double hcMiOrg = miOrg.calcularHuellaCarbonoTotalAnio(anual);
    return this.calcularHuellaCarbonoMiembroAnual(anual) / hcMiOrg * 100;
  }

  public double impactoMiembroEnOrganizacionMensual(int anio, int mes){
    Organizacion miOrg =  RepoOrganizacion.getInstance().encontrarOrganizacion(area);
    double hcMiOrg = miOrg.calcularHuellaCarbonoTotalMensual(anio, mes);
    return this.calcularHuellaCarbonoMiembroMensual(anio, mes) / hcMiOrg * 100;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<ValorHCMensualMiembro> getValorHCMensuales() {
    return valorHCMensualMiembros;
  }

  public void setValorHCMensuales(List<ValorHCMensualMiembro> valorHCMensualeMiembros) {
    this.valorHCMensualMiembros = valorHCMensualeMiembros;
  }



}
