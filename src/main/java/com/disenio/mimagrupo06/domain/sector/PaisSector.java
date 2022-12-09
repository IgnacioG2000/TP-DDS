package com.disenio.mimagrupo06.domain.sector;

import javax.persistence.*;

@Entity
public class PaisSector {
  @Id
  private Long id;
  private String nombre;

  public PaisSector(Long id, String nombre){
  this.id = id;
  this.nombre = nombre;
  }

  public PaisSector(){

  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
