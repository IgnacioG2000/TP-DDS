package notificadores;

import domain.organizacion.Contacto;

import java.util.List;

public class NotificarPorWpp extends Notificador {

  // Esta vacio porque no es necesario implementarlo por ahora.

  public NotificarPorWpp() {
  }

  @Override
  public void comunicar(Notificacion notificacion, List<Contacto> contactos) {
  }
}
