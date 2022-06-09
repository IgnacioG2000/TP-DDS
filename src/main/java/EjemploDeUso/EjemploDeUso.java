package EjemploDeUso;

import apiDistancia.ApiDistancia;
import apiDistancia.ListadoPais;
import apiDistancia.Pais;
import apiDistancia.ServicioApiDistancia;

import java.io.IOException;
import java.util.List;

public class EjemploDeUso {


  public static void main(String[] args) throws IOException {
    ServicioApiDistancia servicioApiDistancia = ServicioApiDistancia.getInstancia();
   // System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

    Pais listadoPaises = servicioApiDistancia.listadoDePais();

    if(listadoPaises == null) {
      System.out.print("Estoy retornando algo nulo");
    }else {
      System.out.print("no retorne algo nulo");
    }
  }
}