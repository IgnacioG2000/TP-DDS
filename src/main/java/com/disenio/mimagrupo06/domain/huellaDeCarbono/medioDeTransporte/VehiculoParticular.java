package com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte;

import com.disenio.mimagrupo06.apiDistancia.ArchivoConfig;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.IOException;

@Entity
@DiscriminatorValue("3")
public class VehiculoParticular extends MedioDeTransporte {
  @Enumerated(EnumType.ORDINAL)
  private TipoVehiculo tipoVehiculo;
  @Enumerated(EnumType.ORDINAL)
  private TipoCombustible tipoCombustible;

  public VehiculoParticular(TipoVehiculo tipoVehiculo, TipoCombustible tipoCombustible) throws IOException {
    this.tipoVehiculo = tipoVehiculo;
    this.tipoCombustible = tipoCombustible;
    this.factorEmision = ArchivoConfig.obtenerFEVehiculoParticular();
  }

  public VehiculoParticular() {

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
