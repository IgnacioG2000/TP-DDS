package com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte;

import com.disenio.mimagrupo06.apiDistancia.ArchivoConfig;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.IOException;

@Entity
@DiscriminatorValue("1")
public class TransporteNoMotorizado extends MedioDeTransporte {
 @Enumerated(EnumType.ORDINAL)
  private TipoNoMotorizado tipoNoMotorizado;

  public TransporteNoMotorizado(TipoNoMotorizado tipoNoMotorizado) throws IOException {
    this.tipoNoMotorizado = tipoNoMotorizado;
    this.factorEmision = ArchivoConfig.obtenerFENoMotorizados();
  }

  public TransporteNoMotorizado() {

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
