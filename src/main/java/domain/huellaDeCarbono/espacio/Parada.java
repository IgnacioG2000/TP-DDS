package domain.huellaDeCarbono.espacio;

public class Parada extends Espacio{

  public Parada(double latitud, double longitud, String provincia, String municipio, String localidad,
                String direccion, String numero, float codigoPostal) {
    super(latitud, longitud, provincia, municipio, localidad, direccion, numero, codigoPostal);
  }
}
