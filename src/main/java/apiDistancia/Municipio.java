package apiDistancia;

public class Municipio {
  public String id;
  public String nombre;
  public Provincia provincia;

  public String getNombre() {
    return this.nombre;
  }

  public String getId() {
    return this.id;
  }
}
