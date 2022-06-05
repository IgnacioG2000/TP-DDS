package repositorios;

import domain.huellaDeCarbono.espacio.Parada;

import java.util.Collection;
import java.util.HashMap;

public class RepoTransportePublico {
  private HashMap<String, Collection<Parada>> lineas = new HashMap<>();

  private static final RepoTransportePublico INSTANCE = new RepoTransportePublico();

  public static RepoTransportePublico getInstance() {
    return INSTANCE;
  }
}
