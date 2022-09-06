package com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte;

import com.disenio.mimagrupo06.apiDistancia.ArchivoConfig;

import java.io.IOException;

public class TransportePublico extends MedioDeTransporte{
  private TipoTransportePublico tipo;
  private String nombre;

  public TransportePublico(TipoTransportePublico tipo, String nombre) throws IOException {
    this.tipo = tipo;
    this.nombre = nombre;
    this.factorEmision = ArchivoConfig.obtenerFETrasnportePublico();
  }

  public TipoTransportePublico getTipo() {
    return tipo;
  }

  public void setTipo(TipoTransportePublico tipo) {
    this.tipo = tipo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public boolean puedoSerCompartido(){
    return false;
  }
}
