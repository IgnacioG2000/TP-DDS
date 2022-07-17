package seguridad.roles;

import domain.huellaDeCarbono.CalculadoraHC.CalculadoraHCActividad;

import java.security.NoSuchAlgorithmException;

public class Administrador extends Usuario {
  private CalculadoraHCActividad calculadoraHCActividad;

  public Administrador(String usuario, String contrasenia
      , CalculadoraHCActividad calculadoraHCActividad) throws NoSuchAlgorithmException {
    super(usuario, contrasenia);
    this.calculadoraHCActividad = calculadoraHCActividad;
  }

  public CalculadoraHCActividad getCalculadoraHCActividad() {
    return calculadoraHCActividad;
  }



}
