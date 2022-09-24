package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.apiDistancia.Municipio;
import com.disenio.mimagrupo06.domain.sector.MunicipioSector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoMunicipioSector extends CrudRepository<MunicipioSector, Long> {

    MunicipioSector findByMunicipioCodigo(Long municipioCodigo);
}
