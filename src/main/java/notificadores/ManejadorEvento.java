package notificadores;

import domain.organizacion.Contacto;
import domain.organizacion.Organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManejadorEvento {
  private List<Notificador> notificadores;

  public ManejadorEvento() {
    this.notificadores = new ArrayList<>();
  }

  public void suscribirse(Notificador notificador){
    notificadores.add(notificador);
  }

  public void desuscribirse(Notificador notificador){
    notificadores.remove(notificador);
  }


  public void notificar(Notificacion notificacion, Organizacion organizacion) {
    notificadores.forEach(n -> n.comunicar(notificacion, organizacion.getContactos()));

  }


}
