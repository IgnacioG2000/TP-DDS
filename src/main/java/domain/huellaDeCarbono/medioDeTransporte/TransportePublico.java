package domain.huellaDeCarbono.medioDeTransporte;

public class TransportePublico extends MedioDeTransporte{
  private TipoTransportePublico tipo;
  private String nombre;

  public TransportePublico(TipoTransportePublico tipo, String nombre) {
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
}
