package com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte;

import com.disenio.mimagrupo06.apiDistancia.ArchivoConfig;

import java.io.IOException;

public class VehiculoParticular extends MedioDeTransporte {
  private TipoVehiculo tipoVehiculo;
  private TipoCombustible tipoCombustible;

  public VehiculoParticular(TipoVehiculo tipoVehiculo, TipoCombustible tipoCombustible) throws IOException {
    this.tipoVehiculo = tipoVehiculo;
    this.tipoCombustible = tipoCombustible;
    this.factorEmision = ArchivoConfig.obtenerFEVehiculoParticular();
  }

  public TipoVehiculo getTipoVehiculo() {
    return tipoVehiculo;
  }

  public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
    this.tipoVehiculo = tipoVehiculo;
  }

  public TipoCombustible getTipoCombustible() {
    return tipoCombustible;
  }

  public void setTipoCombustible(TipoCombustible tipoCombustible) {
    this.tipoCombustible = tipoCombustible;
  }

  @Override
  public boolean puedoSerCompartido(){
    return true;
  }
}
