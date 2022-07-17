package domain.huellaDeCarbono.medioDeTransporte;

public class TransporteNoMotorizado extends MedioDeTransporte {
  private TipoNoMotorizado tipoNoMotorizado;

  public TransporteNoMotorizado(double factor, TipoNoMotorizado tipoNoMotorizado) {
    super(factor);
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
