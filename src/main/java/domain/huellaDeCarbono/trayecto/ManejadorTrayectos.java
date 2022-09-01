package domain.huellaDeCarbono.trayecto;

import repositorios.RepoOrganizacion;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
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

  public double calcularHCAnual(Collection<Trayecto> listaTrayectos, int anio){

    //Conseguir anio
    int meses;
    double hcTotal = 0.0;


    //conseguir cantidad de meses de dicho anio
    if(anio != LocalDate.now().getYear()){
      meses = 12;
    }else{
      meses = LocalDate.now().getMonthValue()-1;
    }

    //iterar por dichos meses
    for(int i = 1;i<=meses;i++){
        hcTotal+= this.calcularHCMensual(listaTrayectos, anio, i);
    }

    return hcTotal;
  }

}
