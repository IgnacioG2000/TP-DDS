import domain.organizacion.Contacto;
import domain.organizacion.Organizacion;
import notificadores.EmailSender;
import notificadores.ManejadorEvento;
import notificadores.NotificarPorMail;
import notificadores.SchedulerNotification;
import org.quartz.SchedulerException;

public class Main {

  public static void main(String[] args) throws SchedulerException {
    Organizacion org = new Organizacion(new ManejadorEvento());
    Contacto contacto = new Contacto("rputrino@frba.utn.edu.ar", "11111111");
    org.agregarContacto(contacto.getEmail(), contacto.getNumeroTelefono());

    NotificarPorMail noti = new NotificarPorMail();
    noti.suscribirse(contacto);

    SchedulerNotification schedulerNotificador = new SchedulerNotification();
    schedulerNotificador.comenzar();
  }
}
