package domain.miembro;

import domain.organización.*;
import domain.huellaDeCarbono.trayecto.*;
import repositorios.RepoOrganizacion;

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

  public void cargarProyecto(Trayecto trayecto){
    Organizacion org = RepoOrganizacion.getInstance().encontrarOrganizacion(area);
    org.agregarVinculacion(trayecto);
  }
}
