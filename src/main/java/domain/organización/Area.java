package domain.organización;

import domain.huellaDeCarbono.espacio.Espacio;
import domain.huellaDeCarbono.espacio.EspacioDeTrabajo;
import domain.huellaDeCarbono.espacio.Hogar;
import domain.huellaDeCarbono.espacio.TipoDeHogar;
import domain.huellaDeCarbono.medioDeTransporte.MedioDeTransporte;
import domain.huellaDeCarbono.medioDeTransporte.ServicioContratado;
import domain.huellaDeCarbono.medioDeTransporte.TipoServicioContratado;
import domain.huellaDeCarbono.trayecto.Tramo;
import domain.huellaDeCarbono.trayecto.Trayecto;
import domain.miembro.Miembro;

import java.io.IOException;
import java.text.CollationElementIterator;
import java.util.ArrayList;
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

  public Double calcularHuellaCarbonoTotalArea() {
    Set<Tramo> tramos = this
        .getTrayectosRegistados()
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

    return hcTramos * 20;//sumar HC de DA
    //TODO: REVISAR LA MULTIPLICACION 20(dias al mes)
    /*
        .collect(Collectors.toSet())
        .stream()
        .reduce();

    Hogar espacio1 = new Hogar(2.0,3.0,"CABA","ASD","Pepita","Deloria","1445",147,"PP", 5, "H", TipoDeHogar.CASA);
    ServicioContratado sc = new ServicioContratado(10.0, TipoServicioContratado.REMIS);
    Collection<Miembro> coleccionMiembroVacia = new ArrayList<>();
    Tramo tramo1 = new Tramo(espacio1, espacio1, sc, coleccionMiembroVacia);
    Tramo tramo2 = new Tramo(espacio1, espacio1, sc, coleccionMiembroVacia);
    Tramo tramo3 = new Tramo(espacio1, espacio1, sc, coleccionMiembroVacia);
    Collection<Tramo> tramos1 = new ArrayList<>();
    tramos1.add(tramo1);
    tramos1.add(tramo2);
    Collection<Tramo> tramos2 = new ArrayList<>();
    tramos1.add(tramo3);
    tramos1.add(tramo2);
    Trayecto tray1 = new Trayecto(espacio1,espacio1, tramos1);
    Trayecto tray2 = new Trayecto(espacio1,espacio1, tramos2);
    getTrayectosRegistados().add(tray1);
    getTrayectosRegistados().add(tray2);



  */
  }

}
