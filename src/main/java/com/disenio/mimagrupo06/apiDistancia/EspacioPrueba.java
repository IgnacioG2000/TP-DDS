package com.disenio.mimagrupo06.apiDistancia;
// Clase de prueba para la obtención de distancia entre espacios.
public class EspacioPrueba {
    private String localidad;
    private String calle;
    private String altura;

    public EspacioPrueba(String localidad, String calle, String altura) {
        this.localidad = localidad;
        this.calle = calle;
        this.altura = altura;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }
}
