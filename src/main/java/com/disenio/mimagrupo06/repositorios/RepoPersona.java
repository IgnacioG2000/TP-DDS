package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.miembro.Persona;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface RepoPersona extends CrudRepository<Persona, Long> {
  Persona findByUsuario(Usuario usuario);
}
