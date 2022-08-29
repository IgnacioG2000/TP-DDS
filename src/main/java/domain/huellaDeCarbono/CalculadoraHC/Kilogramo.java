package domain.huellaDeCarbono.CalculadoraHC;

public class Kilogramo extends TipoUnidad{
  @Override
  public double pasarA(double valor) {
    return valor * 1;
  }
}
