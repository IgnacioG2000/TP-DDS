package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.apiDistancia.Localidad;
import com.disenio.mimagrupo06.domain.sector.LocalidadSector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoLocalidadSector extends CrudRepository<LocalidadSector, Long> {
}
