package apiDistancia;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
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

