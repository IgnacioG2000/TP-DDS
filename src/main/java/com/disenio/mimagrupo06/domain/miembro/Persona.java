package com.disenio.mimagrupo06.domain.miembro;

import com.disenio.mimagrupo06.seguridad.roles.*;
import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo",discriminatorType = DiscriminatorType.INTEGER)
public class Persona {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nombre;
  private String apellido;
  @Enumerated
  private TipoDocumento tipoDocumento;
  private String nroDocumento;
  @ManyToOne
  private Hogar ubicacion;
  @OneToOne
  private Usuario usuario;

  public Persona(String nombre, String apellido, TipoDocumento tipoDocumento, String nroDocumento, Hogar ubicacion, Usuario usuario) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.tipoDocumento = tipoDocumento;
    this.nroDocumento = nroDocumento;
    this.ubicacion = ubicacion;
    this.usuario = usuario;
  }

  public Persona(){

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
