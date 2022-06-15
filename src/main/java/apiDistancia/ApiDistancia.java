package apiDistancia;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface ApiDistancia {

  @GET("paises")
  Call<List<Pais>> paises(@Query ("offset") int offset);

  @GET("provincias")
  Call<List<Provincia>> provincias(@Query ("offset") int offset, @Query("paisId") String idPais);

  @GET("municipios")
  Call<List<Municipio>> municipios(@Query ("offset") int offset, @Query("provinciaId") String idProvincia);
/*
  @GET("municipios")
  Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos);

  @GET("municipios")
  Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("max") int max);
  */
}
