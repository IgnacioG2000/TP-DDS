package seguridad.roles;

import domain.huellaDeCarbono.CalculadoraHCActividad;

import java.security.NoSuchAlgorithmException;

public class Administrador extends Usuario {
  private CalculadoraHCActividad calculadoraHCActividad;

  public Administrador(String usuario, String contrasenia
      , CalculadoraHCActividad calculadoraHCActividad) throws NoSuchAlgorithmException {
    super(usuario, contrasenia);
    this.calculadoraHCActividad = calculadoraHCActividad;
  }

  public CalculadoraHCActividad getCalculadoraHCActividad() {
    return calculadoraHCActividad;
  }

  public void setCalculadoraHCActividad(CalculadoraHCActividad calculadoraHCActividad) {
    this.calculadoraHCActividad = calculadoraHCActividad;
  }

  public void setFactorEmisionFuel(Double factorEmision) {
    calculadoraHCActividad.setFactorEmisionFuel(factorEmision);
  }

  public void setFactorEmisionDiesel(Double factorEmision) {
    calculadoraHCActividad.setFactorEmisionDiesel(factorEmision);
  }

  public void setFactorEmisionGas(Double factorEmision) {
    calculadoraHCActividad.setFactorEmisionGas(factorEmision);
  }

  public void setFactorEmisionNafta(Double factorEmision) {
    calculadoraHCActividad.setFactorEmisionNafta(factorEmision);
  }

  public void setFactorEmisionLogProdRes(Double factorEmision) {
    calculadoraHCActividad.setFactorEmisionLogProdRes(factorEmision);
  }

  public void setFactorEmisionCarbon(Double factorEmision) {
    calculadoraHCActividad.setFactorEmisionCarbon(factorEmision);
  }

  public void setFactorEmisionCarbonLenia(Double factorEmision) {
    calculadoraHCActividad.setFactorEmisionCarbonLenia(factorEmision);
  }

  public void setFactorEmisionElectricidad(Double factorEmision) {
    calculadoraHCActividad.setFactorEmisionElectricidad(factorEmision);
  }

  public void setFactorEmisionKerosene(Double factorEmision) {
    calculadoraHCActividad.setFactorEmisionKerosene(factorEmision);
  }

  public void setFactorEmisionLenia(Double factorEmision) {
    calculadoraHCActividad.setFactorEmisionLenia(factorEmision);
  }

  public void setFactorEmisionCombConsumidoGasoil(Double factorEmision) {
    calculadoraHCActividad.setFactorCombConsumidoGasoil(factorEmision);
  }

  public void setFactorEmisionCombConsumidoGNC(Double factorEmision) {
    calculadoraHCActividad.setFactorCombConsumidoGNC(factorEmision);
  }

  public void setFactorEmisionCombConsumidoNafta(Double factorEmision) {
    calculadoraHCActividad.setFactorCombConsumidoNafta(factorEmision);
  }

}
