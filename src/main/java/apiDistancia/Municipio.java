package apiDistancia;

public class Municipio extends Sector{
  public String id;
  public String nombre;
  public Provincia provincia;

  public String getNombre() {
    return this.nombre;
  }

  @Override
  public String nombreProvincia() {
    return provincia.getNombre();
  }

  public String getId() {
    return this.id;
  }
}
