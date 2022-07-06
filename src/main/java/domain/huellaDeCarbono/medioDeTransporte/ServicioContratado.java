package domain.huellaDeCarbono.medioDeTransporte;

public class ServicioContratado extends MedioDeTransporte{
  private TipoServicioContratado tipo;

  public ServicioContratado(double factorEmision, TipoServicioContratado tipo) {
    super(factorEmision);
    this.tipo = tipo;
  }

  @Override
  public boolean puedoSerCompartido(){
    return true;
  }
}
