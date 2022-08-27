package notificadores;

import domain.organizacion.Contacto;
import domain.organizacion.Organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManejadorEvento {
  private List<Notificador> notificadores;
  private Organizacion organizacion;

  public ManejadorEvento() {
    this.notificadores = new ArrayList<>();
  }

  public void suscribirse(Notificador notificador){
    notificadores.add(notificador);
  }

  public void desuscribirse(Notificador notificador){
    notificadores.remove(notificador);
  }


  public void notificar(Notificacion notificacion) {
    notificadores.forEach(n -> n.comunicar(notificacion, organizacion.getContactos()));

  }


}
