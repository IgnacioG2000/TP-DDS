import apiDistancia.ServicioApiDistancia;
import domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import domain.huellaDeCarbono.espacio.*;
import domain.huellaDeCarbono.medioDeTransporte.*;
import domain.huellaDeCarbono.trayecto.Tramo;
import domain.huellaDeCarbono.trayecto.Trayecto;
import domain.miembro.Miembro;
import domain.miembro.Persona;
import domain.miembro.TipoDocumento;
import domain.organizacion.Area;
import excel_ETL.Consumo;
import excel_ETL.DatosDeLaActividad;
import excel_ETL.Transformador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seguridad.roles.Usuario;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculoHCTest {

  private static final DecimalFormat df = new DecimalFormat("0.00");

  Espacio espacioOrigen = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "maipu", "100", 1992);
  Espacio espacioDestino = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "O'Higgins", "200", 1992);
  EspacioDeTrabajo espacioTrabajoArea = new EspacioDeTrabajo(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "O'Higgins", "200", 1992,2, "A");
  MedioDeTransporte medioDeTransporte1 = new TransportePublico(10, TipoTransportePublico.TREN, "Tren Roca" );
  MedioDeTransporte medioDeTransporte2 = new VehiculoParticular(5, TipoVehiculo.AUTO,TipoCombustible.NAFTA);
  Hogar hogarGuido = new Hogar(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "unaCalle", "Alturacalle", 1992, "unbarrio", 3, "Hola", TipoDeHogar.CASA);
  Usuario usuarioGuido = new Usuario("Guido2000", "contraCOntraKCRF123");
  Persona personaGuido = new Persona("Guido", "Serco", TipoDocumento.DNI, "4256565656", hogarGuido, usuarioGuido);
  Miembro miembroGuido = new Miembro(personaGuido);
  Hogar hogarTaylor = new Hogar(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "unaCalle", "Alturacalle", 1992, "unbarrio", 3, "Hola", TipoDeHogar.CASA);
  Usuario usuarioTaylor = new Usuario("Taylor1234", "djf8ree245");
  Persona personaTaylor = new Persona("Taylor", "Swift", TipoDocumento.DNI, "367789999", hogarTaylor, usuarioTaylor);
  Miembro miembroTaylor = new Miembro(personaTaylor);
  Usuario usuarioJake = new Usuario("Jake_The_Taylor_Hater", "Taylor_hater_4_life");
  Persona personaJake = new Persona("Jake", "Gyllenhaal", TipoDocumento.DNI, "4256565656", hogarGuido, usuarioJake);
  Miembro miembroJake = new Miembro(personaJake);
  Tramo tramo1 = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte1, Arrays.asList(miembroGuido));
  Tramo tramo2 = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte2, Arrays.asList(miembroTaylor,miembroJake));
  Tramo tramo3 = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte2, Arrays.asList(miembroTaylor));
  Trayecto trayecto1 = new Trayecto(espacioOrigen,espacioDestino,Arrays.asList(tramo1), LocalDate.of(2021, 1, 1),5);
  Trayecto trayecto2 = new Trayecto(espacioOrigen,espacioDestino,Arrays.asList(tramo2), LocalDate.of(2021, 1, 1),3);
  Trayecto trayecto3 = new Trayecto(espacioOrigen,espacioDestino,Arrays.asList(tramo3), LocalDate.of(2021, 1, 1),2);
  Area area = new Area("Area1", Arrays.asList(miembroGuido), espacioTrabajoArea,Arrays.asList(trayecto1),null);
  Area area4Ever21 = new Area("Area4Ever21", Arrays.asList(miembroTaylor,miembroJake), espacioTrabajoArea,Arrays.asList(trayecto2,trayecto3),null);
  //Area areaTrabajadores = new Area("Area4Ever21", Arrays.asList(miembroTaylor,miembroGuido), espacioTrabajoArea,Arrays.asList(trayecto2,trayecto3),null);

  CalculadoraHCActividad calculadoraHCActividad = new CalculadoraHCActividad();

  //area con multiples miembros con tramo compartido
  //area con multiples miembros sin tramo compartido
  //area con un solo miembro
  //miembro con multples tramos de diferente medioTransporte
  //miembro con 2 trayectos al mismo tiempo activos Taylor


  @Test
  public void calculoHCMiembroEnElMismoMes(){
    miembroGuido.setArea(area);
    double dist = tramo1.getDistancia();
    System.out.print("DISTANCIA: " + dist);
    assertEquals(df.format(medioDeTransporte1.getFactorEmision() * dist * 4.5 * 5), df.format(miembroGuido.calcularHuellaCarbonoMiembroMensual(2021,1)));
  }

  @Test
  public void calculoHCMiembroSinTrayecto(){
    miembroGuido.setArea(area);
    assertEquals(0.0, miembroGuido.calcularHuellaCarbonoMiembroMensual(2020,1));
  }

  public CalculoHCTest() throws NoSuchAlgorithmException, IOException {
  }

  @Test
  public void calculoHCMiembroMesTrayectoTerminado() {
    miembroGuido.setArea(area);
    double dist = tramo1.getDistancia();
    System.out.print("DISTANCIA: " + dist);
    System.out.print("FE: " + medioDeTransporte1.getFactorEmision() );
    trayecto1.setFechaFin(LocalDate.now());
    assertEquals(df.format(medioDeTransporte1.getFactorEmision() * dist * 4.5 * 5), df.format(miembroGuido.calcularHuellaCarbonoMiembroMensual(2021,7)));
  }

  @Test
  public void calculoHCMiembroAnioVencido() {
    miembroGuido.setArea(area);
    double dist = tramo1.getDistancia();
    System.out.print("DISTANCIA: " + dist);
    System.out.print("FE: " + medioDeTransporte1.getFactorEmision() );
    assertEquals(df.format(medioDeTransporte1.getFactorEmision() * dist * 4.5 * 12.0 * 5), df.format(miembroGuido.calcularHuellaCarbonoMiembroAnual(2021)));
  }

  @Test
  public void calculoHCMiembroAnioCorriendo() {
    miembroGuido.setArea(area);
    double dist = tramo1.getDistancia();
    System.out.print("DISTANCIA: " + dist);
    System.out.print("FE: " + medioDeTransporte1.getFactorEmision() );
    assertEquals(df.format(medioDeTransporte1.getFactorEmision() * dist * 4.5 * (LocalDate.now().getMonthValue()-1) * 5), df.format(miembroGuido.calcularHuellaCarbonoMiembroAnual(2022)));
  }

  //area con multiples miembros con tramo compartido
  @Test
  public void calculoHCAnualAreaConMiembrosTramoCompartido() {
    miembroJake.setArea(area4Ever21);
    miembroTaylor.setArea(area4Ever21);
    double dist2 = tramo2.getDistancia();
    double dist3 = tramo3.getDistancia();
    assertEquals(df.format((medioDeTransporte2.getFactorEmision() * dist2 * 4.5 * 0.6 + medioDeTransporte2.getFactorEmision() * dist3 * 4.5 * 0.4)* 12 * 5) , df.format(area4Ever21.calcularHuellaCarbonoTotalAreaAnual(2021)));
  }

  @Test
  public void calculoHCMensualAreaConMiembrosTramoCompartido() {
    miembroJake.setArea(area4Ever21);
    miembroTaylor.setArea(area4Ever21);
    double dist2 = tramo2.getDistancia();
    double dist3 = tramo3.getDistancia();
    assertEquals( df.format(medioDeTransporte2.getFactorEmision() * dist2 * 4.5 * 0.6 * 5 + medioDeTransporte2.getFactorEmision() * dist3 * 4.5 * 0.4 * 5) , df.format(area4Ever21.calcularHuellaCarbonoTotalAreaMensual(2021,8)));
  }

  //area con un solo miembro
  @Test
  public void calculoHCAnualArea() {
    miembroGuido.setArea(area);
    double dist = tramo1.getDistancia();
    System.out.print("DISTANCIA: " + dist);
    System.out.print("FE: " + medioDeTransporte1.getFactorEmision() );
    assertEquals(df.format(medioDeTransporte1.getFactorEmision() * dist * 4.5 * 12 * 5), df.format(area.calcularHuellaCarbonoTotalAreaAnual(2021)));
  }
