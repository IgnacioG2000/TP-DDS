package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Tramo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface RepoTramo extends CrudRepository<Tramo, Long> {
  List<Tramo> findAll();
}
