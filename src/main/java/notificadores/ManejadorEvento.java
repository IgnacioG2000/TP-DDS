package notificadores;

import domain.organizacion.Contacto;
import domain.organizacion.Organizacion;

import java.util.List;
import java.util.stream.Collectors;

public class ManejadorEvento {
  private List<Notificador> notificadores;
  private List<Organizacion> notificados;

  public ManejadorEvento(List<Notificador> notificadores, List<Organizacion> notificados) {
    this.notificadores = notificadores;
    this.notificados = notificados;
  }

  public void suscribirse(Notificador notificado){
    notificadores.add(notificado);
  }

  public void desuscribirse(Notificador notificado){
    notificadores.remove(notificado);
  }

  public void notificar() {
  //  notificados.forEach(n -> n.getNotificadoresPreferidos().forEach(np -> np.notificar(n.getContactos())));

  }
}
