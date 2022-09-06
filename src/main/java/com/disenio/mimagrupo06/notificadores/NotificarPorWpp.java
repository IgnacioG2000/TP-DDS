package com.disenio.mimagrupo06.notificadores;

import com.disenio.mimagrupo06.domain.organizacion.Contacto;

import java.util.Collection;


public class NotificarPorWpp extends Notificador {

  // Esta vacio porque no es necesario implementarlo por ahora.

  public NotificarPorWpp() {
  }

  @Override
  public void comunicar(Notificacion notificacion, Collection<Contacto> contactos) {
  }
}
