package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUsuario extends CrudRepository<Usuario,Long> {
  Usuario findByUsuarioAndContraseniaHasheada(String usuario,String contraseniaHasheada);
}
