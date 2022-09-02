package domain.huellaDeCarbono.CalculadoraHC;

import apiDistancia.ArchivoConfig;
import excel_ETL.DatosDeLaActividad;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CalculadoraHCActividad {
  Collection<TipoActividad> tiposActividad = new ArrayList<>();

  public CalculadoraHCActividad() {
    TipoActividad gasNatural = new TipoActividad("Gas Natural", "m3",10);
    TipoActividad dieselGasoil = new TipoActividad("dieselGasoil", "lt",7);
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

    tiposActividad.addAll(Arrays.asList(gasNatural, dieselGasoil, kerosene, fuelOil, nafta,
        carbon, carbonDeLenia, lenia, combustibleGasoil, combustibleGNC, combustibleNafta,
        electricidad, distMediaRecorrida, pesoTotalTransportado));
  }

  public void setFactorEmisionDeTipoActividad(DatosDeLaActividad dato, Double fe) {
    TipoActividad tipoActividad = obtenerTipoActividad(dato);
    tipoActividad.setFe(fe);
  }

  private TipoActividad obtenerTipoActividad(DatosDeLaActividad dato){
    //recorrer tiposActividad y devolver el que cumple
    return tiposActividad
        .stream()
        .filter(tipoActividad -> Objects.equals(tipoActividad.getNombre(), dato.getTipoDeConsumo())).collect(Collectors.toList())
        .get(0);
  }

  public double calcularHuellaCarbonoLogProdRes(Collection<DatosDeLaActividad> datos)  throws IOException {

      Double K = ArchivoConfig.obtenerValorK();
      Double factorEmision;



      //NO HACEMOS PASAJE ENTRE KG Y KM PORQUE YA VIENEN BIEN "Distancia Media Recorrida"
      List<DatosDeLaActividad> aux = datos.stream().filter(dato -> (dato.getTipoDeConsumo().equals("Distancia Media Recorrida"))).collect(Collectors.toList());

      DatosDeLaActividad datoDistancia = aux.get(0);
      DatosDeLaActividad datoPeso = datos.stream().filter(dato -> (dato.getTipoDeConsumo().equals("Peso Total Transportado"))).collect(Collectors.toList()).get(0);
      DatosDeLaActividad datoTransporte = datos.stream().filter(dato -> (dato.getTipoDeConsumo().matches("Medio de Transporte: .+"))).collect(Collectors.toList()).get(0);

      //aca usamos el arch para conseguir los FE: NO tenemos INSTANCIA de camiones o lugar donde guardarlo PARA INSTANCIARLO
      if (Objects.equals(datoTransporte.getTipoDeConsumo(), "Medio de Transporte: Camion de carga"))
      {
        factorEmision = ArchivoConfig.obtenerFECamion();
      } else {
        factorEmision = ArchivoConfig.obtenerFEUtilitario();
      }
    return datoDistancia.getConsumo().getValor() * datoPeso.getConsumo().getValor() * K * factorEmision;
  }

  public double calcularHuellaCarbonoCombElec(DatosDeLaActividad dato) {
    Double distanciaDato = dato.getConsumo().getValor();
    TipoActividad tipoActividad = this.obtenerTipoActividad(dato);
    Double fe = tipoActividad.getFe();
    return distanciaDato * fe;
  }

  public double calcularHCActividadAnual(Collection<DatosDeLaActividad> datos, int anio) {
    Collection<DatosDeLaActividad> datosActividad= datos.stream().filter(dato -> dato.perteneceAnio(anio)).collect(Collectors.toList());

    Collection<DatosDeLaActividad> combElec = datosActividad
        .stream()
        .filter(unDato -> !unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    Collection<DatosDeLaActividad> logProdRes = datosActividad
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

  public double calcularHCActividadMensual(Collection<DatosDeLaActividad> datos, int anio, int mes) {

    //Estas son las actividades mensuales que se calculan de forma total
    Collection<DatosDeLaActividad> datosActividad= datos.stream().filter(dato -> dato.perteneceMesAnio(anio, mes)).collect(Collectors.toList());

    //Estas son las actividades anuales, las cuales se calculan y luego dividen por la cantidad de meses del año
    Collection<DatosDeLaActividad> datosActividadSoloAnio= datos.stream().filter(dato -> dato.perteneceSoloAnio(anio)).collect(Collectors.toList());

    //Separacion por Actividad en periodo Mensual
    Collection<DatosDeLaActividad> combElec = datosActividad
        .stream()
        .filter(unDato -> !unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    Collection<DatosDeLaActividad> logProdRes = datosActividad
        .stream()
        .filter(unDato-> unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());


    //Separacion por Actividad en periodo Anual
    Collection<DatosDeLaActividad> combElecAnio = datosActividadSoloAnio
        .stream()
        .filter(unDato -> !unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    Collection<DatosDeLaActividad> logProdResAnio = datosActividadSoloAnio
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
}
