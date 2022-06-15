package domain.huellaDeCarbono.espacio;

public class Hogar extends Espacio{
  private int piso;
  private String departamento;
  private TipoDeHogar tipoDeHogar;

  public Hogar(Double latitud, Double longitud, String provincia, String direccion, int numero,
               int codigoPostal, String barrio, String municipio, String localidad, int piso,
               String departamento, TipoDeHogar tipoDeHogar) {
    super(latitud, longitud, provincia, direccion, numero, codigoPostal, barrio, municipio, localidad);
    this.piso = piso;
    this.departamento = departamento;
    this.tipoDeHogar = tipoDeHogar;
  }

  public Integer getPiso() {
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
