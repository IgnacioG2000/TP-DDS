package domain.roles;

import password.ValidadorDeMetricas;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
  private String usuario;
  private String contraseniaHasheada;
  private String salt;

  public ValidadorDeMetricas miValidador = new ValidadorDeMetricas();

  public Usuario(String usuario, String contrasenia, String nombre, String apellido) {
    String contraseniaCompactada = miValidador.validar(usuario, contrasenia);
    //hashear contrasenia
    String contraHash = "";
    this.contraseniaHasheada = contraHash;
    this.usuario = usuario;
  }

    public String getUsuario () {
      return usuario;
    }

    public String getContraseniaHasheada () {
      return contraseniaHasheada;
    }

    public String getSalt () {
      return salt;
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
}
