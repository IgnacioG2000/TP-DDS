package domain.miembro;

import domain.organizacion.*;
import domain.huellaDeCarbono.trayecto.*;
import repositorios.RepoOrganizacion;

import java.util.List;

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

  public Double calcularHuellaCarbonoMiembro(){
    List<Trayecto> listaTrayectosDelMiembro = area.getTrayectosDelMiembro(this);
    Double hcMiembro = listaTrayectosDelMiembro.stream().mapToDouble(Trayecto::calcularHuellaCarbonoTotalTrayecto).sum();
    return hcMiembro;
  }

  public Double impactoMiembroEnOrganizacion(Double constanteDA){
    Organizacion miOrg =  RepoOrganizacion.getInstance().encontrarOrganizacion(area);
    Double hcMiOrg = miOrg.calcularHuellaCarbonoTotal(constanteDA);
    return this.calcularHuellaCarbonoMiembro() / hcMiOrg * 100;
  }
}
