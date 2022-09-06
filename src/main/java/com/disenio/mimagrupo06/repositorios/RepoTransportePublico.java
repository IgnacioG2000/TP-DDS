package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Parada;


import java.util.Collection;
import java.util.HashMap;

public class RepoTransportePublico {
  private HashMap<String, Collection<Parada>> lineas = new HashMap<>();

  private static final RepoTransportePublico INSTANCE = new RepoTransportePublico();

  public static RepoTransportePublico getInstance() {
    return INSTANCE;
  }
}
