package com.disenio.mimagrupo06.seguridad.verificadorSesion;


public class IntentosInicioSesion {

    private String usuario;
    private int cantIntentos;
    private long timestamp;

    public IntentosInicioSesion(String usuario) {
        this.usuario = usuario;
        cantIntentos = 1;
        //en segundos
        this.actualizarTimeStamp();
    }

    public void aumentarIntentos() {
        cantIntentos++;
        this.actualizarTimeStamp();
    }

    private void actualizarTimeStamp() {
        timestamp = System.currentTimeMillis() / 1000;
    }

    public boolean puedeIntentarLoggearse() {
        return (cantIntentos <= 3) && ((System.currentTimeMillis() / 1000 - timestamp) > 10);
    }

}
