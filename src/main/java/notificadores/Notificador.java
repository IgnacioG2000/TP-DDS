package notificadores;

import domain.organizacion.Contacto;

import java.util.Collection;
import java.util.List;

public abstract class Notificador {

  public abstract void comunicar(Notificacion notificacion, Collection<Contacto> contactos);
}
