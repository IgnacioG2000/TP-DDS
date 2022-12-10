package com.disenio.mimagrupo06.seguridad.roles;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.ValorHCMensualOrganizacion;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.ValorHCMensualSector;
import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.sector.Sector;
import com.disenio.mimagrupo06.repositorios.RepoArea;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion;
import com.disenio.mimagrupo06.repositorios.RepoOrganizacion1;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("3")
public class AgenteSectorial extends Usuario {
  @ManyToOne
  private Sector sectorTerritorial;
  @Transient
  private RepoArea ra;

  @OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.ALL}) // TODO Agregue esto
  @JoinColumn(name = "sector_id")
  private List<ValorHCMensualSector> valoresHCMensualSector = new ArrayList<>();

  public RepoArea getRa() {
    return ra;
  }

  public void setRa(RepoArea ra) {
    this.ra = ra;
  }

  public AgenteSectorial(String usuario, String contrasenia, Sector sector) throws NoSuchAlgorithmException {
    super(usuario, contrasenia);
    this.sectorTerritorial = sector;

    setTipoUsuario(3);
  }

  public AgenteSectorial() {

  }

  public Sector getSectorTerritorial() {
    return sectorTerritorial;
  }

  private void agregarHCMensual(int anio, int mes, Double hcSector) {
    ValorHCMensualSector valorHC = new ValorHCMensualSector(anio, mes, hcSector, this.sectorTerritorial, this);
    this.valoresHCMensualSector.add(valorHC);
  }

  public double calcularHuellaCarbonoPorSectorAnual(int anio) {
    List<Area> listaAreas = ra.findAll();

    //Conseguir anio
    int meses;

    //conseguir cantidad de meses de dicho anio
    if(anio != LocalDate.now().getYear()){
      meses = 12;
    }else{
      meses = LocalDate.now().getMonthValue()-1;
    }

    double hcSector=0.0;

    for(int i =1; i<=meses;i++){
      hcSector += this.calcularHuellaCarbonoPorSectorMensual(anio,i);
    }

    return hcSector;
  /*
    double hcPorSector = listaAreas
        .stream()
        .filter(area -> area.perteneceSector(sectorTerritorial))
        .collect(Collectors.toList())
        .stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaAnual(anio))
        .sum();

    return hcPorSector;*/
  }
  // TODO CAMBIE ESTE METODO Y EL DE ABAJO
  public double calcularHuellaCarbonoPorSectorMensual(int anio, int mes) {
    List<Area> listaAreas = ra.findAll();

    double hcSector;
    if(valoresHCMensualSector.stream().noneMatch(valorHCMensualSector -> valorHCMensualSector.soyMes(anio,mes, this.sectorTerritorial, this))) {
      if(anio <= LocalDate.now().getYear()){
        hcSector = listaAreas
                .stream()
                .filter(area -> area.perteneceSector(sectorTerritorial))
                .collect(Collectors.toList())
                .stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaMensual(anio, mes))
                .sum();
        agregarHCMensual(anio, mes, hcSector);
      } else {
        hcSector = 0;
      }


    }else{
      hcSector = valoresHCMensualSector
              .stream()
              .filter(valorHCMensualSector -> valorHCMensualSector.soyMes(anio,mes, this.sectorTerritorial, this))
              .collect(Collectors.toList())
              .get(0)
              .getHuellaCarbono();
    }
    return hcSector;

  }

  public void setSectorTerritorial(Sector sectorTerritorial) {
    this.sectorTerritorial = sectorTerritorial;
  }
}
