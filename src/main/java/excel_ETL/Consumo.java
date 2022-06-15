package excel_ETL;

public class Consumo {
  private Double valor;
  private String periocidad;

  public Consumo() {
  }

  public Double getValor() {
    return valor;
  }

  public String getPeriocidad() {
    return periocidad;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public void setPeriocidad(String periocidad) {
    this.periocidad = periocidad;
  }
}
