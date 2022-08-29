package domain.huellaDeCarbono.CalculadoraHC;

public class TipoActividad {
  String nombre;
  Double fe;
  TipoUnidad tipoUnidad;

  public TipoActividad(String nombre, TipoUnidad tipoUnidad) {
    this.nombre = nombre;
    this.tipoUnidad = tipoUnidad;
  }

  private Double obtenerFE() {
    return fe;
  }

  public String getNombre() {
    return nombre;
  }

  public Double getFe() {
    return fe;
  }

  public TipoUnidad getTipoUnidad() {
    return tipoUnidad;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setFe(Double fe) {
    this.fe = fe;
  }

  public void setTipoUnidad(TipoUnidad tipoUnidad) {
    this.tipoUnidad = tipoUnidad;
  }

}
