package domain.huellaDeCarbono.medioDeTransporte;

public class TransportePublico extends MedioDeTransporte{
  private TipoTransportePublico tipo;
  private String nombre;

  public TransportePublico(double factor,TipoTransportePublico tipo, String nombre) {
    super(factor);
    this.tipo = tipo;
    this.nombre = nombre;
  }

  public TipoTransportePublico getTipo() {
    return tipo;
  }

  public void setTipo(TipoTransportePublico tipo) {
    this.tipo = tipo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public boolean puedoSerCompartido(){
    return false;
  }
}
