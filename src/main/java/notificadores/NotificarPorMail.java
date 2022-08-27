package notificadores;

import domain.organizacion.Contacto;

import java.util.List;

public class NotificarPorMail extends Notificador {

  public NotificarPorMail() {
    super();
  }

  @Override
  public void comunicar(Notificacion notificacion, List<Contacto> contactos) {
    contactos.forEach(contacto -> EmailSender.getInstance().enviarConGMail(contacto.getEmail(),
        notificacion.getAsunto(), notificacion.getCuerpo()));
  }
}