//TODO
  /*
  @Test
  public void calculamosHCActividadCombustionElectricidad() {

    Transformador.getInstance().cargarDatos("\\src\\main\\java\\excel_ETL\\excelTesteo.xls");
    List<DatosDeLaActividad> datosDeLaActividad = Transformador.getInstance().getDatosDeLaActividad();

    assertEquals(df.format(3 * 10), df.format(calculadoraHCActividad.calcularHuellaCarbonoCombElec(datosDeLaActividad.get(0))));
  }
*/
  //TODO revisar cuando ponemos solo el año y ver el tema del prorrateo
  @Test
  public void calculamosHCActividadCombustionElectricidadMensual() {

    Transformador.getInstance().cargarDatos("\\src\\main\\java\\excel_ETL\\excelTesteo.xls");
    Collection<DatosDeLaActividad> datosDeLaActividad = Transformador.getInstance().getDatosDeLaActividad();

    assertEquals(df.format(3 * 10), df.format(calculadoraHCActividad.calcularHCActividadMensual(datosDeLaActividad,2021,01)));
  }

  @Test
  public void calculamosHCActividadCombustionElectricidadAnual() {

    Transformador.getInstance().cargarDatos("\\src\\main\\java\\excel_ETL\\excelTesteo.xls");
    Collection<DatosDeLaActividad> datosDeLaActividad = Transformador.getInstance().getDatosDeLaActividad();

    assertEquals(df.format(2 * 7 + 5 * 10), df.format(calculadoraHCActividad.calcularHCActividadAnual(datosDeLaActividad,2022)));
  }
  @Test
  public void calculamosHCActividadLogProdResMensual() {


  }

  @Test
  public void calculamosHCActividadLogProdResAnual() {

  }
}
