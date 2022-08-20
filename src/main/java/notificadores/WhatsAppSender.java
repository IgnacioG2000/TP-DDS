package notificadores;

import domain.organizacion.Contacto;

import java.util.List;

public class WhatsAppSender extends Notificador{

  // Esta vacio porque no es necesario implementarlo por ahora.

  public WhatsAppSender() {
  }

  public void send(String from, String to, String subject, String body) {
  }

  @Override
  public void notificar(List<Contacto> contactos) {
    contactos.forEach(c -> this.send("hardocear",c.getNumeroTelefono(), null, "harcodeo"));
  }
}
