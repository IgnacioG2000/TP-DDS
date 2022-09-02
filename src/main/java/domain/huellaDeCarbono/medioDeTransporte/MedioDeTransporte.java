package domain.huellaDeCarbono.medioDeTransporte;

public abstract class MedioDeTransporte {
  protected double factorEmision;

  public MedioDeTransporte() {
  }

  public abstract boolean puedoSerCompartido();

  public double getFactorEmision() {
    return factorEmision;
  }

  public void setFactorEmision(double factorEmision) {
    this.factorEmision = factorEmision;
  }
}
