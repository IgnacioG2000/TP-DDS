package domain.huellaDeCarbono.CalculadoraHC;

public class TipoActividad {
  String nombre;
  Double fe;
  String tipoUnidad;

  public TipoActividad(String nombre, String tipoUnidad) {
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

  public String getTipoUnidad() {
    return tipoUnidad;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setFe(Double fe) {
    this.fe = fe;
  }

  public void setTipoUnidad(String tipoUnidad) {
    this.tipoUnidad = tipoUnidad;
  }

}
