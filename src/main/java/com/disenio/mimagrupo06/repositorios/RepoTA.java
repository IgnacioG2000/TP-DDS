package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.CalculadoraHC.TipoActividad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.Optional;

@Repository
public interface RepoTA extends CrudRepository<TipoActividad, Long> {

    Optional<TipoActividad> findByNombre(String nombre);
    List<TipoActividad> findAll();
}
