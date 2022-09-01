package EjemploDeUso;

import apiDistancia.*;
import domain.huellaDeCarbono.espacio.*;
import domain.huellaDeCarbono.medioDeTransporte.MedioDeTransporte;
import domain.huellaDeCarbono.medioDeTransporte.TipoTransportePublico;
import domain.huellaDeCarbono.medioDeTransporte.TransportePublico;
import domain.huellaDeCarbono.trayecto.Tramo;
import domain.huellaDeCarbono.trayecto.Trayecto;
import domain.miembro.Miembro;
import domain.miembro.Persona;
import domain.miembro.TipoDocumento;
import domain.organizacion.Area;
import seguridad.roles.Usuario;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class EjemploDeUso {


  public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
    /*ServicioApiDistancia servicioApiDistancia = ServicioApiDistancia.getInstancia();
   // System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");
  String idPais = new String();
  String idProvincia = new String();
  String idMunicipio = new String();
  List<Pais> listadoPaises = servicioApiDistancia.listadoDePais(1);

    List<Municipio> listadoMunicipios = servicioApiDistancia.listadoMunicipios( "168");

    if(listadoMunicipios.size() == 0) {
      System.out.print("Estoy retornando algo nulo");
    }else {
      System.out.print("Municipio: " + listadoMunicipios.get(1).getNombre() + "\n");
      idMunicipio = listadoMunicipios.get(1).getId();
      System.out.print("ID Municipio: " + idMunicipio + "\n");
    }

   // FORMA 2 Descomentar dsp de testear

    Espacio espacioOrigen = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "unaCalle", "Alturacalle", 1992 );
    Espacio espacioDestino = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "unaCalle", "Alturacalle", 1992);


    Double dist = servicioApiDistancia.obtenerDistancia(espacioOrigen, espacioDestino);


    System.out.print("Distancia Valor: " + dist + "\n");

    // System.out.print("lalala safe");*/

  }

}