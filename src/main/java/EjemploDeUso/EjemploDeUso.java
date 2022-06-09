package EjemploDeUso;

import apiDistancia.Pais;
import apiDistancia.ServicioApiDistancia;

import java.io.IOException;
import java.util.List;

public class EjemploDeUso {


  public static void main(String[] args) throws IOException {
    ServicioApiDistancia servicioApiDistancia = ServicioApiDistancia.getInstancia();
   // System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

  List<Pais> listadoPaises = servicioApiDistancia.listadoDePais();


    if(listadoPaises.size() == 0) {
      System.out.print("Estoy retornando algo nulo");
    }else {
      System.out.print(listadoPaises.get(0).getNombre());
    }
  }
}