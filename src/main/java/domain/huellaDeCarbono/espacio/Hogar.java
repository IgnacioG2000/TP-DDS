package domain.huellaDeCarbono.espacio;

public class Hogar extends Espacio{
  private int piso;
  private String departamento;
  private TipoDeHogar tipoDeHogar;

  public Hogar(Double latitud, Double longitud, String provincia, String municipio, String localidad,
               String direccion, String numero, float codigoPostal, String barrio, int piso, String departamento,
               TipoDeHogar tipoDeHogar) {
    super(latitud, longitud, provincia, municipio, localidad, direccion, numero, codigoPostal);
    this.piso = piso;
    this.departamento = departamento;
    this.tipoDeHogar = tipoDeHogar;
  }

  public int getPiso() {
    return piso;
  }

  public void setPiso(int piso) {
    this.piso = piso;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  public TipoDeHogar getTipoDeHogar() {
    return tipoDeHogar;
  }

  public void setTipoDeHogar(TipoDeHogar tipoDeHogar) {
    this.tipoDeHogar = tipoDeHogar;
  }
}
