package notificadores;

public class Notificacion {
  private String asunto;
  private String cuerpo;

  public Notificacion(String asunto, String cuerpo) {
    this.asunto = asunto;
    this.cuerpo = cuerpo;
  }

  public String getAsunto() {
    return asunto;
  }

  public String getCuerpo() {
    return cuerpo;
  }
}
