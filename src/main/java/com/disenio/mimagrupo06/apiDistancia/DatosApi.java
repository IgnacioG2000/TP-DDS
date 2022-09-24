package com.disenio.mimagrupo06.apiDistancia;

import com.disenio.mimagrupo06.domain.sector.LocalidadSector;
import com.disenio.mimagrupo06.domain.sector.MunicipioSector;
import com.disenio.mimagrupo06.domain.sector.PaisSector;
import com.disenio.mimagrupo06.domain.sector.ProvinciaSector;
import com.disenio.mimagrupo06.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class DatosApi {
 //private static final DatosApi INSTANCE = new DatosApi();
  // private ServicioApiDistancia servicio =  new ServicioApiDistancia();
  @Autowired
  private RepoPaisSector ps;

  @Autowired
  private RepoProvinciaSector rps;
  @Autowired
  private RepoMunicipioSector ms;
  @Autowired
  private RepoLocalidadSector ls;


  List<Provincia> provincias = new ArrayList<>();
  List<Municipio> municipios = new ArrayList<>();
  List<Localidad> localidades = new ArrayList<>();

  List<Provincia> obtenerProvincias() throws IOException {
  String idPais = ServicioApiDistancia.getInstancia().obtenerIdPais();
  //el offset está hardcodeado
  return ServicioApiDistancia.getInstancia().listadoDeProvincias(idPais);
  }

  //public static DatosApi getInstance() {return INSTANCE;}

  private List<Localidad> obtenerLocalidades() throws IOException  {

    // recorre mientras que indice < tam lista
    for (int i = 0; i < municipios.size(); i++) {
      localidades.addAll(ServicioApiDistancia.getInstancia().listadoLocalidades(municipios.get(i).getId()));
    }
    return localidades;
  }

  private List<Municipio> obtenerMunicipios() throws IOException  {

  // recorre mientras que indice < tam lista
  for (int i = 0; i < provincias.size(); i++) {
    municipios.addAll(ServicioApiDistancia.getInstancia().listadoMunicipios(provincias.get(i).getId()));
  }
  return municipios;

  }

  public void cargarDatos() throws IOException {
    this.persistirPaises();
    this.persitirProvincias();
    this.persistirMunicipios();
    this.persistirLocalidades();
  }

  private void persistirPaises() throws IOException {
    List<Pais> paises = ServicioApiDistancia.getInstancia().listadoDePais(1);
    PaisSector pais = new PaisSector(Long.parseLong(paises.get(0).getId()), paises.get(0).getNombre());
    ps.save(pais);
  }


  private void persitirProvincias() throws IOException {

    provincias = this.obtenerProvincias();
    if(provincias.size() == 0) {
      System.out.println("toi vacio xd");
    }
    provincias.forEach(p -> rps.save(new ProvinciaSector(Long.parseLong(p.getId()), p.getNombre(), ps.findById(Long.parseLong(p.getPais().getId())).get())));


  }

  private void persistirMunicipios() throws IOException{
      municipios = this.obtenerMunicipios();
      municipios.forEach(m-> ms.save(new MunicipioSector(Long.parseLong(m.getId()), m.nombre, rps.findByProvinciaCodigo(Long.parseLong(m.getProvincia().getId())))));

  }

  private void persistirLocalidades() throws IOException{
      localidades = this.obtenerLocalidades();
      System.out.println("tamanio localidades" + localidades.size());
      //Long id, String nombre, String codPostal, Municipio municipio)
      localidades.forEach(l->ls.save(new LocalidadSector(l.getId(), l.getNombre() , l.getCodPostal() , ms.findByMunicipioCodigo(Long.parseLong(l.getMunicipio().getId())))));
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

  //List<Localidad> obtenerLocalidadesDe(MunicipioSector m) {return findBy(m.nombre, m.provincia);}


}
