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
  //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(tokenInterceptor).build();

  Gson gson = new GsonBuilder()
          .setLenient()
          .create();

  private ServicioApiDistancia() throws IOException {
    this.setUrlApi(this.obtenerUrlAPI());
    this.retrofit = new Retrofit.Builder()
            //.client(client)
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

  /*
    public Distancia paginaDeHogares(String authHeader) throws IOException {
      Distancia refugiosService = this.retrofit.create(Distancia.class);
      //Call<PaginaDeHogares> https://media.discordapp.net/attachments/958739109986304047/984245922395484160/unknown.pngrequestHogares = refugiosService.hogares(authHeader);
      Response<Distancia> responseDeHogares = requestHogares.execute();
      return responseDeHogares.body();
    }
  */
  public String obtenerUrlAPI() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    System.out.println(propiedades.getProperty("urlApi"));
    return propiedades.getProperty("urlApi");
  }

  public String obtenerTokenAutorizacion() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return propiedades.getProperty("tokenAutorizacionAPI");
  }

  public List<Pais> listadoDePais() throws IOException {
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<List<Pais>> requestPaises = apiDistancia.paises("Bearer " + this.obtenerTokenAutorizacion());
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

