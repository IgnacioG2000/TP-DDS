package repositorios;

import apiDistancia.Municipio;

import java.util.Collection;

public class RepoMunicipio {
  private Collection<Municipio> municipios;

  private static final RepoMunicipio INSTANCE = new RepoMunicipio();

  public static RepoMunicipio getInstance() {
    return INSTANCE;
  }
}
