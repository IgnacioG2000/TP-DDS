package com.disenio.mimagrupo06.apiDistancia;

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

  @GET("localidades")
  Call<List<Localidad>> localidades(@Query("offset") int offset, @Query("municipioId") String idMunicipio);

  @GET("distancia")
  Call<Distancia> distancia(@Query("localidadOrigenId") String idLocalidadOrigen,
                                  @Query("calleOrigen") String calleOrigen,
                                  @Query("alturaOrigen") String alturaOrigen,
                                  @Query("localidadDestinoId") String idLocalidadDestino,
                                  @Query("calleDestino") String calleDestino,
                                  @Query("alturaDestino") String alturaDestino);


}
