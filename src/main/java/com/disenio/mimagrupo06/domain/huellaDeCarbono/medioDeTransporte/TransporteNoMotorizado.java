package com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte;

import com.disenio.mimagrupo06.apiDistancia.ArchivoConfig;

import java.io.IOException;

public class TransporteNoMotorizado extends MedioDeTransporte {
  private TipoNoMotorizado tipoNoMotorizado;

  public TransporteNoMotorizado(TipoNoMotorizado tipoNoMotorizado) throws IOException {
    this.tipoNoMotorizado = tipoNoMotorizado;
    this.factorEmision = ArchivoConfig.obtenerFENoMotorizados();
  }

  public TipoNoMotorizado getTipoNoMotorizado() {
    return tipoNoMotorizado;
  }

  public void setTipoNoMotorizado(TipoNoMotorizado tipoNoMotorizado) {
    this.tipoNoMotorizado = tipoNoMotorizado;
  }

  @Override
  public boolean puedoSerCompartido(){
    return false;
  }
}
