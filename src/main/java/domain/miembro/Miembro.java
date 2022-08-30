package domain.miembro;

import domain.organizacion.*;
import domain.huellaDeCarbono.trayecto.*;
import repositorios.RepoOrganizacion;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Miembro {
  private Persona persona;
  private Area area;

  public Miembro(Persona persona) {
    this.persona = persona;
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
  //TODO Consultar si es necesario Anual Y Mensual
  public Double calcularHuellaCarbonoMiembroMensual(int anio, int mes){
    Collection<Trayecto> listaTrayectosDelMiembro = area.getTrayectosDelMiembro(this);
    Collection<Trayecto> listaTrayectosMiembroMes = listaTrayectosDelMiembro.stream().filter(trayecto -> trayecto.perteneceMes(anio, mes)).collect(Collectors.toList());
    System.out.println("lista trayectos del miembro: "+ listaTrayectosDelMiembro.size() + "\n" );
    System.out.println("lista trayectos del miembro mensual: "+ listaTrayectosMiembroMes.size() + "\n" );
    Double hcMiembro = listaTrayectosMiembroMes.stream().mapToDouble(Trayecto::calcularHCTrayectoMensual).sum();
    System.out.print("hc miembro en calcular huella carbodno mensual:" + hcMiembro + "\n");
    return hcMiembro;
  }


  public Double calcularHuellaCarbonoMiembroAnual(int anio){
    Collection<Trayecto> listaTrayectosDelMiembro = area.getTrayectosDelMiembro(this);
    Collection<Trayecto> listaTrayectosMiembroAnio = listaTrayectosDelMiembro.stream().filter(trayecto -> trayecto.perteneceAnio(anio)).collect(Collectors.toList());

    Double hcMiembro = listaTrayectosMiembroAnio.stream().mapToDouble(Trayecto::calcularHuellaCarbonoTotalTrayecto).sum();
    return hcMiembro;
  }

  //TODO Consultar si es necesario Anual y Mensual
  public Double impactoMiembroEnOrganizacionAnual(int anual){
    Organizacion miOrg =  RepoOrganizacion.getInstance().encontrarOrganizacion(area);
    Double hcMiOrg = miOrg.calcularHuellaCarbonoTotalAnio(anual);
    return this.calcularHuellaCarbonoMiembroAnual(anual) / hcMiOrg * 100;
  }
}
