package excel_ETL;

public class Consumo {
  private Number valor;
  private String periocidad;

  public Consumo() {
  }

  public Number getValor() {
    return valor;
  }

  public String getPeriocidad() {
    return periocidad;
  }

  public void setValor(Number valor) {
    this.valor = valor;
  }

  public void setPeriocidad(String periocidad) {
    this.periocidad = periocidad;
  }
}
