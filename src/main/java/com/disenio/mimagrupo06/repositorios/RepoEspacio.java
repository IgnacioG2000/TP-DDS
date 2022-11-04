package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.huellaDeCarbono.espacio.Espacio;
import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface RepoEspacio extends CrudRepository<Espacio, Long> {
    List<Espacio> findAll();
}
