package domain.huellaDeCarbono.espacio;

public class EspacioDeTrabajo extends Espacio{
  private int piso;
  private String unidad;

  public EspacioDeTrabajo(Double latitud, Double longitud, String provincia, String direccion, int numero,
                          int codigoPostal, String barrio, String municipio, String localidad, int piso,
                          String unidad) {
    super(latitud, longitud, provincia, direccion, numero, codigoPostal, barrio, municipio, localidad);
    this.piso = piso;
    this.unidad = unidad;
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
