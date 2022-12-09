package com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte;

import com.disenio.mimagrupo06.apiDistancia.ArchivoConfig;

import javax.persistence.*;
import java.io.IOException;

@Entity
@DiscriminatorValue("2")
public class ServicioContratado extends MedioDeTransporte{
  @Enumerated(EnumType.ORDINAL)
  @Column(insertable=false, updatable=false)
  private TipoServicioContratado tipo;

  public ServicioContratado(TipoServicioContratado tipo) throws IOException {
    this.tipo = tipo;
    this.factorEmision = ArchivoConfig.obtenerFEServicioContratado();
    nombreAMostrar = tipo.toString();
  }

  public ServicioContratado() {

  }

  @Override
  public boolean puedoSerCompartido(){
    return true;
  }

  public TipoServicioContratado getTipo() {
    return tipo;
  }

  public void setTipo(TipoServicioContratado tipo) {
    this.tipo = tipo;
  }
}
