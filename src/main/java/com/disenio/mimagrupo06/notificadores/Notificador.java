package com.disenio.mimagrupo06.notificadores;

import com.disenio.mimagrupo06.domain.organizacion.Contacto;

import java.util.Collection;


public abstract class Notificador {

  public abstract void comunicar(Notificacion notificacion, Collection<Contacto> contactos);
}
