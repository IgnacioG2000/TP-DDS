package notificadores;

import domain.organizacion.Contacto;

import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailSender extends Notificador {
    private static Properties prop = new Properties();
    private static Session session;
    private static String username;
    // GMAIL app specific password: https://support.google.com/accounts/answer/185833?p=InvalidSecondFactor
    private static String password;


    static {
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public EmailSender(String username, String password) {
        EmailSender.username = username;
        EmailSender.password = password;
    }

    public void send(String from, String to, String subject, String body) {

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
//            mimeBodyPart.setContent(body, "text/plain; charset=utf-8");
            mimeBodyPart.setContent(body, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notificar(List<Contacto> contactos) {
        contactos.forEach(c -> this.send("hardcodear",c.getEmail(), "harcodear mas tarde", "cuerpito"));
    }
}
