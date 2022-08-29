package domain.huellaDeCarbono.CalculadoraHC;

public class Litro extends TipoUnidad{
  @Override
  public double pasarA(double valor) {
    return valor * 1;
  }
}
