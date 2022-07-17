package domain.huellaDeCarbono.medioDeTransporte;

public class VehiculoParticular extends MedioDeTransporte {
  private TipoVehiculo tipoVehiculo;
  private TipoCombustible tipoCombustible;

  public VehiculoParticular(double factorEmision, TipoVehiculo tipoVehiculo, TipoCombustible tipoCombustible) {
    super(factorEmision);
    this.tipoVehiculo = tipoVehiculo;
    this.tipoCombustible = tipoCombustible;
  }

  public TipoVehiculo getTipoVehiculo() {
    return tipoVehiculo;
  }

  public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
    this.tipoVehiculo = tipoVehiculo;
  }

  public TipoCombustible getTipoCombustible() {
    return tipoCombustible;
  }

  public void setTipoCombustible(TipoCombustible tipoCombustible) {
    this.tipoCombustible = tipoCombustible;
  }

  @Override
  public boolean puedoSerCompartido(){
    return true;
  }
}
