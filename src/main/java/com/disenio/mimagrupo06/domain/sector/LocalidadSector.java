package com.disenio.mimagrupo06.domain.sector;

import com.disenio.mimagrupo06.apiDistancia.Municipio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class LocalidadSector
{
   @Id
    private Long id;
    private String nombre;
    private String codPostal;
    @ManyToOne
    private MunicipioSector municipio;


    public LocalidadSector(Long id, String nombre, String codPostal, MunicipioSector municipio) {
        this.id = id;
        this.nombre = nombre;
        this.codPostal = codPostal;
        this.municipio = municipio;
    }

    public LocalidadSector() {

    }



    public String getNombre() {
        return nombre;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public MunicipioSector getMunicipio() {
        return municipio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
