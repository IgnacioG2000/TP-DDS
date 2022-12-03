package com.disenio.mimagrupo06.presentacion.dto;



public class TrayectoExistenteDTO {
    private String idSesion;
    private String area;
    private Long idTrayecto;

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getIdTrayecto() {
        return idTrayecto;
    }

    public void setIdTrayecto(Long idTrayecto) {
        this.idTrayecto = idTrayecto;
    }
}