package domain.miembro;

import domain.huellaDeCarbono.CalculadoraHC.ValorHCMensual;
import domain.organizacion.*;
import domain.huellaDeCarbono.trayecto.*;
import repositorios.RepoOrganizacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Miembro {
  private Persona persona;
  private Area area;
  private List<ValorHCMensual> valorHCMensuales;

  public Miembro(Persona persona) {
    this.persona = persona;
    this.valorHCMensuales = new ArrayList<>();
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
  public Double calcularHuellaCarbonoMiembroMensual(int anio, int mes){
    Double hcMiembro;
    if(valorHCMensuales.stream().noneMatch(valorHCMensual -> valorHCMensual.soyMes(anio,mes))) {
      Collection<Trayecto> listaTrayectosDelMiembro = area.getTrayectosDelMiembro(this);
      hcMiembro = ManejadorTrayectos.getInstance().calcularHCMensual(listaTrayectosDelMiembro, anio, mes);
      System.out.print("hc miembro en calcular huella carbodno mensual:" + hcMiembro + "\n");
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


  public Double calcularHuellaCarbonoMiembroAnual(int anio){
    Collection<Trayecto> listaTrayectosDelMiembro = area.getTrayectosDelMiembro(this);
    Double hcMiembro = ManejadorTrayectos.getInstance().calcularHCAnual(listaTrayectosDelMiembro, anio);

    return hcMiembro;
  }

  public Double impactoMiembroEnOrganizacionAnual(int anual){
    Organizacion miOrg =  RepoOrganizacion.getInstance().encontrarOrganizacion(area);
    Double hcMiOrg = miOrg.calcularHuellaCarbonoTotalAnio(anual);
    return this.calcularHuellaCarbonoMiembroAnual(anual) / hcMiOrg * 100;
  }
}
