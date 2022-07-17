package domain.huellaDeCarbono.medioDeTransporte;

public class UtilitarioLiviano extends MedioDeTransporte{
  public UtilitarioLiviano(double factorEmision) {
    super(factorEmision);
  }

  @Override
  public boolean puedoSerCompartido() {
    return false;
  }

}
