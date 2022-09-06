package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.apiDistancia.Provincia;

import java.util.Collection;

public class RepoProvincia {
  private Collection<Provincia> provincias;

  private static final RepoProvincia INSTANCE = new RepoProvincia();

  public static RepoProvincia getInstance() {
    return INSTANCE;
  }
}
