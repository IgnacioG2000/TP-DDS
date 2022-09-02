package domain.huellaDeCarbono.medioDeTransporte;

import apiDistancia.ArchivoConfig;

import java.io.IOException;

public class ServicioContratado extends MedioDeTransporte{
  private TipoServicioContratado tipo;

  public ServicioContratado(TipoServicioContratado tipo) throws IOException {
    this.tipo = tipo;
    this.factorEmision = ArchivoConfig.obtenerFEServicioContratado();
  }

  @Override
  public boolean puedoSerCompartido(){
    return true;
  }
}
