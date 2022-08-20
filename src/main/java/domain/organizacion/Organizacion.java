package domain.organizacion;

import domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;
import excel_ETL.Transformador;
import notificadores.Notificador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Organizacion {
  private String razonSocial;
  private TipoDeOrganizacion tipoDeOrganizacion;
  private Collection<Area> sectores;
  private List<Contacto> contactos;
  private Clasificacion clasificacion;
  private Transformador transformador;
  private CalculadoraHCActividad calculadoraHCActividad;
  private List<Notificador> notificadoresPreferidos;

  public Organizacion(String razonSocial, TipoDeOrganizacion tipoDeOrganizacion, Collection<Area> sectores,
      Clasificacion clasificacion, Transformador transformador,
      CalculadoraHCActividad calculadoraHCActividad) {
    this.razonSocial = razonSocial;
    this.tipoDeOrganizacion = tipoDeOrganizacion;
    this.sectores = sectores;
    this.clasificacion = clasificacion;
    this.transformador = transformador;
    this.calculadoraHCActividad = calculadoraHCActividad;
    this.notificadoresPreferidos = new ArrayList<>();
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public TipoDeOrganizacion getTipoDeOrganizacion() {
    return tipoDeOrganizacion;
  }

  public void setTipoDeOrganizacion(TipoDeOrganizacion tipoDeOrganizacion) {
    this.tipoDeOrganizacion = tipoDeOrganizacion;
  }

  public Collection<Area> getSectores() {
    return sectores;
  }

  public void setSectores(Collection<Area> sectores) {
    this.sectores = sectores;
  }

  public Clasificacion getClasificacion() {
    return clasificacion;
  }

  public void setClasificacion(Clasificacion clasificacion) {
    this.clasificacion = clasificacion;
  }

  public void agregarArea(Area area) {
    sectores.add(area);
  }

  public void agregarNotificador(Notificador notificador) {
    this.notificadoresPreferidos.add(notificador);
  }

  public Double calcularHuellaCarbonoTotalFecha(LocalDate fecha, boolean esMensual) {
    Double hcAreas = sectores.stream().mapToDouble(area -> area.calcularHuellaCarbonoTotalArea(fecha, esMensual)).sum();
    Double hcActividad = calculadoraHCActividad.calcularHCActividad(transformador.getDatosDeLaActividad(), fecha,
        esMensual);
    return hcActividad + hcAreas;
  }

  public boolean tieneArea(Area area) {
    return sectores.contains(area);
  }

  public void agregarContacto(String mail, String num) {
    contactos.add(new Contacto(mail, num));
  }

  public List<Contacto> getContactos() {
    return contactos;
  }

  public List<Notificador> getNotificadoresPreferidos() {
    return notificadoresPreferidos;
  }
}
