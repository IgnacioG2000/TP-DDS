package domain.huellaDeCarbono.medioDeTransporte;

public class CamionDeCarga extends MedioDeTransporte{
  public CamionDeCarga(double factorEmision) {
    super(factorEmision);
  }

  @Override
  public boolean puedoSerCompartido() {
    return false;
  }
}
