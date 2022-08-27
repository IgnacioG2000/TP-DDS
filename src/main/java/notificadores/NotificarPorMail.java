package notificadores;

import java.util.List;

public class NotificarPorMail extends Notificador {

  public NotificarPorMail() {
    super();
  }

  @Override
  public void comunicar(Notificacion notificacion) {
    contactos.forEach(contacto -> EmailSender.getInstance().enviarConGMail(contacto.getEmail(),
        notificacion.getAsunto(), notificacion.getCuerpo()));
  }
}
