package domain.huellaDeCarbono;

import excel_ETL.DatosDeLaActividad;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CalculadoraHCActividad {
  private Double factorEmisionGas;
  private Double factorEmisionDiesel;
  private Double factorEmisionKerosene;
  private Double factorEmisionFuel;
  private Double factorEmisionNafta;
  private Double factorEmisionCarbon;
  private Double factorEmisionCarbonLenia;
  private Double factorEmisionLenia;
  private Double factorCombConsumidoGasoil;
  private Double factorCombConsumidoGNC;
  private Double factorCombConsumidoNafta;
  private Double factorEmisionElectricidad;
  private Double factorEmisionLogProdRes;

  public CalculadoraHCActividad(Double factorEmisionGas, Double factorEmisionDiesel, Double factorEmisionKerosene,
                                Double factorEmisionFuel, Double factorEmisionNafta, Double factorEmisionCarbon,
                                Double factorEmisionCarbonLenia, Double factorEmisionLenia,
                                Double factorCombConsumidoGasoil, Double factorCombConsumidoGNC,
                                Double factorCombConsumidoNafta, Double factorEmisionElectricidad,
                                Double factorEmisionLogProdRes) {
    this.factorEmisionGas = factorEmisionGas;
    this.factorEmisionDiesel = factorEmisionDiesel;
    this.factorEmisionKerosene = factorEmisionKerosene;
    this.factorEmisionFuel = factorEmisionFuel;
    this.factorEmisionNafta = factorEmisionNafta;
    this.factorEmisionCarbon = factorEmisionCarbon;
    this.factorEmisionCarbonLenia = factorEmisionCarbonLenia;
    this.factorEmisionLenia = factorEmisionLenia;
    this.factorCombConsumidoGasoil = factorCombConsumidoGasoil;
    this.factorCombConsumidoGNC = factorCombConsumidoGNC;
    this.factorCombConsumidoNafta = factorCombConsumidoNafta;
    this.factorEmisionElectricidad = factorEmisionElectricidad;
    this.factorEmisionLogProdRes = factorEmisionLogProdRes;
  }

  public Double calcularHuellaCarbonoLogProdRes(List<DatosDeLaActividad> datos, Double k) {
    List<Double> valores = datos
        .stream()
        .map(unDato->unDato.getConsumo().getValor())
        .collect(Collectors.toList());

    return valores.get(0) * valores.get(1) * k * this.factorEmisionLogProdRes;
  }

  public Double calcularHuellaCarbonoCombElec(DatosDeLaActividad dato) {
    Double distanciaDato = dato.getConsumo().getValor();
    Double fe = this.obtenerFE(dato.getTipoDeConsumo());
    return distanciaDato * fe;
  }

  private Double obtenerFE(String tipoDeConsumo) {
    Double feAsociado;
    switch (tipoDeConsumo) {
      case "Gas Natural":
        feAsociado = factorEmisionGas;
        break;
      case "Diesel/Gasoil":
        feAsociado = factorEmisionDiesel;
        break;
      case "Kerosene":
        feAsociado = factorEmisionKerosene;
        break;
      case "Fuel Oil":
        feAsociado = factorEmisionFuel;
        break;
      case "Nafta":
        feAsociado = factorEmisionNafta;
        break;
      case "Carbón":
        feAsociado = factorEmisionCarbon;
        break;
      case "Carbón de leña":
        feAsociado = factorEmisionCarbonLenia;
        break;
      case "Leña":
        feAsociado = factorEmisionLenia;
        break;
      case "Combustible consumido - Gasoil":
        feAsociado = factorCombConsumidoGasoil;
        break;
      case "Combustible consumido - GNC":
        feAsociado = factorCombConsumidoGNC;
        break;
      case "Combustible consumido - Nafta":
        feAsociado = factorCombConsumidoNafta;
        break;
      case "Electricidad":
        feAsociado = factorEmisionElectricidad;
        break;
      default:
          feAsociado = 0.0;
    }
    return feAsociado;
  }

  public Double calcularHCActividad(List<DatosDeLaActividad> datos, LocalDate fecha, boolean esMensual, Double constante) {
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
    Double hcLogProdRes = this.calcularHuellaCarbonoLogProdRes(logProdRes, constante);

    return hcCombElec + hcLogProdRes;
  }


  public void setFactorEmisionGas(Double factorEmisionGas) {
    this.factorEmisionGas = factorEmisionGas;
  }

  public void setFactorEmisionDiesel(Double factorEmisionDiesel) {
    this.factorEmisionDiesel = factorEmisionDiesel;
  }

  public void setFactorEmisionKerosene(Double factorEmisionKerosene) {
    this.factorEmisionKerosene = factorEmisionKerosene;
  }

  public void setFactorEmisionFuel(Double factorEmisionFuel) {
    this.factorEmisionFuel = factorEmisionFuel;
  }

  public void setFactorEmisionNafta(Double factorEmisionNafta) {
    this.factorEmisionNafta = factorEmisionNafta;
  }

  public void setFactorEmisionCarbon(Double factorEmisionCarbon) {
    this.factorEmisionCarbon = factorEmisionCarbon;
  }

  public void setFactorEmisionCarbonLenia(Double factorEmisionCarbonLenia) {
    this.factorEmisionCarbonLenia = factorEmisionCarbonLenia;
  }

  public void setFactorEmisionLenia(Double factorEmisionLenia) {
    this.factorEmisionLenia = factorEmisionLenia;
  }

  public void setFactorCombConsumidoGasoil(Double factorCombConsumidoGasoil) {
    this.factorCombConsumidoGasoil = factorCombConsumidoGasoil;
  }

  public void setFactorCombConsumidoGNC(Double factorCombConsumidoGNC) {
    this.factorCombConsumidoGNC = factorCombConsumidoGNC;
  }

  public void setFactorCombConsumidoNafta(Double factorCombConsumidoNafta) {
    this.factorCombConsumidoNafta = factorCombConsumidoNafta;
  }

  public void setFactorEmisionElectricidad(Double factorEmisionElectricidad) {
    this.factorEmisionElectricidad = factorEmisionElectricidad;
  }

  public void setFactorEmisionLogProdRes(Double factorEmisionLogProdRes) {
    this.factorEmisionLogProdRes = factorEmisionLogProdRes;
  }
}
