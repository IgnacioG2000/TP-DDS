package com.disenio.mimagrupo06.repositorios;

import com.disenio.mimagrupo06.domain.organizacion.Organizacion;
import com.disenio.mimagrupo06.seguridad.roles.Usuario;
import com.disenio.mimagrupo06.seguridad.roles.UsuarioOrganizacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoOrganizacion extends CrudRepository<Organizacion, Long> {
  //List<Organizacion> findAll();
    Organizacion findByUsuarioOrganizacion(UsuarioOrganizacion usuarioOrganizacion);
}
