package com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto;

import java.util.Collection;
import java.util.stream.Collectors;

public class ManejadorTrayectos {
  //recibe por parametro la lista Trayectos con los q trabaja
  //SINGLETON: siempre la misma logica y no es necesaria multiples instancias

  private static final ManejadorTrayectos INSTANCE = new ManejadorTrayectos();

  public static ManejadorTrayectos getInstance() {
    return INSTANCE;
  }

  public double calcularHCMensual(Collection<Trayecto> listaTrayectos, int anio, int mes) {
    Collection<Trayecto> trayectosATratar;
    trayectosATratar = listaTrayectos.stream().filter(trayecto -> trayecto.perteneceMes(anio, mes)).collect(Collectors.toList());

    double hcMensual =trayectosATratar.stream().mapToDouble(trayecto -> trayecto.calcularHCTrayectoMensual()).sum();

    return hcMensual;
  }

}
