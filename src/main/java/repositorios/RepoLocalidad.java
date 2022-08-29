package repositorios;

import apiDistancia.Localidad;

import java.util.Collection;

public class RepoLocalidad {
  private Collection<Localidad> localidades;

  private static final RepoLocalidad INSTANCE = new RepoLocalidad();

  public static RepoLocalidad getInstance() {
    return INSTANCE;
  }
}
