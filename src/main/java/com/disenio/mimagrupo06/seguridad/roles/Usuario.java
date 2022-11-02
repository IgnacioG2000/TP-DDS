package com.disenio.mimagrupo06.seguridad.roles;

import com.disenio.mimagrupo06.repositorios.RepositorioUsuario;
import com.disenio.mimagrupo06.seguridad.password.ValidadorDeMetricas;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo",discriminatorType = DiscriminatorType.INTEGER)
public abstract class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String usuario;
  private String contraseniaHasheada;
  @Transient
  public ValidadorDeMetricas miValidador = new ValidadorDeMetricas();
  @Transient
  byte[] salt = getSalt();

  public Usuario(String usuario, String contrasenia) throws NoSuchAlgorithmException {
    String contraseniaValidada = miValidador.validarTodo(usuario, contrasenia);
    this.usuario = usuario;
    //hashear contrasenia
    this.contraseniaHasheada = this.generarHash(contrasenia, salt);
  }

  public static String generarHash(String contrasenia,byte[] salt) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.reset();
    digest.update(salt);
    byte[] hash = digest.digest(contrasenia.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();

    for (byte b : hash) {
      sb.append(String.format("%02x", b));
    }

    return sb.toString();
  }

  public static byte[] getSalt()  {
    //SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[]{1, 2, 4, 8, 16, 32, 64, (byte) 128};
    //secureRandom.nextBytes(salt); // proxima semilla
    return salt;
  }

  public Usuario() {

  }

  public String getUsuario() {
    return usuario;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
