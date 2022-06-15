package domain.huellaDeCarbono.espacio;

public abstract class Espacio {
  private Double latitud;
  private Double longitud;
  private String provincia;
  private String direccion;
  private int numero;
  private int codigoPostal;
  private String barrio;
  private String municipio;
  private String localidad;

  public Espacio(Double latitud, Double longitud, String provincia, String direccion, int numero,
                 int codigoPostal, String barrio, String municipio, String localidad) {
    this.latitud = latitud;
    this.longitud = longitud;
    this.provincia = provincia;
    this.direccion = direccion;
    this.numero = numero;
    this.codigoPostal = codigoPostal;
    this.barrio = barrio;
    this.municipio = municipio;
    this.localidad = localidad;
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

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public Number getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(int codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

  public String getBarrio() {
    return barrio;
  }

  public void setBarrio(String barrio) {
    this.barrio = barrio;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public String getLocalidad() {
    return localidad;
  }

  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }
}
