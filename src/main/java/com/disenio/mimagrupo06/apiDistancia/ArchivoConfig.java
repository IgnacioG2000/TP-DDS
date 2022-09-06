package com.disenio.mimagrupo06.apiDistancia;

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

  static public Double obtenerValorK() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return Double.parseDouble(propiedades.getProperty("K"));
  }

  static public Double obtenerFENoMotorizados() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return Double.parseDouble(propiedades.getProperty("FENoMotorizado"));
  }

  static public Double obtenerFEVehiculoParticular() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return Double.parseDouble(propiedades.getProperty("FEVehiculoParticular"));
  }

  static public Double obtenerFEServicioContratado() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return Double.parseDouble(propiedades.getProperty("FEServicioContratado"));
  }

  static public Double obtenerFETrasnportePublico() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return Double.parseDouble(propiedades.getProperty("FETransportePublico"));
  }

  static public Double obtenerFECamion() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return Double.parseDouble(propiedades.getProperty("FECamion"));
  }

  static public Double obtenerFEUtilitario() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return Double.parseDouble(propiedades.getProperty("FEUtilitario"));
  }

  static public Double obtenerCoeficienteHCMensual() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return Double.parseDouble(propiedades.getProperty("CoeficienteHCMensual"));
  }

}
