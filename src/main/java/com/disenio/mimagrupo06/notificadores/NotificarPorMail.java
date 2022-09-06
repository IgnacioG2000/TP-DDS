package com.disenio.mimagrupo06.notificadores;


import com.disenio.mimagrupo06.domain.organizacion.Contacto;

import java.util.Collection;
import java.util.List;

public class NotificarPorMail extends Notificador {

  public NotificarPorMail() {
    super();
  }

  @Override
  public void comunicar(Notificacion notificacion, Collection<Contacto> contactos) {
    contactos.forEach(contacto -> EmailSender.getInstance().enviarConGMail(contacto.getEmail(),
        notificacion.getAsunto(), notificacion.getCuerpo()));
  }
}
