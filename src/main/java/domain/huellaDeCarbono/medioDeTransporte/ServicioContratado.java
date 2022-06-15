package domain.huellaDeCarbono.medioDeTransporte;

public class ServicioContratado extends MedioDeTransporte{
  private TipoServicioContratado tipo;

  public ServicioContratado(TipoServicioContratado tipo) {
    this.tipo = tipo;
  }
  
  @Override
  public boolean puedoSerCompartido(){
    return true;
  }
}
