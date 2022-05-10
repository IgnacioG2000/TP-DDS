package domain.roles;

import password.ValidadorDeMetricas;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Usuario {
  private final String usuario;
  private final String contraseniaHasheada;

  public ValidadorDeMetricas miValidador = new ValidadorDeMetricas();

  public Usuario(String usuario, String contrasenia) throws NoSuchAlgorithmException {
    String contraseniaValidada = miValidador.validarTodo(usuario, contrasenia);
    byte[] unSalt = getSalt();
    //hashear contrasenia
    this.contraseniaHasheada = generarHash(contraseniaValidada,unSalt);
    this.usuario = usuario;
  }

    public String getUsuario () {
      return usuario;
    }

    public String getContraseniaHasheada () {
      return contraseniaHasheada;
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
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[30];
    secureRandom.nextBytes(salt); // proxima semilla
    return salt;
  }

}
