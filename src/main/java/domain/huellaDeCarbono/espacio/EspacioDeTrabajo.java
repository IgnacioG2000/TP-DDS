package domain.huellaDeCarbono.espacio;

public class EspacioDeTrabajo extends Espacio{
  private int piso;
  private String unidad;

  public EspacioDeTrabajo(Double latitud, Double longitud, String direccion, Number numero, Number codigoPostal, String barrio, int piso, String unidad) {
    super(latitud, longitud, direccion, numero, codigoPostal, barrio);
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
