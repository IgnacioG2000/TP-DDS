package domain.huellaDeCarbono.espacio;

public class EspacioDeTrabajo extends Espacio{
  private int piso;
  private String unidad;

  public EspacioDeTrabajo(Double latitud, Double longitud, String provincia, String direccion, String numero, float codigoPostal, String barrio, int piso, String unidad) {
    this.latitud = latitud;
    this.longitud = longitud;
    this.provincia = provincia;
    this.direccion = direccion;
    this.numero = numero;
    this.codigoPostal = codigoPostal;
    this.barrio = barrio;
    this.piso=piso;
    this.unidad=unidad;
  }

  public int getPiso() {
    return piso;
  }

  public void setPiso(int piso) {
    this.piso = piso;
  }

  public String getUnidad() {
    return unidad;
  }

  public void setUnidad(String unidad) {
    this.unidad = unidad;
  }
}
