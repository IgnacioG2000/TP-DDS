package domain.huellaDeCarbono.medioDeTransporte;

public abstract class MedioDeTransporte {
  protected Double factorEmision;

  public MedioDeTransporte(Double factorEmision) {
    this.factorEmision = factorEmision;
  }

  public abstract boolean puedoSerCompartido();

  public Double getFactorEmision() {
    return factorEmision;
  }

  public void setFactorEmision(Double factorEmision) {
    this.factorEmision = factorEmision;
  }
}
