package com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC;

import com.disenio.mimagrupo06.apiDistancia.ArchivoConfig;
import com.disenio.mimagrupo06.excel_ETL.DatoDeLaActividad;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import com.disenio.mimagrupo06.repositorios.RepoTA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalculadoraHCActividad {
  private List<TipoActividad> tiposActividad;

  private RepoTA ta;
/*
  @Bean
  public CalculadoraHCActividad calculadoraHCActividad() {
    CalculadoraHCActividad calculadoraHCActividad = new CalculadoraHCActividad();
    return calculadoraHCActividad;
  }
*/
  public void setFactorEmisionDeTipoActividad(DatoDeLaActividad dato, Double fe) {
    TipoActividad tipoActividad = obtenerTipoActividad(dato);
    tipoActividad.setFe(fe);
  }

  private TipoActividad obtenerTipoActividad(DatoDeLaActividad dato){
    //recorrer tiposActividad y devolver el que cumple
    List<TipoActividad> tiposActividad = ta.findAll();
    return tiposActividad
        .stream()
        .filter(tipoActividad -> Objects.equals(tipoActividad.getNombre(), dato.getTipoDeConsumo())).collect(Collectors.toList())
        .get(0);
  }

  public double calcularHuellaCarbonoLogProdRes(Collection<DatoDeLaActividad> datos)  throws IOException {

      Double K = ArchivoConfig.obtenerValorK();
      Double factorEmision;



      //NO HACEMOS PASAJE ENTRE KG Y KM PORQUE YA VIENEN BIEN "Distancia Media Recorrida"
      List<DatoDeLaActividad> aux = datos.stream().filter(dato -> (dato.getTipoDeConsumo().equals("Distancia Media Recorrida"))).collect(Collectors.toList());

      DatoDeLaActividad datoDistancia = aux.get(0);
      DatoDeLaActividad datoPeso = datos.stream().filter(dato -> (dato.getTipoDeConsumo().equals("Peso Total Transportado"))).collect(Collectors.toList()).get(0);
      DatoDeLaActividad datoTransporte = datos.stream().filter(dato -> (dato.getTipoDeConsumo().matches("Medio de Transporte: .+"))).collect(Collectors.toList()).get(0);

      //aca usamos el arch para conseguir los FE: NO tenemos INSTANCIA de camiones o lugar donde guardarlo PARA INSTANCIARLO
      if (Objects.equals(datoTransporte.getTipoDeConsumo(), "Medio de Transporte: Camion de carga"))
      {
        factorEmision = ArchivoConfig.obtenerFECamion();
      } else {
        factorEmision = ArchivoConfig.obtenerFEUtilitario();
      }
    return datoDistancia.getConsumo().getValor() * datoPeso.getConsumo().getValor() * K * factorEmision;
  }

  public double calcularHuellaCarbonoCombElec(DatoDeLaActividad dato) {
    Double valor = dato.getConsumo().getValor();
    TipoActividad tipoActividad = this.obtenerTipoActividad(dato);
    Double fe = tipoActividad.getFe();
    return valor * fe;
  }

  public double calcularHCActividadAnual(Collection<DatoDeLaActividad> datos, int anio) {
    Collection<DatoDeLaActividad> datosActividad= datos.stream().filter(dato -> dato.perteneceAnio(anio)).collect(Collectors.toList());

    Collection<DatoDeLaActividad> combElec = datosActividad
        .stream()
        .filter(unDato -> !unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    Collection<DatoDeLaActividad> logProdRes = datosActividad
        .stream()
        .filter(unDato-> unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    double hcCombElec = combElec.stream().mapToDouble(this::calcularHuellaCarbonoCombElec).sum();
    double hcLogProdRes=0.0;

    if(logProdRes.size() >= 4){
      try{
        hcLogProdRes = this.calcularHuellaCarbonoLogProdRes(logProdRes);
      }catch (IOException e) {
        e.printStackTrace();
      }
    }
    return hcCombElec + hcLogProdRes;
  }

  public double calcularHCActividadMensual(Collection<DatoDeLaActividad> datos, int anio, int mes) {

    //Estas son las actividades mensuales que se calculan de forma total
    Collection<DatoDeLaActividad> datosActividad = datos.stream().filter(dato -> dato.perteneceMesAnio(anio, mes)).collect(Collectors.toList());

    //Estas son las actividades anuales, las cuales se calculan y luego dividen por la cantidad de meses del año
    Collection<DatoDeLaActividad> datosActividadSoloAnio = datos.stream().filter(dato -> dato.perteneceSoloAnio(anio)).collect(Collectors.toList());

    //Separacion por Actividad en periodo Mensual
    Collection<DatoDeLaActividad> combElec = datosActividad
        .stream()
        .filter(unDato -> !unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    Collection<DatoDeLaActividad> logProdRes = datosActividad
        .stream()
        .filter(unDato-> unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());


    //Separacion por Actividad en periodo Anual
    Collection<DatoDeLaActividad> combElecAnio = datosActividadSoloAnio
        .stream()
        .filter(unDato -> !unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    Collection<DatoDeLaActividad> logProdResAnio = datosActividadSoloAnio
        .stream()
        .filter(unDato-> unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    //Calculo HC de las actividades del Mes
    double hcMes;
    double hcCombElec = combElec.stream().mapToDouble(this::calcularHuellaCarbonoCombElec).sum();
    double hcLogProdRes=0.0;

    if(logProdRes.size() >= 4){
      try{
        hcLogProdRes = this.calcularHuellaCarbonoLogProdRes(logProdRes);
      }catch (IOException e) {
        e.printStackTrace();
      }
    }

    hcMes = hcCombElec + hcLogProdRes;

    //Calculo HC de las actividades del Anio, posteriormente dividido por los meses vencidos del anio
    double hcAnio;
    double hcCombElecAnio = combElecAnio.stream().mapToDouble(this::calcularHuellaCarbonoCombElec).sum();
    double hcLogProdResAnio=0.0;

    if(logProdResAnio.size() >= 4){
      try{
        hcLogProdResAnio = this.calcularHuellaCarbonoLogProdRes(logProdResAnio);
      }catch (IOException e) {
        e.printStackTrace();
      }
    }

    hcAnio = hcCombElecAnio + hcLogProdResAnio;

    int mesesVencidos;
    if(anio != LocalDate.now().getYear()){
      mesesVencidos = 12;
    }else{
      mesesVencidos = LocalDate.now().getMonthValue()-1;
    }

    return hcMes + hcAnio/mesesVencidos;

  }

  public void cargarFE() {

    TipoActividad gasNatural = new TipoActividad("Gas Natural", "m3",10);
    TipoActividad dieselGasoil = new TipoActividad("DieselGasoil", "lt",7);
    TipoActividad kerosene = new TipoActividad("Kerosene", "lt",5);
    TipoActividad fuelOil= new TipoActividad("Fuel Oil", "lt",8);
    TipoActividad nafta = new TipoActividad("Nafta", "lt",9);
    TipoActividad carbon = new TipoActividad("Carbon", "kg",10);
    TipoActividad carbonDeLenia = new TipoActividad("Carbon de Lenia", "kg",5);
    TipoActividad lenia = new TipoActividad("Lenia", "kg",8);
    TipoActividad combustibleGasoil = new TipoActividad("Combustible Gasoil", "lt",7);
    TipoActividad combustibleGNC = new TipoActividad("Combustible GNC", "lt",8);
    TipoActividad combustibleNafta = new TipoActividad("Combustible Nafta", "lt",7);
    TipoActividad electricidad = new TipoActividad("Electricidad", "Kwh",10);
    TipoActividad distMediaRecorrida = new TipoActividad("Distancia Media Recorrida", "km",6);
    TipoActividad pesoTotalTransportado = new TipoActividad("Peso Total Transportado", "kg",4);
    TipoActividad materiaPrima = new TipoActividad("Materia Prima", " ",1);
    TipoActividad camionUtilitario = new TipoActividad("Medio de Transporte: Camion utilitario", " ",1);
    TipoActividad camionCarga = new TipoActividad("Medio de Transporte: Camion de carga", " ",1);

    if (tiposActividad == null)
      {
        System.out.println("Está la lista en nulo!!");
      }else {
        System.out.println("No está nula, va a arrancar a guardar");
     tiposActividad.addAll(Arrays.asList(gasNatural, dieselGasoil, kerosene, fuelOil, nafta,
              carbon, carbonDeLenia, lenia, combustibleGasoil, combustibleGNC, combustibleNafta,
              electricidad, distMediaRecorrida, pesoTotalTransportado, materiaPrima, camionUtilitario, camionCarga));

      tiposActividad.forEach(da->ta.save(da));
  }
  }

  public RepoTA getTa() {
    return ta;
  }

  public void setTa(RepoTA ta) {
    this.ta = ta;
  }
}
