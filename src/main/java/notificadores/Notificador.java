package notificadores;

import domain.organizacion.Contacto;

import java.util.List;

public abstract class Notificador {

  public abstract void comunicar(Notificacion notificacion, List<Contacto> contactos);
}
