package apiDistancia;

import java.util.List;

public class ListadoPais {
  /*public int cantidad;
  public int total;
  public int inicio;
  public Parametro parametros;*
  int id;
   */
  public List<Pais> paises;

  public List<Pais> getPaises() {
    return paises;
  }

  public void setPaises(List<Pais> paises) {
    this.paises = paises;
  }

  private class Parametro {
    public List<String> campos;
  }
}
