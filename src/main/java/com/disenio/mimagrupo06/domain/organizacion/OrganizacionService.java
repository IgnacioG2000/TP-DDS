package com.disenio.mimagrupo06.domain.organizacion;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import com.disenio.mimagrupo06.excel_ETL.Transformador;
import org.springframework.context.annotation.Bean;


public class OrganizacionService {
  private Transformador transformador;
  private CalculadoraHCActividad calculadoraHCActividad;

  public void cargarDatosActividad(String path,Organizacion organizacion) {
    transformador.cargarDatos(organizacion, path);
  }

  public Transformador getTransformador() {
    return transformador;
  }

  public void setTransformador(Transformador transformador) {
    this.transformador = transformador;
  }
  @Bean
  public OrganizacionService organizacionService() {
    OrganizacionService organizacionService = new OrganizacionService();
    this.setTransformador(new Transformador());
    return organizacionService;
  }

  public CalculadoraHCActividad getCalculadoraHCActividad() {
    return calculadoraHCActividad;
  }

  public void setCalculadoraHCActividad(CalculadoraHCActividad calculadoraHCActividad) {
    this.calculadoraHCActividad = calculadoraHCActividad;
  }

  public double calcularHuellaCarbonoTotalAnio(int anio, Organizacion organizacion) {
    double hcAreas = organizacion.getSectores().stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaAnual(anio)).sum();
    double hcActividad = calculadoraHCActividad.calcularHCActividadAnual(organizacion.getDatosDeLaActividad(), anio);
    return hcActividad + hcAreas;
  }

  public double calcularHuellaCarbonoTotalMensual(int anio, int mes,Organizacion organizacion) {
    double hcAreas = organizacion.getSectores().stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalAreaMensual(anio, mes)).sum();
    double hcActividad = calculadoraHCActividad.calcularHCActividadMensual(organizacion.getDatosDeLaActividad(), anio, mes);
    return hcActividad + hcAreas;
  }
}
