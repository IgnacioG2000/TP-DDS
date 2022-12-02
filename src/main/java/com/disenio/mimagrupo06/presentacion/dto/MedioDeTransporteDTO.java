package com.disenio.mimagrupo06.presentacion.dto;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class MedioDeTransporteDTO {
  private Long id;
  private String claseAInicializar;
  private TipoServicioContratado tipoServicioContratado;
  private TipoNoMotorizado tipoNoMotorizado;
  private TipoTransportePublico tipoTransporte;
  private String nombre;
  private TipoVehiculo tipoVehiculoParticular;
  private TipoCombustible tipoCombustible;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getClaseAInicializar() {
    return claseAInicializar;
  }

  public void setClaseAInicializar(String claseAInicializar) {
    this.claseAInicializar = claseAInicializar;
  }

  public TipoServicioContratado getTipoServicioContratado() {
    return tipoServicioContratado;
  }

  public void setTipoServicioContratado(TipoServicioContratado tipoServicioContratado) {
    this.tipoServicioContratado = tipoServicioContratado;
  }

  public TipoNoMotorizado getTipoNoMotorizado() {
    return tipoNoMotorizado;
  }

  public void setTipoNoMotorizado(TipoNoMotorizado tipoNoMotorizado) {
    this.tipoNoMotorizado = tipoNoMotorizado;
  }

  public TipoTransportePublico getTipoTransporte() {
    return tipoTransporte;
  }

  public void setTipoTransporte(TipoTransportePublico tipoTransporte) {
    this.tipoTransporte = tipoTransporte;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public TipoVehiculo getTipoVehiculoParticular() {
    return tipoVehiculoParticular;
  }

  public void setTipoVehiculoParticular(TipoVehiculo tipoVehiculoParticular) {
    this.tipoVehiculoParticular = tipoVehiculoParticular;
  }

  public TipoCombustible getTipoCombustible() {
    return tipoCombustible;
  }

  public void setTipoCombustible(TipoCombustible tipoCombustible) {
    this.tipoCombustible = tipoCombustible;
  }
}
