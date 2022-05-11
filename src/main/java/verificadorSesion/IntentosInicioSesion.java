package verificadorSesion;


public class IntentosInicioSesion {

    String usuario;
    int cantIntentos;
    long timestamp;

    public IntentosInicioSesion(String usuario) {
        this.usuario = usuario;
        cantIntentos = 1;
        //en segundos
        this.actualizarTimeStamp();
    }

    void aumentarIntentos() {
        cantIntentos++;
        this.actualizarTimeStamp();
    }

    private void actualizarTimeStamp() {
        timestamp = System.currentTimeMillis() / 1000;
    }
    boolean puedeIntentarLoggearse() {
        return (cantIntentos <= 3) && ((System.currentTimeMillis() / 1000 - timestamp) > 10);
    }

}
