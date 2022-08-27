package apiDistancia;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.huellaDeCarbono.espacio.Espacio;
import exception.NoSeEncuentraEnLaApi;
import exception.NoSePuedeCalcularDistancia;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioApiDistancia {
  private static ServicioApiDistancia instancia = null;
  private final Retrofit retrofit;
  private static String urlApi;
  private TokenInterceptor tokenInterceptor = new TokenInterceptor();


  OkHttpClient client = new OkHttpClient.Builder().addInterceptor(tokenInterceptor).build();

  Gson gson = new GsonBuilder()
          .setLenient()
          .create();

  private ServicioApiDistancia() throws IOException {

    this.setUrlApi(ArchivoConfig.obtenerUrlAPI());
    this.retrofit = new Retrofit.Builder()
            .client(client)
            .baseUrl(urlApi)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
  }

  private void setUrlApi(String urlApi) {
    ServicioApiDistancia.urlApi = urlApi;
  }

  public static ServicioApiDistancia getInstancia() throws IOException {
    if (instancia == null) {
      instancia = new ServicioApiDistancia();
    }
    return instancia;
  }

  public List<Pais> listadoDePais(int offset) {
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<List<Pais>> requestPaises = apiDistancia.paises(offset);
    Response<List<Pais>> responsePaises;
     try {
      responsePaises = requestPaises.execute();
      return  responsePaises.body();
    } catch (IOException e) {
      throw new RuntimeException("Se produjo un error en la llamada a la api, no se pueden obtener los paises");
    }
  }

  public List<Provincia> listadoDeProvincias(String idPais) throws NoSeEncuentraEnLaApi {
    //puede que este hardcodeao haya que verlo
    int offset = 1;
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<List<Provincia>> requestProvincias = apiDistancia.provincias(offset, idPais);
    Response<List<Provincia>> responseProvincias;
    try {
      responseProvincias = requestProvincias.execute();

      return  responseProvincias.body();
    } catch (IOException e) {
      throw new NoSeEncuentraEnLaApi("Se produjo un error en la llamada a la api, no se pueden obtener las provincias");
    }
  }

  //offset siempre tiene que arrancar en 1 porque lo itera adentro
  public List<Municipio> listadoMunicipios(String idProvincia) throws NoSeEncuentraEnLaApi  {
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    int indice = 1 ;
    Response<List<Municipio>> responseMunicipios;
    List<Municipio> listaCompleta = new ArrayList<>();

    try {
      Call<List<Municipio>> requestMunicipios = apiDistancia.municipios(indice, idProvincia);
      responseMunicipios = requestMunicipios.execute();
      while(responseMunicipios.body().size() > 0) {
        //hace una llamada inneceasria pero funciona
        requestMunicipios = apiDistancia.municipios(indice, idProvincia);
        responseMunicipios = requestMunicipios.execute();
        listaCompleta.addAll(responseMunicipios.body());
        indice++;
      }

      System.out.print("el tam de la lista completa es " + listaCompleta.size()+ "\n");


      return  listaCompleta;
    } catch (IOException e) {
      throw new NoSeEncuentraEnLaApi ("Se produjo un error en la llamada a la api, no se pueden obtener los municipios");
    }
  }

  public List<Localidad> listadoLocalidades(String idMunicipio) throws NoSeEncuentraEnLaApi  {
    int offset = 1;
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<List<Localidad>> requestLocalidades = apiDistancia.localidades(offset, idMunicipio);
    Response<List<Localidad>> responseLocalidades;

    try {
      responseLocalidades = requestLocalidades.execute();
      return  responseLocalidades.body();
    } catch (IOException e) {
      throw new NoSeEncuentraEnLaApi ("Se produjo un error en la llamada a la api, no se pueden obtener las localidades");
    }
  }

  public Double obtenerDistancia(Espacio origen, Espacio llegada) throws IOException {

    try {
      Double.parseDouble(this.calculoDistancia(origen, llegada).getValor());
    } catch(NoSeEncuentraEnLaApi exp) {
      throw new NoSePuedeCalcularDistancia("No se puede calcular la distancia porque " + exp.getMessage());
    }
    return Double.parseDouble(this.calculoDistancia(origen, llegada).getValor());
  }


  public Distancia calculoDistancia(Espacio espacioOrigen, Espacio espacioDestino) throws NoSeEncuentraEnLaApi, IOException {
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    String idLocalidadOrigen;
    String idLocalidadDestino;
    String calleOrigen = espacioOrigen.getDireccion();
    String alturaOrigen = espacioOrigen.getNumero();
    String calleDestino = espacioDestino.getDireccion();
    String alturaDestino = espacioDestino.getNumero();
    Response<Distancia> responseDistancia;

    try {
      idLocalidadOrigen = this.obtenerIdLocalidad(espacioOrigen.getLocalidad(), espacioOrigen.getMunicipio(), espacioOrigen.getProvincia());
      idLocalidadDestino = this.obtenerIdLocalidad(espacioDestino.getLocalidad(), espacioDestino.getMunicipio(), espacioDestino.getProvincia());

      Call<Distancia> requestDistancia =  apiDistancia.distancia(idLocalidadOrigen, calleOrigen, alturaOrigen, idLocalidadDestino, calleDestino, alturaDestino);
      System.out.print("estoy ejecutando hasta aca\n");
    
      responseDistancia = requestDistancia.execute();
      return  responseDistancia.body();
    } catch (NoSeEncuentraEnLaApi exp) {
      throw new NoSeEncuentraEnLaApi(exp.getMessage());
     }


  }

    public String obtenerIdPais() {
    //esto medio que esta hardcodeado pero sabemos como funciona entonces meh
    return this.listadoDePais(1).get(0).getId();
  }

  private String obtenerIdLocalidad(String localidad, String municipio, String provincia) throws NoSeEncuentraEnLaApi {

    String idLocalidad;
    String idPais = this.obtenerIdPais();


    //Obtengo ID de la provincia
    List<Provincia> listadoProvincias = this.listadoDeProvincias(idPais);
    Optional <Provincia> provinciaObtenida = listadoProvincias.stream().filter(unaProvincia -> provincia.equals(unaProvincia.getNombre())).findFirst();

    if(!provinciaObtenida.isPresent()) {
      throw new NoSeEncuentraEnLaApi("No se encontra la provincia " + provincia);
    }else {
      String idProvincia = provinciaObtenida.get().getId();
      System.out.print("Id de provincia: " + idProvincia);

      List<Municipio> listadoMunicipios = this.listadoMunicipios(idProvincia);
      Optional<Municipio> municipioObtenido = listadoMunicipios.stream().filter(unMunicipio -> municipio.equals(unMunicipio.getNombre())).findFirst();

      if (!municipioObtenido.isPresent()) {

        throw new NoSeEncuentraEnLaApi("No se encontro el municipio " + municipio);

      } else {
        String idMuncipio = municipioObtenido.get().getId();
        List<Localidad> listadoLocalidades = this.listadoLocalidades(idMuncipio);
        Optional<Localidad> localidadObtenida = listadoLocalidades.stream().filter(unaLocalidad -> localidad.equals(unaLocalidad.getNombre())).findFirst();

        if (!localidadObtenida.isPresent()) throw new NoSeEncuentraEnLaApi("No se encontro la localidad " + localidad);
        else {
          idLocalidad = localidadObtenida.get().getId();
        }

      }


    }
  return idLocalidad;
}
}


