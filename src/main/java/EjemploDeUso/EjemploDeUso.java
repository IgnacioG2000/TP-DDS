package EjemploDeUso;

import apiDistancia.ApiDistancia;
import apiDistancia.ListadoPais;
import apiDistancia.Pais;
import apiDistancia.ServicioApiDistancia;

import java.io.IOException;

public class EjemploDeUso {


  public static void main(String[] args) throws IOException {
    ServicioApiDistancia servicioApiDistancia = ServicioApiDistancia.getInstancia();
    System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

    ListadoPais listadoPaises = servicioApiDistancia.listadoDePais();

     System.out.println(listadoPaises.getPaises().get(0).getNombre());

  }
}