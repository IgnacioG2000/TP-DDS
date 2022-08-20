package notificadores;


import domain.organizacion.Contacto;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import repositorios.RepoOrganizacion;

import java.util.List;

public abstract class Notificador implements Job {

    private String mailGeneral = "hola";
    private String numeroGeneral;
    private String password;


    //es lo menos feo que se nos ocurrió, perdón por los gatitos matados.
    EmailSender mailSender = new EmailSender(mailGeneral, password);
    WhatsAppSender wspSender = new WhatsAppSender();

    //asumimos que como no dice que se ELIGE POR DONDE SE MANDA, le mandamos por los dos :)

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        this.enviarNotificacionesPorTodosLosMedios("Recomendaciones",
            "Se enviará las recomendaciones de las Organizaciones...");
    }

    //Elegir este metodo para el Cronos.
    public void enviarNotificacionesPorTodosLosMedios(String titulo, String texto) {
        //tmb se podria mandar a una lista de mails, pero por ahora lo madnamos a cada uno
        RepoOrganizacion.getInstance().listadoContactosOrganizaciones().forEach( c -> mailSender.send(mailGeneral,c.getEmail(),titulo,texto));
        RepoOrganizacion.getInstance().listadoContactosOrganizaciones().forEach( c -> wspSender.send(numeroGeneral,c.getNumeroTelefono(),titulo,texto));

    }

    public void setMailGeneral(String mailGeneral) {
        this.mailGeneral = mailGeneral;
    }

    public String getNumeroGeneral() {
        return numeroGeneral;
    }

    public void setNumeroGeneral(String numeroGeneral) {
        this.numeroGeneral = numeroGeneral;
    }


    public abstract void notificar(List<Contacto> contactos);
}
