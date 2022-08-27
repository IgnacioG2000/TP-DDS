package notificadores;


import domain.organizacion.Contacto;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import repositorios.RepoOrganizacion;

import java.util.ArrayList;
import java.util.List;

public abstract class Notificador implements Job {
  List<Contacto> contactos;

  public Notificador() {
    this.contactos = new ArrayList<>();
  }

  public void suscribirse(Contacto contacto){
    contactos.add(contacto);
  }

  public void desuscribirse(Contacto contacto){
    contactos.remove(contacto);
  }

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    this.comunicar(new Notificacion("Recomendaciones",
        "Se enviará las recomendaciones de las Organizaciones..."));
  }
  public abstract void comunicar(Notificacion notificacion);
}
