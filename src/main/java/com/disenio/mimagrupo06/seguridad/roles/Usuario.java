package com.disenio.mimagrupo06.seguridad.roles;

import com.disenio.mimagrupo06.repositorios.RepositorioUsuario;
import com.disenio.mimagrupo06.seguridad.password.ValidadorDeMetricas;

import java.security.NoSuchAlgorithmException;

public class Usuario {
  private String usuario;
  private boolean admistrador;

  public ValidadorDeMetricas miValidador = new ValidadorDeMetricas();

  public Usuario(String usuario, String contrasenia) throws NoSuchAlgorithmException {
    String contraseniaValidada = miValidador.validarTodo(usuario, contrasenia);
    //hashear contrasenia
    this.usuario = usuario;
    RepositorioUsuario.getInstance().agregarUsuario(usuario, contraseniaValidada);
  }

  public String getUsuario() {
    return usuario;
  }








}
