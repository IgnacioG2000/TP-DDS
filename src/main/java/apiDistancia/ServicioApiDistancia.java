package apiDistancia;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.huellaDeCarbono.espacio.Espacio;
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

  //quizas en un futuro ponemos esto por si queremos agregar mas de un header (?)
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

  public List<Pais> listadoDePais(int offset) throws IOException {
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<List<Pais>> requestPaises = apiDistancia.paises(offset);
    Response<List<Pais>> responsePaises;

   // System.out.println(requestPaises.request().header("accept"));
     try {

      responsePaises = requestPaises.execute();
      System.out.println(responsePaises.code());

      System.out.println(responsePaises.raw());

      return  responsePaises.body();
    } catch (IOException e) {
      //e.printStackTrace();
      System.out.println("tmb toi aca");
      throw new RuntimeException("error xd");
    }
  }

  public List<Provincia> listadoDeProvincias(int offset, String idPais) throws IOException {
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<List<Provincia>> requestProvincias = apiDistancia.provincias(offset, idPais);
    Response<List<Provincia>> responseProvincias;

    // System.out.println(requestPaises.request().header("accept"));
    try {
      responseProvincias = requestProvincias.execute();
      return  responseProvincias.body();
    } catch (IOException e) {
      //e.printStackTrace();
      System.out.println("tmb toi aca");
      throw new RuntimeException("error xd");
    }
  }


  public List<Municipio> listadoMunicipios(int offset, String idProvincia) {
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<List<Municipio>> requestMunicipios = apiDistancia.municipios(offset, idProvincia);
    Response<List<Municipio>> responseMunicipios;

    // System.out.println(requestPaises.request().header("accept"));
    try {
      responseMunicipios = requestMunicipios.execute();
      return  responseMunicipios.body();
    } catch (IOException e) {
      //e.printStackTrace();
      System.out.println("tmb toi aca");
      throw new RuntimeException("error xd");
    }
  }

  public List<Localidad> listadoLocalidades(int offset, String idMunicipio) {
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<List<Localidad>> requestLocalidades = apiDistancia.localidades(offset, idMunicipio);
    Response<List<Localidad>> responseLocalidades;

    // System.out.println(requestPaises.request().header("accept"));
    try {
      responseLocalidades= requestLocalidades.execute();
      return  responseLocalidades.body();
    } catch (IOException e) {
      //e.printStackTrace();
      System.out.println("tmb toi aca");
      throw new RuntimeException("error xd");
    }
  }

  /* Esto Anda - Forma 1
  public Distancia ObtenerDistancia(String idLocalidadOrigen, String calleOrigen, String alturaOrigen, String idLocalidadDestino, String calleDestino, String alturaDestino) {

    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<Distancia> requestDistancia = apiDistancia.distancia(idLocalidadOrigen, calleOrigen, alturaOrigen, idLocalidadDestino, calleDestino, alturaDestino);
    Response<Distancia> responseDistancia;

    // System.out.println(requestPaises.request().header("accept"));
    try {
      responseDistancia= requestDistancia.execute();
      return  responseDistancia.body();
    } catch (IOException e) {
      //e.printStackTrace();
      System.out.println("tmb toi aca");
      throw new RuntimeException("error xd");
    }
  }
*/
/*
  public Double obtenerDistancia(Espacio origen, Espacio llegada) {

    return Double.parseDouble(this.calculoDistancia(origen, llegada).getValor());
  }
*/

  // FORMA 2 -> Incluye "Espacio"
  public Distancia calculoDistancia(Espacio espacioOrigen, Espacio espacioDestino) throws IOException {
    String idLocalidadOrigen = this.obtenerIdLocalidad(espacioOrigen.getLocalidad(), espacioOrigen.getMunicipio(), espacioOrigen.getProvincia());
    String calleOrigen = espacioOrigen.getDireccion();
    String alturaOrigen = espacioOrigen.getNumero();
    String idLocalidadDestino = this.obtenerIdLocalidad(espacioDestino.getLocalidad(), espacioDestino.getMunicipio(), espacioDestino.getProvincia());
    String calleDestino = espacioDestino.getDireccion();
    String alturaDestino = espacioDestino.getNumero();

    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<Distancia> requestDistancia = apiDistancia.distancia(idLocalidadOrigen, calleOrigen, alturaOrigen, idLocalidadDestino, calleDestino, alturaDestino);
    Response<Distancia> responseDistancia;

    // System.out.println(requestPaises.request().header("accept"));
    try {
      responseDistancia= requestDistancia.execute();
      return  responseDistancia.body();
    } catch (IOException e) {
      //e.printStackTrace();
      System.out.println("tmb toi aca");
      throw new RuntimeException("error xd");
    }
  }

  private String obtenerIdLocalidad(String localidad, String municipio, String provincia) throws IOException {

    String id_localidad;
    String idPais = "9";
    ServicioApiDistancia servicioApiDistancia = ServicioApiDistancia.getInstancia();

    //Obtengo ID de la provincia
    List<Provincia> listadoProvincias = servicioApiDistancia.listadoDeProvincias(1, idPais);
    Optional <Provincia> provinciaObtenida = listadoProvincias.stream().filter(unaProvincia -> provincia.equals(unaProvincia.getNombre())).findFirst();
    String idProvincia = provinciaObtenida.get().getId();

    // Obtengo ID del municipio
    List<Municipio> listadoMunicipios = servicioApiDistancia.listadoMunicipios(1, idProvincia);
    Optional <Municipio> municipioObtenido = listadoMunicipios.stream().filter(unMunicipio -> municipio.equals(unMunicipio.getNombre())).findFirst();
    String idMuncipio = municipioObtenido.get().getId();


    //Obtengo ID de la localidad
    List<Localidad> listadoLocalidades = servicioApiDistancia.listadoLocalidades(1, idMuncipio);
    Optional <Localidad> localidadObtenida = listadoLocalidades.stream().filter(unaLocalidad -> localidad.equals(unaLocalidad.getNombre())).findFirst();
    String idLocalidad = localidadObtenida.get().getId();

        return idLocalidad;
  }


}
/*
 public List<Pais> listarPaises() {
    return listadoDePais().getPaises();
  }
  public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
    Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }
*/

