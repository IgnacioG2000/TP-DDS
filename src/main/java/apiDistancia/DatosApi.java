package apiDistancia;


import exception.NoSeEncuentraEnLaApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatosApi {
  private static final DatosApi INSTANCE = new DatosApi();

 // private ServicioApiDistancia servicio =  new ServicioApiDistancia();

  List<Provincia> provincias = new ArrayList<>();
  List<Municipio> municipios = new ArrayList<>();

  private void obtenerProvincias() throws IOException {
  String idPais = ServicioApiDistancia.getInstancia().obtenerIdPais();
  //el offset está hardcodeado
  provincias.addAll(ServicioApiDistancia.getInstancia().listadoDeProvincias(1, idPais));
  }

  private void obtenerMunicipios() throws IOException  {

  // recorre mientras que indice < tam lista
  for (int i = 0; i < provincias.size(); i++) {
    municipios.addAll(ServicioApiDistancia.getInstancia().listadoMunicipios(provincias.get(i).getId()));
  }
  }




}
