package domain.huellaDeCarbono.espacio;

public abstract class Espacio {
  private Double latitud;
  private Double longitud;
  private String direccion;
  private Number numero;
  private Number codigoPostal;
  private String barrio;

  public Espacio(Double latitud, Double longitud, String direccion, Number numero, Number codigoPostal, String barrio) {
    this.latitud = latitud;
    this.longitud = longitud;
    this.direccion = direccion;
    this.numero = numero;
    this.codigoPostal = codigoPostal;
    this.barrio = barrio;
  }

  public Double getLatitud() {
    return latitud;
  }

  public void setLatitud(Double latitud) {
    this.latitud = latitud;
  }

  public Double getLongitud() {
    return longitud;
  }

  public void setLongitud(Double longitud) {
    this.longitud = longitud;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public Number getNumero() {
    return numero;
  }

  public void setNumero(Number numero) {
    this.numero = numero;
  }

  public Number getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(Number codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

  public String getBarrio() {
    return barrio;
  }

  public void setBarrio(String barrio) {
    this.barrio = barrio;
  }
}
