package domain.huellaDeCarbono.medioDeTransporte;

public class TransporteNoMotorizado extends MedioDeTransporte {
  private TipoNoMotorizado tipoNoMotorizado;

  public TransporteNoMotorizado(TipoNoMotorizado tipoNoMotorizado) {
    this.tipoNoMotorizado = tipoNoMotorizado;
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
