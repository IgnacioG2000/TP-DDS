package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.apiDistancia.Localidad;

import java.util.Collection;

public class RepoLocalidad {
  private Collection<Localidad> localidades;

  private static final RepoLocalidad INSTANCE = new RepoLocalidad();

  public static RepoLocalidad getInstance() {
    return INSTANCE;
  }
}
