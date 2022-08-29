package domain.huellaDeCarbono.CalculadoraHC;

public class MetroCubico extends TipoUnidad{
  @Override
  public double pasarA(double valor) {
    return valor * 1000;
  }
}
