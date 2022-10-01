package com.disenio.mimagrupo06.notificadores;

import com.disenio.mimagrupo06.domain.organizacion.Organizacion;

import java.io.IOException;


public class ManejadorEvento {
  private static ManejadorEvento instancia = null;

  public ManejadorEvento() {
  }

  public static ManejadorEvento getInstancia() {
    if (instancia == null) {
      instancia = new ManejadorEvento();
    }
    return instancia;
  }


  public void notificar(Notificacion notificacion, Organizacion organizacion) {
    organizacion.getMediosNotificacion();
    switch (organizacion.getMediosNotificacion()){
      case email:
        NotificarPorMail.getInstancia().comunicar(notificacion, organizacion.getContactos());
        break;
      case whatsapp:
        ;
        break;
      case email_y_whatsapp:
        NotificarPorMail.getInstancia().comunicar(notificacion, organizacion.getContactos());
        break;
      default:
        break;
    }
  }


}
