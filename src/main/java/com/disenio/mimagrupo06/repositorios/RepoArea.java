package com.disenio.mimagrupo06.repositorios;


import com.disenio.mimagrupo06.domain.organizacion.Area;
import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepoArea extends CrudRepository<Area, Long> {

  List<Area> findAll();
}
