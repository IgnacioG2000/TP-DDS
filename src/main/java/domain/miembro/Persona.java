package domain.miembro;

import seguridad.roles.*;
import domain.huellaDeCarbono.espacio.*;

public class Persona {
  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private String nroDocumento;
  private Hogar ubicacion;
  private Usuario usuario;

  public Persona(String nombre, String apellido, TipoDocumento tipoDocumento, String nroDocumento, Hogar ubicacion, Usuario usuario) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoDocumento = tipoDocumento;
    this.nroDocumento = nroDocumento;
    this.ubicacion = ubicacion;
    this.usuario = usuario;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public TipoDocumento getTipoDocumento() {
    return tipoDocumento;
  }

  public void setTipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = tipoDocumento;
  }

  public String getNroDocumento() {
    return nroDocumento;
  }

  public void setNroDocumento(String nroDocumento) {
    this.nroDocumento = nroDocumento;
  }

  public Hogar getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(Hogar ubicacion) {
    this.ubicacion = ubicacion;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
