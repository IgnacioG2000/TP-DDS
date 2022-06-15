package domain.huellaDeCarbono.espacio;

public class Parada extends Espacio{
  public Parada(Double latitud, Double longitud, String provincia, String direccion, int numero,
                int codigoPostal, String barrio, String municipio, String localidad) {
    super(latitud, longitud, provincia, direccion, numero, codigoPostal, barrio, municipio, localidad);
  }
}
