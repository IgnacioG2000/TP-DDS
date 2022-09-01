package domain.huellaDeCarbono.medioDeTransporte;

public abstract class MedioDeTransporte {
  protected double factorEmision;

  public MedioDeTransporte(double factorEmision) {
    this.factorEmision = factorEmision;
  }

  public abstract boolean puedoSerCompartido();

  public double getFactorEmision() {
    return factorEmision;
  }

  public void setFactorEmision(double factorEmision) {
    this.factorEmision = factorEmision;
  }
}
