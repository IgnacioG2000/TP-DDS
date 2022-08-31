package domain.huellaDeCarbono.CalculadoraHC;

import apiDistancia.ArchivoConfig;
import excel_ETL.DatosDeLaActividad;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CalculadoraHCActividad {
  Collection<TipoActividad> tiposActividad = new ArrayList<>();

  public CalculadoraHCActividad() {
    TipoActividad gasNatural = new TipoActividad("Gas Natural", new MetroCubico());
    TipoActividad dieselGasoil = new TipoActividad("dieselGasoil", new Litro());
    TipoActividad kerosene = new TipoActividad("Kerosene", new Litro());
    TipoActividad fuelOil= new TipoActividad("Fuel Oil", new Litro());
    TipoActividad nafta = new TipoActividad("Nafta", new Litro());
    TipoActividad carbon = new TipoActividad("Carbon", new Kilogramo());
    TipoActividad carbonDeLenia = new TipoActividad("Carbon de Lenia", new Kilogramo());
    TipoActividad lenia = new TipoActividad("Lenia", new Kilogramo());
    TipoActividad combustibleGasoil = new TipoActividad("Combustible Gasoil", new Litro());
    TipoActividad combustibleGNC = new TipoActividad("Combustible GNC", new Litro());
    TipoActividad combustibleNafta = new TipoActividad("Combustible Nafta", new Litro());
    TipoActividad electricidad = new TipoActividad("Electricidad", new KiloWatts());
    TipoActividad distMediaRecorrida = new TipoActividad("Distancia Media Recorrida", new Kilometro());
    TipoActividad pesoTotalTransportado = new TipoActividad("Peso Total Transportado", new Kilogramo());

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
        .filter(tipoActividad -> tipoActividad.getNombre() == dato.getTipoDeConsumo()).collect(Collectors.toList())
        .get(0);
  }

  public Double calcularHuellaCarbonoLogProdRes(Collection<DatosDeLaActividad> datos)  throws IOException {

      ArchivoConfig arch = new ArchivoConfig();
      Double K = Double.parseDouble(arch.obtenerValorK());
      Double factorEmision;

      //TODO: Charlar con el grupo porque hay que pensarlo
      //TODO: puede ser meterlo en un config todos los strings y no tenerlos hardcodeados (idea by Mati)
      //NO HACEMOS PASAJE ENTRE KG Y KM PORQUE YA VIENEN BIEN Y SERÍA HACER MUCHO LIO AGREGANDO LA UNIDAD
      DatosDeLaActividad datoDistancia = datos.stream().filter(dato -> (dato.getTipoDeConsumo() == "Distancia Media Recorrida" )).collect(Collectors.toList()).get(0);
      DatosDeLaActividad datoPeso = datos.stream().filter(dato -> (dato.getTipoDeConsumo() == "Peso Total Transportado" )).collect(Collectors.toList()).get(0);
      DatosDeLaActividad datoTransporte = datos.stream().filter(dato -> (dato.getTipoDeConsumo().matches("Medio de transporte: \\."))).collect(Collectors.toList()).get(0);

      //aca usamos el arch para conseguir los FE: NO tenemos INSTANCIA de camiones o lugar donde guardarlo PARA INSTANCIARLO
      if (datoTransporte.getTipoDeConsumo() == "Medio de Transporte: Camion de carga")
      {
        factorEmision = Double.parseDouble(arch.obtenerFECamion());
      } else {
        factorEmision = Double.parseDouble(arch.obtenerFEUtilitario());
      }
    return datoDistancia.getConsumo().getValor() * datoPeso.getConsumo().getValor() * K * factorEmision;
  }

  public Double calcularHuellaCarbonoCombElec(DatosDeLaActividad dato) {
    Double distanciaDato = dato.getConsumo().getValor();
    TipoActividad tipoActividad = this.obtenerTipoActividad(dato);
    Double fe = tipoActividad.getFe();
    return tipoActividad.getTipoUnidad().pasarA(distanciaDato ) * fe;
  }

  //TODO consultar si es necesario Anual y Mensual
  public Double calcularHCActividadAnual(Collection<DatosDeLaActividad> datos, int anio) {
    Collection<DatosDeLaActividad> datosActividad= datos.stream().filter(dato -> dato.perteneceAnio(anio)).collect(Collectors.toList());

    Collection<DatosDeLaActividad> combElec = datosActividad
        .stream()
        .filter(unDato -> !unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    Collection<DatosDeLaActividad> logProdRes = datosActividad
        .stream()
        .filter(unDato-> unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    Double hcCombElec = combElec.stream().mapToDouble(this::calcularHuellaCarbonoCombElec).sum();
    Double hcLogProdRes=0.0;

    //TODO: por que mayor igual a 4? Rta: porque en el excel hay 4 elementos en la lista, no se si son necesarios los 4 para operar pero lo pusimos asi
    if(logProdRes.size() >= 4){
      try{
        hcLogProdRes = this.calcularHuellaCarbonoLogProdRes(logProdRes);
      }catch (IOException e) {
        e.printStackTrace();
      }
    }
    return hcCombElec + hcLogProdRes;
  }

  public Double calcularHCActividadMensual(Collection<DatosDeLaActividad> datos, int anio, int mes) {

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
    Double hcMes;
    Double hcCombElec = combElec.stream().mapToDouble(this::calcularHuellaCarbonoCombElec).sum();
    Double hcLogProdRes=0.0;

    //TODO: por que mayor igual a 4? Rta: porque en el excel hay 4 elementos en la lista, no se si son necesarios los 4 para operar pero lo pusimos asi
    if(logProdRes.size() >= 4){
      try{
        hcLogProdRes = this.calcularHuellaCarbonoLogProdRes(logProdRes);
      }catch (IOException e) {
        e.printStackTrace();
      }
    }

    hcMes = hcCombElec + hcLogProdRes;

    //Calculo HC de las actividades del Anio, posteriormente dividido por los meses vencidos del anio
    Double hcAnio;
    Double hcCombElecAnio = combElecAnio.stream().mapToDouble(this::calcularHuellaCarbonoCombElec).sum();
    Double hcLogProdResAnio=0.0;

    //TODO: por que mayor igual a 4? Rta: porque en el excel hay 4 elementos en la lista, no se si son necesarios los 4 para operar pero lo pusimos asi
    if(logProdRes.size() >= 4){
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
