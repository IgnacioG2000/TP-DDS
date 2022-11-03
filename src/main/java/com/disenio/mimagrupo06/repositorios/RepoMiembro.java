package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.miembro.Miembro;
import com.disenio.mimagrupo06.domain.miembro.Persona;

import org.springframework.data.repository.CrudRepository;

public interface RepoMiembro extends CrudRepository<Miembro, Long> {
  Miembro findByPersona(Persona persona);
}
