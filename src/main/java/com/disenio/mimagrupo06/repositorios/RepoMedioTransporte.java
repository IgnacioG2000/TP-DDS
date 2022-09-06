package com.disenio.mimagrupo06.repositorios;



import com.disenio.mimagrupo06.domain.huellaDeCarbono.medioDeTransporte.MedioDeTransporte;

import java.util.Collection;

public class RepoMedioTransporte {
  private Collection<MedioDeTransporte> medioDeTransportes;

  private static final RepoMedioTransporte INSTANCE = new RepoMedioTransporte();

  public static RepoMedioTransporte getInstance() {
    return INSTANCE;
  }
}
