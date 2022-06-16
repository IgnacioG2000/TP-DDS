package EjemploDeUso;

import apiDistancia.*;
import domain.huellaDeCarbono.espacio.Espacio;
import domain.huellaDeCarbono.espacio.Hogar;
import domain.huellaDeCarbono.espacio.Parada;

import java.io.IOException;
import java.util.List;

public class EjemploDeUso {


  public static void main(String[] args) throws IOException {
    ServicioApiDistancia servicioApiDistancia = ServicioApiDistancia.getInstancia();
   // System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");
  String idPais = new String();
  String idProvincia = new String();
  String idMunicipio = new String();
  List<Pais> listadoPaises = servicioApiDistancia.listadoDePais(1);

    List<Municipio> listadoMunicipios = servicioApiDistancia.listadoMunicipios(1, "168");

    if(listadoMunicipios.size() == 0) {
      System.out.print("Estoy retornando algo nulo");
    }else {
      System.out.print("Municipio: " + listadoMunicipios.get(1).getNombre() + "\n");
      idMunicipio = listadoMunicipios.get(1).getId();
      System.out.print("ID Municipio: " + idMunicipio + "\n");
    }

   // FORMA 2 Descomentar dsp de testear

    Espacio espacioOrigen = new Parada(1.0, 1.0, "RIVERA", "ADOLFO ALSINA", "unaCalle", "BUENOS A   IRES", "Alturacalle", 1992, "unbarrio" );
    Espacio espacioDestino = new Parada(1.0, 1.0, "CARHUE", "ADOLFO ALSINA", "unaCalle", "BUENOS AIRES", "Alturacalle", 1992, "unbarrio" );

    Double dist = servicioApiDistancia.obtenerDistancia(espacioOrigen, espacioDestino);


   // System.out.print("Distancia Valor: " + dist + "\n");

    System.out.print("lalala safe");
  }
}