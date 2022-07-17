package domain.miembro;

import domain.organizacion.*;
import domain.huellaDeCarbono.trayecto.*;
import repositorios.RepoOrganizacion;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Miembro  {
  private Persona persona;
  private Area area;


  public Miembro(Persona persona, Area area) {
    this.persona = persona;
    this.area = area;
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

  public void cargarTrayecto(Trayecto trayecto){
    area.agregarVinculacion(trayecto);
  }

  public Double calcularHuellaCarbonoMiembro(LocalDate fecha, boolean esMensual){
    List<Trayecto> listaTrayectosDelMiembro = area.getTrayectosDelMiembro(this);
    List<Trayecto> listaTrayectosFechaMiembro;
    if(esMensual){
      listaTrayectosFechaMiembro = listaTrayectosDelMiembro.stream().filter(trayecto -> trayecto.perteneceMes(fecha)).collect(Collectors.toList());
    }else{
      listaTrayectosFechaMiembro = listaTrayectosDelMiembro.stream().filter(trayecto -> trayecto.perteneceAnio(fecha)).collect(Collectors.toList());
    }
    Double hcMiembro = listaTrayectosFechaMiembro.stream().mapToDouble(Trayecto::calcularHuellaCarbonoTotalTrayecto).sum();
    return hcMiembro;
  }

  public Double impactoMiembroEnOrganizacion( LocalDate fecha, boolean esMensual){
    Organizacion miOrg =  RepoOrganizacion.getInstance().encontrarOrganizacion(area);
    Double hcMiOrg = miOrg.calcularHuellaCarbonoTotalFecha(fecha, esMensual);
    return this.calcularHuellaCarbonoMiembro(fecha, esMensual) / hcMiOrg * 100;
  }
}
