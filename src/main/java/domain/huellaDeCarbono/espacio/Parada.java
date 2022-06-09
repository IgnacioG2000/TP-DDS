package domain.huellaDeCarbono.espacio;

public class Parada extends Espacio{
  public Parada(Double latitud, Double longitud, String direccion, String provincia, float numero, float codigoPostal, String barrio) {
    this.latitud = latitud;
    this.longitud = longitud;
    this.provincia = provincia;
    this.direccion = direccion;
    this.numero = numero;
    this.codigoPostal = codigoPostal;
    this.barrio = barrio;
  }
}
