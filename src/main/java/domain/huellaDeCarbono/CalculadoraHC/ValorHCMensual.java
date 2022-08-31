package domain.huellaDeCarbono.CalculadoraHC;

public class ValorHCMensual {
  int anio;
  int mes;
  double huellaCarbono;

  public ValorHCMensual(int anio, int mes, double huellaCarbono) {
    this.anio = anio;
    this.mes = mes;
    this.huellaCarbono = huellaCarbono;
  }

  public boolean soyMes(int otroAnio, int otroMes) {
    return otroAnio == anio && otroMes == mes;
  }

  public double getHuellaCarbono() {
    return huellaCarbono;
  }
}
