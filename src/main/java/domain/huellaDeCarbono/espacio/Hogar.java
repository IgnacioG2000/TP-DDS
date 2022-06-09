package domain.huellaDeCarbono.espacio;

public class Hogar extends Espacio{
  private Integer piso;
  private String departamento;
  private TipoDeHogar tipoDeHogar;

  public Hogar(Double latitud, Double longitud, String direccion, float numero, float codigoPostal, String barrio, Integer piso, String departamento, TipoDeHogar tipoDeHogar) {
    this.latitud = latitud;
    this.longitud = longitud;
    this.provincia = provincia;
    this.direccion = direccion;
    this.numero = numero;
    this.codigoPostal = codigoPostal;
    this.barrio = barrio;
    this.piso = piso;
    this.departamento = departamento;
    this.tipoDeHogar = tipoDeHogar;
  }

  public Integer getPiso() {
    return piso;
  }

  public void setPiso(Integer piso) {
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
