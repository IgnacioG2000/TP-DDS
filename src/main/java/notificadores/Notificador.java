package notificadores;


import repositorios.RepoOrganizacion;

public class Notificador {

    private String mailGeneral = "hola";
    private String numeroGeneral;


    //es lo menos feo que se nos ocurrió, perdón por los gatitos matados.
    EmailSender mailSender = new EmailSender();
    WhatsAppSender wspSender = new WhatsAppSender();

    //asumimos que como no dice que se ELIGE POR DONDE SE MANDA, le mandamos por los dos :)
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

}
