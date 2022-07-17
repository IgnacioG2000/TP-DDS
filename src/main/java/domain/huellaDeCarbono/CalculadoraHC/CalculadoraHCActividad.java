package domain.huellaDeCarbono.CalculadoraHC;

import apiDistancia.ArchivoConfig;
import excel_ETL.DatosDeLaActividad;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalculadoraHCActividad {
  List<TipoActividad> tiposActividad = new ArrayList<>();

  public CalculadoraHCActividad() {
    List<TipoActividad> listaActividad = new ArrayList<>();
    TipoActividad gasNatural = new TipoActividad("Gas Natural", "m3");
    TipoActividad dieselGasoil = new TipoActividad("dieselGasoil", "lt");
    TipoActividad kerosene = new TipoActividad("kerosene", "lt");
    TipoActividad fuelOil= new TipoActividad("Fuel Oil", "lt");
    TipoActividad nafta = new TipoActividad("Nafta", "lt");
    TipoActividad carbon = new TipoActividad("Carbon", "kg");
    TipoActividad carbonDeLenia = new TipoActividad("Carbon de Lenia", "kg");
    TipoActividad lenia = new TipoActividad("Lenia", "kg");
    TipoActividad combustibleGasoil = new TipoActividad("Combustible Gasoil", "lt");
    TipoActividad combustibleGNC = new TipoActividad("Combustible GNC", "lt");
    TipoActividad combustibleNafta = new TipoActividad("Combustible Nafta", "lt");
    TipoActividad electricidad = new TipoActividad("Electricidad", "Kwh");
    TipoActividad LogísticaDeproductosYresiduos= new TipoActividad("Distancia Media Recorrida", "km");
    TipoActividad pesoTotalTransportado = new TipoActividad("Peso Total Transportado", "kg");

    tiposActividad.addAll(Arrays.asList(gasNatural, dieselGasoil, kerosene, fuelOil, nafta, carbon, carbonDeLenia, lenia, combustibleGasoil, combustibleGNC, combustibleNafta, electricidad, distMediaRecorrida, pesoTotalTransportado));
  }

  private TipoActividad obtenerTipoActividad(DatosDeLaActividad dato){
    //recorrer tiposActividad y devolver el que cumple
    return tiposActividad.stream().filter(tipoActividad -> tipoActividad.getNombre() == dato.getTipoDeConsumo()).collect(Collectors.toList()).get(0);
  }

  public Double calcularHuellaCarbonoLogProdRes(List<DatosDeLaActividad> datos)  throws IOException {

      ArchivoConfig arch = new ArchivoConfig();
      Double K = Double.parseDouble(arch.obtenerValorK());

      //TODO: Charlar con el grupo porque hay que pensarlo
      List<Double> valores = datos
        .stream()
        .map(unDato->unDato.getConsumo().getValor())
        .collect(Collectors.toList());


    return valores.get(0) * valores.get(1) * K * this.factorEmisionLogProdRes;

  }

  public Double calcularHuellaCarbonoCombElec(DatosDeLaActividad dato) {
    Double distanciaDato = dato.getConsumo().getValor();
    TipoActividad tipoActividad = this.obtenerTipoActividad(dato);
    Double fe = tipoActividad.getFe();
    return distanciaDato * fe;
  }


  public Double calcularHCActividad(List<DatosDeLaActividad> datos, LocalDate fecha, boolean esMensual) {
    List<DatosDeLaActividad> datosActividad;
    if(esMensual){
      datosActividad= datos.stream().filter(dato -> dato.perteneceMes(fecha)).collect(Collectors.toList());
    }else{
      datosActividad= datos.stream().filter(dato -> dato.perteneceAnio(fecha)).collect(Collectors.toList());
    }

    List<DatosDeLaActividad> combElec = datosActividad
        .stream()
        .filter(unDato -> unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    List<DatosDeLaActividad> logProdRes = datosActividad
        .stream()
        .filter(unDato-> !unDato.getActividad().equals("Logística de productos y residuos"))
        .collect(Collectors.toList());

    Double hcCombElec = combElec.stream().mapToDouble(this::calcularHuellaCarbonoCombElec).sum();
    Double hcLogProdRes = this.calcularHuellaCarbonoLogProdRes(logProdRes);

    return hcCombElec + hcLogProdRes;
  }

}
