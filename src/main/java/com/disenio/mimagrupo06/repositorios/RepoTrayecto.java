package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.trayecto.Trayecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoTrayecto extends CrudRepository<Trayecto, Long> {
    List<Trayecto> findAll();
}
