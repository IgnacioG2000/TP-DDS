package com.disenio.mimagrupo06.domain.sector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaisSector {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public PaisSector(){

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
