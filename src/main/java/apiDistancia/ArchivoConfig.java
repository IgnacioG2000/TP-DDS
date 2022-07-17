package apiDistancia;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ArchivoConfig {

  static public String obtenerUrlAPI() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    System.out.println(propiedades.getProperty("urlApi"));
    return propiedades.getProperty("urlApi");
  }

  static public String obtenerTokenAutorizacion() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return propiedades.getProperty("tokenAutorizacionAPI");
  }

  static public String obtenerValorK() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return propiedades.getProperty("K");

  }

  static public String obtenerFECamion() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return propiedades.getProperty("FECamion");

  }

  static public String obtenerFEUtilitario() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return propiedades.getProperty("FEUtilitario");

  }

}
