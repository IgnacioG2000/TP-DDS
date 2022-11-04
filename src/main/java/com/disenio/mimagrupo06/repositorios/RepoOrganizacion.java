package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoOrganizacion extends CrudRepository<Organizacion, Long> {
  //List<Organizacion> findAByUsuario();
}
