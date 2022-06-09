package apiDistancia;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioApiDistancia {
  private static ServicioApiDistancia instancia = null;
  private final Retrofit retrofit;
  private static String urlApi;

  private ServicioApiDistancia() throws IOException {
    this.setUrlApi(this.obtenerUrlAPI());
    this.retrofit = new Retrofit.Builder().baseUrl(urlApi)
        .addConverterFactory(GsonConverterFactory.create())
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
    return propiedades.getProperty("urlApi");
  }

  public String obtenerTokenAutorizacion() throws IOException {
    Properties propiedades = new Properties();
    propiedades.load(new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
    return propiedades.getProperty("tokenAutorizacionAPI");
  }

  public ListadoPais listadoDePais() throws IOException {
    ApiDistancia apiDistancia = this.retrofit.create(ApiDistancia.class);
    Call<ListadoPais> requestPaises  = apiDistancia.paises();
    Response<ListadoPais> responsePaises = requestPaises.execute();
    return responsePaises.body();
  }
/*
  public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia) throws IOException {
    GeorefService georefService = this.retrofit.create(GeorefService.class);
    Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
    Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
    return responseListadoDeMunicipios.body();
  }
*/
}
