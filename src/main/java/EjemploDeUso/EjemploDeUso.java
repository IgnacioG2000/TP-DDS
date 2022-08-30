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
    Espacio espacioOrigen = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "maipu", "100", 1992);
    Espacio espacioDestino = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "O'Higgins", "200", 1992);
    EspacioDeTrabajo espacioTrabajoArea = new EspacioDeTrabajo(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "O'Higgins", "200", 1992,2, "A");
    MedioDeTransporte medioDeTransporte1 = new TransportePublico(10, TipoTransportePublico.TREN, "Tren Roca" );
    Hogar hogarGuido = new Hogar(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "unaCalle", "Alturacalle", 1992, "unbarrio", 3, "Hola", TipoDeHogar.CASA);
    Usuario usuarioGuido = new Usuario("Guido2000", "contraCOntraKCRF123");
    Persona personaGuido = new Persona("Guido", "Serco", TipoDocumento.DNI, "4256565656", hogarGuido, usuarioGuido);
    Miembro miembroGuido = new Miembro(personaGuido);
    Tramo tramo = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte1, Arrays.asList(miembroGuido));
    Trayecto trayecto1 = new Trayecto(espacioOrigen,espacioDestino,Arrays.asList(tramo), LocalDate.of(2022, 1, 1), 5);
    Area area = new Area("Area1", Arrays.asList(miembroGuido), espacioTrabajoArea,Arrays.asList(trayecto1),null);

    miembroGuido.setArea(area);
    Double dist = ServicioApiDistancia.getInstancia().obtenerDistancia(espacioOrigen, espacioDestino);
    System.out.print("DISTANCIA: " + dist);
    System.out.print("FE: " + medioDeTransporte1.getFactorEmision() );
    trayecto1.setFechaFin(LocalDate.now());
    System.out.print("Expected: "+medioDeTransporte1.getFactorEmision() * dist * 4.5);
    System.out.print("Actual: "+ miembroGuido.calcularHuellaCarbonoMiembroMensual(2022,1));
  }

}