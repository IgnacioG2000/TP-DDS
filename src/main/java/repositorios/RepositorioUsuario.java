package repositorios;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

public class RepositorioUsuario {
  byte[] salt = getSalt();
  //Key Usuario - Value Contrasenia hasheada
  HashMap<String,String> usuarios = new HashMap<>();
  private static final RepositorioUsuario INSTANCE = new RepositorioUsuario();

  public static RepositorioUsuario getInstance() {
    return INSTANCE;
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

  public void agregarUsuario(String usuario, String contrasenia) throws NoSuchAlgorithmException {
    usuarios.put(usuario, generarHash(contrasenia, salt));
  }

  public static byte[] getSalt()  {
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[30];
    secureRandom.nextBytes(salt); // proxima semilla
    return salt;
  }

  private boolean contraseniaCoincide(String usuario, String contraseniaValidar) throws NoSuchAlgorithmException {
    String contraseniaAValidarHashed = generarHash(contraseniaValidar, salt);
    return contraseniaAValidarHashed.equals(usuarios.get(usuario));
  }
}
