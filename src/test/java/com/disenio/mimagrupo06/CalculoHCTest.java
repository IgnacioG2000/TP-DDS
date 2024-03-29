package com.disenio.mimagrupo06;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.*;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte.*;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.domain.miembro.TipoDocumento;
import com.disenio.mimagrupo06.domain.organizacion.*;
import com.disenio.mimagrupo06.excel_ETL.DatoDeLaActividad;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.repositorios.RepoTA;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioComun;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculoHCTest {

  private static final DecimalFormat df = new DecimalFormat("0.00");
  @Autowired
  private RepoTA repoTA;

  Espacio espacioOrigen = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "maipu", "100", 1992);
  Espacio espacioDestino = new Parada(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "O'Higgins", "200", 1992);
  EspacioDeTrabajo espacioTrabajoArea = new EspacioDeTrabajo(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "O'Higgins", "200", 1992,2, "A");
  MedioDeTransporte medioDeTransporte1 = new TransportePublico(TipoTransportePublico.TREN, "Tren Roca" );
  MedioDeTransporte medioDeTransporte2 = new VehiculoParticular(TipoVehiculo.AUTO,TipoCombustible.NAFTA);
  Hogar hogarGuido = new Hogar(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "unaCalle", "Alturacalle", 1992, 3, "Hola", TipoDeHogar.CASA);
  UsuarioComun usuarioGuido = new UsuarioComun("Guido2000", "contraCOntraKCRF123");
  Persona personaGuido = new Persona("Guido", "Serco",TipoDocumento.DNI, "4256565656", hogarGuido, usuarioGuido);
  Miembro miembroGuido = new Miembro(personaGuido);
  Hogar hogarTaylor = new Hogar(1.0, 1.0, "BUENOS AIRES", "ADOLFO ALSINA", "CARHUE", "unaCalle", "Alturacalle", 1992, 3, "Hola", TipoDeHogar.CASA);
  UsuarioComun usuarioTaylor = new UsuarioComun("Taylor1234", "djf8ree245");
  Persona personaTaylor = new Persona("Taylor", "Swift", TipoDocumento.DNI, "367789999", hogarTaylor, usuarioTaylor);
  Miembro miembroTaylor = new Miembro(personaTaylor);
  UsuarioComun usuarioJake = new UsuarioComun("Jake_The_Taylor_Hater", "Taylor_hater_4_life");
  Persona personaJake = new Persona("Jake", "Gyllenhaal", TipoDocumento.DNI, "4256565656", hogarGuido, usuarioJake);
  Miembro miembroJake = new Miembro(personaJake);
  Tramo tramo1 = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte1, Arrays.asList(miembroGuido));
  Tramo tramo2 = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte2, Arrays.asList(miembroTaylor,miembroJake));
  Tramo tramo3 = new Tramo(espacioOrigen, espacioDestino, medioDeTransporte2, Arrays.asList(miembroTaylor));
  Trayecto trayecto1 = new Trayecto(espacioOrigen,espacioDestino,Arrays.asList(tramo1), LocalDate.of(2021, 1, 1),5);
  Trayecto trayecto2 = new Trayecto(espacioOrigen,espacioDestino,Arrays.asList(tramo2), LocalDate.of(2021, 1, 1),3);
  Trayecto trayecto3 = new Trayecto(espacioOrigen,espacioDestino,Arrays.asList(tramo3), LocalDate.of(2021, 1, 1),2);
  Area area = new Area("Area1", Arrays.asList(miembroGuido), espacioTrabajoArea);
  Area area4Ever21 = new Area("Area4Ever21", Arrays.asList(miembroTaylor,miembroJake), espacioTrabajoArea);
  //Area areaTrabajadores = new Area("Area4Ever21", Arrays.asList(miembroTaylor,miembroGuido), espacioTrabajoArea,Arrays.asList(trayecto2,trayecto3),null);
  Organizacion organizacion = new Organizacion("Nueva Seguro", TipoDeOrganizacion.EMPRESA, Arrays.asList(area4Ever21),
      Clasificacion.MINISTERIO);
  OrganizacionService organizacionService = new OrganizacionService();
  Transformador transformador = new Transformador();
  CalculadoraHCActividad calculadoraHCActividad = new CalculadoraHCActividad();

  @Test
  public void calculoHCMiembroEnElMismoMes(){
    miembroGuido.setArea(area);
    area.agregarVinculacion(trayecto1);
    area.aceptarVinculacion(trayecto1);
    double dist = tramo1.getDistancia();
    assertEquals((int) (medioDeTransporte1.getFactorEmision() * dist * 4.5 * 5), (int)(miembroGuido.calcularHuellaCarbonoMiembroMensual(2021,1)));
  }

  @Test
  public void calculoHCMiembroSinTrayecto(){
    miembroGuido.setArea(area);
    area.agregarVinculacion(trayecto1);
    area.aceptarVinculacion(trayecto1);
    assertEquals(0.0, miembroGuido.calcularHuellaCarbonoMiembroMensual(2020,1));
  }

  public CalculoHCTest() throws NoSuchAlgorithmException, IOException {
  }

  @Test
  public void calculoHCMiembroMesTrayectoTerminado() {
    miembroGuido.setArea(area);
    area.agregarVinculacion(trayecto1);
    area.aceptarVinculacion(trayecto1);
    double dist = tramo1.getDistancia();
    trayecto1.setFechaFin(LocalDate.now());
    assertEquals((int) (medioDeTransporte1.getFactorEmision() * dist * 4.5 * 5), (int) (miembroGuido.calcularHuellaCarbonoMiembroMensual(2021,7)));
  }

  @Test
  public void calculoHCMiembroAnioVencido() {
    miembroGuido.setArea(area);
    area.agregarVinculacion(trayecto1);
    area.aceptarVinculacion(trayecto1);
    double dist = tramo1.getDistancia();
    assertEquals((int) (medioDeTransporte1.getFactorEmision() * dist * 4.5 * 12.0 * 5), (int) (miembroGuido.calcularHuellaCarbonoMiembroAnual(2021)));
  }

  @Test
  public void calculoHCMiembroAnioCorriendo() {
    miembroGuido.setArea(area);
    area.agregarVinculacion(trayecto1);
    area.aceptarVinculacion(trayecto1);
    double dist = tramo1.getDistancia();
    assertEquals((int) (medioDeTransporte1.getFactorEmision() * dist * 4.5 * (LocalDate.now().getMonthValue()-1) * 5), (int) (miembroGuido.calcularHuellaCarbonoMiembroAnual(2022)));
  }

  //area con multiples miembros con tramo compartido
  @Test
  public void calculoHCAnualAreaConMiembrosTramoCompartido() {
    miembroJake.setArea(area4Ever21);
    miembroTaylor.setArea(area4Ever21);
    area4Ever21.agregarVinculacion(trayecto2);
    area4Ever21.agregarVinculacion(trayecto3);
    area4Ever21.aceptarVinculacion(trayecto2);
    area4Ever21.aceptarVinculacion(trayecto3);
    double dist2 = tramo2.getDistancia();
    double dist3 = tramo3.getDistancia();
    assertEquals((int) ((medioDeTransporte2.getFactorEmision() * dist2 * 4.5 * 0.6 + medioDeTransporte2.getFactorEmision() * dist3 * 4.5 * 0.4)* 12 * 5) , (int) (area4Ever21.calcularHuellaCarbonoTotalAreaAnual(2021)));
  }

  @Test
  public void calculoHCMensualAreaConMiembrosTramoCompartido() {
    miembroJake.setArea(area4Ever21);
    miembroTaylor.setArea(area4Ever21);
    area4Ever21.agregarVinculacion(trayecto2);
    area4Ever21.agregarVinculacion(trayecto3);
    area4Ever21.aceptarVinculacion(trayecto2);
    area4Ever21.aceptarVinculacion(trayecto3);
    double dist2 = tramo2.getDistancia();
    double dist3 = tramo3.getDistancia();
    assertEquals( (int) (medioDeTransporte2.getFactorEmision() * dist2 * 4.5 * 0.6 * 5 + medioDeTransporte2.getFactorEmision() * dist3 * 4.5 * 0.4 * 5) , (int) (area4Ever21.calcularHuellaCarbonoTotalAreaMensual(2021,8)));
  }

  //area con un solo miembro
  @Test
  public void calculoHCAnualArea() {
    miembroGuido.setArea(area);
    area.agregarVinculacion(trayecto1);
    area.aceptarVinculacion(trayecto1);
    double dist = tramo1.getDistancia();
    assertEquals((int) (medioDeTransporte1.getFactorEmision() * dist * 4.5 * 12 * 5), (int) (area.calcularHuellaCarbonoTotalAreaAnual(2021)));
  }

  //TODO revisar cuando ponemos solo el año y ver el tema del prorrateo
  @Test
  public void calculamosHCActividadCombustionElectricidadMensual() {
    transformador.setTa(repoTA);
    organizacionService.setTransformador(transformador);
    organizacionService.setCalculadoraHCActividad(calculadoraHCActividad);
    organizacion.setOrganizacionService(organizacionService);
    calculadoraHCActividad.setTa(repoTA);
    organizacionService.cargarDatosActividad("/src/main/java/com/disenio/mimagrupo06/excel_ETL/excelTesteo.xls",organizacion);
    //organizacion.cargarDatosActividad("/src/main/java/com/disenio/mimagrupo06/excel_ETL/excelTesteo.xls");
    Collection<DatoDeLaActividad> datoDeLaActividad = transformador.getDatosDeLaActividad();


    assertEquals(df.format(3 * 10 + 9 * 14 * 10 * 1.1), df.format(calculadoraHCActividad.calcularHCActividadMensual(datoDeLaActividad,2021,01)));
  }

  @Test
  public void calculamosHCActividadCombustionElectricidadAnual() {
    transformador.setTa(repoTA);
    organizacionService.setTransformador(transformador);
    organizacionService.setCalculadoraHCActividad(calculadoraHCActividad);
    calculadoraHCActividad.setTa(repoTA);
    organizacionService.cargarDatosActividad("/src/main/java/com/disenio/mimagrupo06/excel_ETL/excelTesteo.xls",organizacion);
    Collection<DatoDeLaActividad> datoDeLaActividad = transformador.getDatosDeLaActividad();

    assertEquals(2 * 7 + 5 * 10, (int) (calculadoraHCActividad.calcularHCActividadAnual(datoDeLaActividad,2022)));
  }
  @Test
  public void calculamosHCActividadLogProdResMensual() {
    transformador.setTa(repoTA);
    organizacionService.setTransformador(transformador);
    organizacionService.setCalculadoraHCActividad(calculadoraHCActividad);
    organizacion.setOrganizacionService(organizacionService);
    calculadoraHCActividad.setTa(repoTA);
    organizacionService.cargarDatosActividad("/src/main/java/com/disenio/mimagrupo06/excel_ETL/excelTesteo.xls",organizacion);
    Collection<DatoDeLaActividad> datoDeLaActividad = transformador.getDatosDeLaActividad();

    assertEquals(12*9*10*1.2,(int) calculadoraHCActividad.calcularHCActividadMensual(datoDeLaActividad,2019,04));
  }

  @Test
  public void calculamosHCActividadLogProdResAnual() {
    transformador.setTa(repoTA);
    organizacionService.setTransformador(transformador);
    organizacionService.setCalculadoraHCActividad(calculadoraHCActividad);
    organizacion.setOrganizacionService(organizacionService);
    calculadoraHCActividad.setTa(repoTA);
    organizacionService.cargarDatosActividad("/src/main/java/com/disenio/mimagrupo06/excel_ETL/excelTesteo.xls",organizacion);
    Collection<DatoDeLaActividad> datoDeLaActividad = transformador.getDatosDeLaActividad();

    assertEquals(8*15*10*1.1,(int) calculadoraHCActividad.calcularHCActividadAnual(datoDeLaActividad,2020));
  }

  @Test
  public void calculamosHCOrganizacionMensual() {
    transformador.setTa(repoTA);
    organizacionService.setTransformador(transformador);
    organizacionService.setCalculadoraHCActividad(calculadoraHCActividad);
    organizacion.setOrganizacionService(organizacionService);
    calculadoraHCActividad.setTa(repoTA);
    organizacionService.cargarDatosActividad("/src/main/java/com/disenio/mimagrupo06/excel_ETL/excelTesteo.xls",organizacion);
    miembroJake.setArea(area4Ever21);
    miembroTaylor.setArea(area4Ever21);
    area4Ever21.agregarVinculacion(trayecto2);
    area4Ever21.agregarVinculacion(trayecto3);
    area4Ever21.aceptarVinculacion(trayecto2);
    area4Ever21.aceptarVinculacion(trayecto3);
    double dist2 = tramo2.getDistancia();
    double dist3 = tramo3.getDistancia();
    double hcTrayecto = medioDeTransporte2.getFactorEmision() * dist2 * 4.5 * 0.6 * 5 + medioDeTransporte2.getFactorEmision() * dist3 * 4.5 * 0.4 * 5;
    double calcHCombElec = 3 * 10;
    double calcHCLogProdRes = 9 * 14 * 10 * 1.1;

    assertEquals((int) (hcTrayecto+calcHCombElec+calcHCLogProdRes),
        (int) organizacionService.calcularHuellaCarbonoTotalMensual(2021, 01,organizacion));
  }

  @Test
  public void calculamosHCOrganizacionAnio() {
    transformador.setTa(repoTA);
    organizacionService.setTransformador(transformador);
    organizacionService.setCalculadoraHCActividad(calculadoraHCActividad);
    organizacion.setOrganizacionService(organizacionService);
    calculadoraHCActividad.setTa(repoTA);
    organizacionService.cargarDatosActividad("/src/main/java/com/disenio/mimagrupo06/excel_ETL/excelTesteo.xls",organizacion);
    miembroJake.setArea(area4Ever21);
    miembroTaylor.setArea(area4Ever21);
    area4Ever21.agregarVinculacion(trayecto2);
    area4Ever21.agregarVinculacion(trayecto3);
    area4Ever21.aceptarVinculacion(trayecto2);
    area4Ever21.aceptarVinculacion(trayecto3);
    double dist2 = tramo2.getDistancia();
    double dist3 = tramo3.getDistancia();
    double hcTrayecto = (medioDeTransporte2.getFactorEmision() * dist2 * 4.5 * 0.6 + medioDeTransporte2.getFactorEmision() * dist3 * 4.5 * 0.4) * 12 * 5;
    double calcHCombElec = 3 * 10 + 7 * 9;
    double calcHCLogProdRes = 9 * 14 * 10 * 1.1;

    assertEquals((int) (hcTrayecto+calcHCombElec+calcHCLogProdRes),
        (int) organizacionService.calcularHuellaCarbonoTotalAnio(2021, organizacion));
  }
}
