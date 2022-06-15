package domain.huellaDeCarbono.espacio;

public class Parada extends Espacio{

  // Le agregue municipio y localidad
  public Parada(Double latitud, Double longitud, String localidad, String municipio, String direccion, String provincia, String numero, float codigoPostal, String barrio) {
    this.latitud = latitud;
    this.longitud = longitud;
    this.provincia = provincia;
    this.municipio = municipio;
    this.localidad = localidad;
    this.direccion = direccion;
    this.numero = numero;
    this.codigoPostal = codigoPostal;
    this.barrio = barrio;
  }
}
