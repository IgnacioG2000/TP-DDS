package domain.organizacion;

import domain.huellaDeCarbono.espacio.EspacioDeTrabajo;
import domain.huellaDeCarbono.trayecto.Tramo;
import domain.huellaDeCarbono.trayecto.Trayecto;
import domain.miembro.AgenteSectorial;
import domain.miembro.Miembro;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

  public Double calcularHuellaCarbonoTotalArea(LocalDate fecha, boolean esMensual) {
    Collection<Trayecto> trayectosDeFecha;
    if(esMensual){
      trayectosDeFecha = trayectosRegistados.stream().filter(trayecto -> trayecto.perteneceMes(fecha)).collect(Collectors.toList());
    }else{
      trayectosDeFecha = trayectosRegistados.stream().filter(trayecto -> trayecto.perteneceAnio(fecha)).collect(Collectors.toList());
    }

    Set<Tramo> tramos = trayectosDeFecha
        .stream().map(Trayecto::getTramos)
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());

    Double hcTramos = tramos.stream().mapToDouble(unTramo -> {
      try {
        return unTramo.calcularHuellaCarbonoTramo();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return 0;
    }).sum();

    //TODO: SACAR
    /*
    if(esMensual){
      hcTramos = hcTramos * 20;//20 dias
    }else {
      hcTramos = hcTramos * 20 * 12;//20 dias 12 meses
    }
     */

    return hcTramos;
  }

  public Double calcularHuellaCarbonoPromedioMiembro(LocalDate fecha, boolean esMensual){
    return this.calcularHuellaCarbonoTotalArea(fecha, esMensual) / miembros.size();
  }

  public List<Trayecto> getTrayectosDelMiembro(Miembro miembro) {
    List<Trayecto> trayectosMiembro = trayectosRegistados.stream().filter(trayecto -> trayecto.perteneceMiembro(miembro)).collect(Collectors.toList());
    return trayectosMiembro;
  }

  public boolean perteneceAArea(AgenteSectorial agenteSectorial) {
    return espacioDeTrabajo.getProvincia().equals(agenteSectorial.getSectorTerritorial())
        || espacioDeTrabajo.getMunicipio().equals(agenteSectorial.getSectorTerritorial());
  }
}
