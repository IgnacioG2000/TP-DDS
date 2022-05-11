package domain.roles;

import password.ValidadorDeMetricas;
import repositorios.RepositorioUsuario;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Usuario {
  private String usuario;
  //no deberia ir la contraseña acá

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
