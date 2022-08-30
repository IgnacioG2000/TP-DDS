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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seguridad.roles.Usuario;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculoHCTest {


    Espacio espacioOrigen = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "maipu", "100", 1992);
    Espacio espacioDestino = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "O'Higgins", "200", 1992);
    EspacioDeTrabajo espacioTrabajoArea = new EspacioDeTrabajo(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "O'Higgins", "200", 1992,2, "A");
    MedioDeTransporte medioDeTransporte1 = new TransportePublico(10, TipoTransportePublico.TREN, "Tren Roca" );
    Hogar hogarGuido = new Hogar(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "unaCalle", "Alturacalle", 1992, "unbarrio", 3, "Hola", TipoDeHogar.CASA);
    Usuario usuarioGuido = new Usuario("Guido2000", "contra123");
    Persona personaGuido = new Persona("Guido", "Serco", TipoDocumento.DNI, "4256565656", hogarGuido, usuarioGuido);
    Miembro miembroGuido = new Miembro(personaGuido);
    Area area = new Area("Area1", Arrays.asList(miembroGuido), espacioTrabajoArea);
    Tramo tramo = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte1, Arrays.asList(miembroGuido));
    Trayecto trayecto = new Trayecto(espacioOrigen,espacioDestino,Arrays.asList(tramo), LocalDate.of(2022, 1, 1), 5);

  public CalculoHCTest() throws NoSuchAlgorithmException {
  }


  @Test
  public void calculoHCMiembro(){
    assertEquals(miembroGuido.calcularHuellaCarbonoMiembroMensual(2022,7),187.15);
  }
}
