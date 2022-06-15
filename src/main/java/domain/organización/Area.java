package domain.organización;

import domain.huellaDeCarbono.espacio.EspacioDeTrabajo;
import domain.huellaDeCarbono.trayecto.Trayecto;
import domain.miembro.Miembro;
import java.util.Collection;

public class Area {
  private String nombre;
  private Collection<Miembro> miembros;
  private EspacioDeTrabajo espacioDeTrabajo;
  private Collection<Trayecto> trayectosRegistados;
  private Collection<Trayecto> trayectosPendientes;

  public Area(String nombre, Collection<Miembro> miembros, EspacioDeTrabajo espacioDeTrabajo) {
    this.nombre = nombre;
    this.miembros = miembros;
    this.espacioDeTrabajo = espacioDeTrabajo;
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

  public Collection<Trayecto> getTrayectosPendientes() {
    return trayectosPendientes;
  }

  public void setTrayectosPendientes(Collection<Trayecto> trayectosPendientes) {
    this.trayectosPendientes = trayectosPendientes;
  }

  public void agregarVinculacion(Trayecto trayecto) {
    trayectosPendientes.add(trayecto);
  }

  public void aceptarVinculacion(Trayecto trayecto) {
    trayectosPendientes.remove(trayecto);
    trayectosRegistados.add(trayecto);
  }

}
