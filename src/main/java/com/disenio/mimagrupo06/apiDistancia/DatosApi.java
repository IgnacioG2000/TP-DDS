package com.disenio.mimagrupo06.apiDistancia;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatosApi {
  private static final DatosApi INSTANCE = new DatosApi();

 // private ServicioApiDistancia servicio =  new ServicioApiDistancia();

  List<Provincia> provincias = new ArrayList<>();
  List<Municipio> municipios = new ArrayList<>();
  List<Localidad> localidades = new ArrayList<>();

  private void obtenerProvincias() throws IOException {
  String idPais = ServicioApiDistancia.getInstancia().obtenerIdPais();
  //el offset está hardcodeado
  provincias.addAll(ServicioApiDistancia.getInstancia().listadoDeProvincias(idPais));
  }

  private void obtenerLocalidades() throws IOException  {

    // recorre mientras que indice < tam lista
    for (int i = 0; i < municipios.size(); i++) {
      localidades.addAll(ServicioApiDistancia.getInstancia().listadoLocalidades(municipios.get(i).getId()));
    }
  }

  private void obtenerMunicipios() throws IOException  {

  // recorre mientras que indice < tam lista
  for (int i = 0; i < provincias.size(); i++) {
    municipios.addAll(ServicioApiDistancia.getInstancia().listadoMunicipios(provincias.get(i).getId()));
  }
  }

  //@Scheduled()
  private void actualizarValores() throws IOException {
    provincias.clear();
    municipios.clear();
    localidades.clear();
    this.obtenerProvincias();
    this.obtenerMunicipios();
    this.obtenerLocalidades();

  }


}
